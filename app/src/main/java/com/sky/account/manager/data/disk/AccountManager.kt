package com.sky.account.manager.data.disk

import com.sky.account.manager.data.DataException
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.model.AdminModel
import java.io.File

/**
 * Created by sky on 17-9-3.
 */
interface AccountManager {

    fun createAdmin(model: AdminModel): Boolean

    fun loginAdmin(model: AdminModel): Boolean

    fun getAdmin(): AdminModel

    fun updateAdmin(model: AdminModel): Boolean

    fun search(type: Int, key: String): List<AccountModel>

    fun createAccount(model: AccountModel): Boolean

    fun deleteAccount(model: AccountModel): Boolean

    fun updateAccount(model: AccountModel): Boolean

    @Throws(DataException::class)
    fun encryptAccount(model: AccountModel): AccountModel

    @Throws(DataException::class)
    fun decryptionAccount(model: AccountModel): AccountModel

    fun importAccount(models: List<AccountModel>): Boolean

    fun exportAccount(models: List<AccountModel>, path: File): Boolean
}