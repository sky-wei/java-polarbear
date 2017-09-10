/*
 * Copyright (c) 2017 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.account.manager.data.disk.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.j256.ormlite.dao.Dao
import com.sky.account.manager.data.DataException
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.converter.AccountConverter
import com.sky.account.manager.data.disk.converter.AdminConverter
import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.data.disk.entity.AdminEntity
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.Log
import com.sky.account.manager.util.MD5Util
import com.sky.account.manager.util.SecretUtil
import io.reactivex.Observable
import javafx.application.Platform
import javafx.scene.control.Alert
import java.io.File
import java.util.*

/**
 * Created by sky on 17-9-4.
 */
class AccountManagerImpl(dbManager: DBManager) : AccountManager {

    private val adminDao: Dao<AdminEntity, Int> = dbManager.getAdminDao()
    private val accountDao: Dao<AccountEntity, Int> = dbManager.getAccountDao()
    private var mAdmin: AdminModel = AdminModel()

    override fun createAdmin(model: AdminModel): Boolean {

        val admins = adminDao.queryForEq("name", model.name)

        if (admins.isNotEmpty()) return false

        val tempModel = model.copy()
        tempModel.password = MD5Util.md5sum(model.password)

        // 添加到数据库
        if (adminDao.create(
                AdminConverter.converterModel(tempModel)) > 0) {

            val admins = adminDao.queryForEq("name", model.name)
            val adminModel = AdminConverter.converterEntity(admins[0])

            // 设置未加密的密码
            adminModel.password = model.password

            mAdmin = adminModel
            return true
        }
        return false
    }

    override fun loginAdmin(model: AdminModel): Boolean {

        val admins = adminDao.queryForEq("name", model.name)

        if (admins.isEmpty()) return false

        val value = MD5Util.md5sum(model.password)

        if (admins[0].password == value) {

            val adminModel = AdminConverter.converterEntity(admins[0])

            // 设置未加密的密码
            adminModel.password = model.password

            // 保存
            mAdmin = adminModel
            return true
        }
        return false
    }

    override fun getAdmin(): AdminModel {
        return mAdmin
    }

    override fun changeAdminPassword(model: AdminModel): Observable<Boolean> {

        return Observable.create {

            try {
                // 解密的账号
                val dAccounts = ArrayList<AccountModel>()

                // 查询所有账号
                search(0, "").forEach {
                    // 解密账号
                    dAccounts.add(decryptionAccount(it))
                }

                // 更新管理员的密码
                updateAdmin(model)

                dAccounts.forEach {
                    // 更新账号
                    updateAccount(it)
                }

                it.onNext(true)
                it.onComplete()
            } catch (tr: Throwable) {
                it.onError(DataException("修改管理员密码异常!", tr))
            }
        }
    }

    private fun updateAdmin(model: AdminModel): Boolean {

        val tempModel = model.copy()
        tempModel.password = MD5Util.md5sum(model.password)

        if (adminDao.update(
                AdminConverter.converterModel(tempModel)) > 0) {
            // 保存
            mAdmin = model
            return true
        }
        return false
    }

    override fun search(type: Int, key: String): List<AccountModel> {

        if (key.isNullOrBlank()) {
            return AccountConverter.converterEntity(
                    accountDao.queryForEq("adminId", mAdmin.id))
        }
        return AccountConverter.converterEntity(
                accountDao.queryBuilder().where().eq("adminId", mAdmin.id).and().like("desc", "%$key%").query())
    }

    override fun createAccount(model: AccountModel): Boolean {

        val encrypt = encryptAccount(model)

        return accountDao.create(AccountConverter.converterModel(encrypt)) > 0
    }

    override fun deleteAccount(model: AccountModel): Boolean {
        return accountDao.deleteById(model.id) > 0
    }

    override fun updateAccount(model: AccountModel): Boolean {

        val encrypt = encryptAccount(model)

        return accountDao.update(AccountConverter.converterModel(encrypt)) > 0
    }

    override fun encryptAccount(model: AccountModel): AccountModel {

        val encrypt = model.copy()

        encrypt.password = SecretUtil.encrypt(
                mAdmin.password, model.password)

        return encrypt
    }

    override fun decryptionAccount(model: AccountModel): AccountModel {

        val decrypt = model.copy()

        decrypt.password = SecretUtil.decrypt(
                mAdmin.password, model.password)

        return decrypt
    }

    override fun importAccount(file: File): Observable<Boolean> {

        return Observable.create {

            try {
                // 获取账号信息
                val accounts = Gson()
                        .fromJson<List<AccountModel>>(file.readText())

                accounts.forEach {
                    // 创建账号
                    createAccount(
                            it.copy(id = 0, adminId = mAdmin.id,
                                    createTime = System.currentTimeMillis()))
                }

                it.onNext(true)
                it.onComplete()
            } catch (tr: Throwable) {
                it.onError(DataException("导入账号异常", tr))
            }
        }
    }

    override fun exportAccount(file: File): Observable<Boolean> {

        return Observable.create {

            try {
                // 解密的账号
                val dAccounts = ArrayList<AccountModel>()

                // 查询所有账号
                search(0, "").forEach {
                    // 解密账号
                    dAccounts.add(decryptionAccount(it))
                }

                val gson = GsonBuilder()
                        .setPrettyPrinting()
                        .create()

                // 保存到文件
                file.writeText(gson.toJson(dAccounts))

                it.onNext(true)
                it.onComplete()
            } catch (tr: Throwable) {
                it.onError(DataException("导出账号异常", tr))
            }
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}
