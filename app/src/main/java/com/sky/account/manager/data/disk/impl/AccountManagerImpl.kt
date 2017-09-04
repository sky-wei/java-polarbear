package com.sky.account.manager.data.disk.impl

import com.j256.ormlite.dao.Dao
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.converter.AccountConverter
import com.sky.account.manager.data.disk.converter.AdminConverter
import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.data.disk.entity.AdminEntity
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.MD5Util
import com.sky.account.manager.util.SecretUtil
import java.io.File

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

    override fun updateAdmin(model: AdminModel): Boolean {

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

    override fun importAccount(models: List<AccountModel>): Boolean {
        return false
    }

    override fun exportAccount(models: List<AccountModel>, path: File): Boolean {
        return false
    }
}
