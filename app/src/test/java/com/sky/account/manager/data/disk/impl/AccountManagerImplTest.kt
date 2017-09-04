package com.sky.account.manager.data.disk.impl

import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.model.AdminModel
import org.junit.*

/**
 * Created by sky on 17-9-4.
 */
class AccountManagerImplTest {

    lateinit var dbManager: DBManager
    lateinit var accountManager: AccountManager

    @Before
    fun beforeAccountManager() {

        dbManager = DBManagerImpl()
        accountManager = AccountManagerImpl(dbManager)
    }

    @After
    fun afterClassAccountManager() {
        dbManager.release()
    }

    @Test
    fun createAdmin() {

        val adminModel = AdminModel("sky4", "jingcai1314", "test", System.currentTimeMillis())
        Assert.assertTrue(accountManager.createAdmin(adminModel))
        println(">>>>>>>>>>>>>>> ${accountManager.getAdmin()}")
    }

    @Test
    fun loginAdmin() {

        val adminModel = AdminModel("sky", "jingcai1314")
        Assert.assertTrue(accountManager.loginAdmin(adminModel))
        println(">>>>>>>>>>>>>>> ${accountManager.getAdmin()}")
    }

    @Test
    fun getAdmin() {

        loginAdmin()

        println(">>>>>>>>>>>>>> ${accountManager.getAdmin()}")
        Assert.assertNotNull(accountManager.getAdmin())
    }

    @Test
    fun updateAdmin() {

        loginAdmin()
        val adminModel = accountManager.getAdmin()
        adminModel.password = "jingcai1314"

        Assert.assertTrue(accountManager.updateAdmin(adminModel))
        println(">>>>>>>>>>>>>> ${accountManager.getAdmin()}")
    }

    @Test
    fun search() {

        loginAdmin()

        println(">>>>>>>>>>>>> ${accountManager.search(0, "")}")
        Assert.assertNotNull(accountManager.search(0, ""))

        println(">>>>>>>>>>>>> ${accountManager.search(0, "te")}")
        Assert.assertNotNull(accountManager.search(0, "te"))
    }

    @Test
    fun createAccount() {

        loginAdmin()
        val adminId = accountManager.getAdmin().id

        val account = AccountModel(0, adminId, "jingcai.wei@163.com", "jingcai1314", "http://www.baidu.com", "test", System.currentTimeMillis())
        Assert.assertTrue(accountManager.createAccount(account))
    }

    @Test
    fun deleteAccount() {

        loginAdmin()

        accountManager.search(0, "").forEach { accountManager.deleteAccount(it) }
    }

    @Test
    fun updateAccount() {

        loginAdmin()

        accountManager.search(0, "").forEach {

            val account = accountManager.decryptionAccount(it)

            println(">>>>>>>>>>>>>>>>> $account")

            account.password = "124323345"

            accountManager.updateAccount(account)
        }
    }

    @Test
    fun encryptAccount() {

    }

    @Test
    fun decryptionAccount() {

    }

    @Test
    fun importAccount() {

    }

    @Test
    fun exportAccount() {

    }

}