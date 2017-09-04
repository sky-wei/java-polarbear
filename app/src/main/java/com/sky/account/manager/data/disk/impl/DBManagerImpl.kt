package com.sky.account.manager.data.disk.impl

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.sky.account.manager.Constant
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.data.disk.entity.AdminEntity
import com.sky.account.manager.util.FileUtil


/**
 * Created by sky on 17-9-4.
 */
object DBManagerImpl : DBManager {

    private val DIR = "database/"
    private val URL = "jdbc:sqlite:$DIR${Constant.Database.NAME}"

    private val connectionSource: ConnectionSource
    private val adminDao: Dao<AdminEntity, Int>
    private val accountDao: Dao<AccountEntity, Int>

    init {

        // 创建目录
        FileUtil.mkdirs("$DIR")

        // 创建连接
        connectionSource = JdbcConnectionSource(URL)

        adminDao = DaoManager.createDao(connectionSource, AdminEntity::class.java)
        accountDao = DaoManager.createDao(connectionSource, AccountEntity::class.java)

        // if you need to create the table
        TableUtils.createTableIfNotExists(connectionSource, AdminEntity::class.java)
        TableUtils.createTableIfNotExists(connectionSource, AccountEntity::class.java)
    }

    override fun getAdminDao(): Dao<AdminEntity, Int> {
        return adminDao
    }

    override fun getAccountDao(): Dao<AccountEntity, Int> {
        return accountDao
    }

    override fun release() {
        connectionSource.closeQuietly()
    }
}