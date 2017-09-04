package com.sky.account.manager.data.disk.impl

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.stmt.PreparedQuery
import com.j256.ormlite.stmt.QueryBuilder
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.entity.AdminEntity
import org.junit.Test

/**
 * Created by sky on 17-9-4.
 */
class DBManagerImplTest {

    @Test
    fun release() {

        val dbManager = DBManagerImpl() as DBManager

        var admin = AdminEntity(0, "sky", "jingcai1314", "test", System.currentTimeMillis())

        val adminDao = dbManager.getAdminDao()
//        adminDao.create(admin)

        println(">>>>>>>>>>>>> ${adminDao.queryForEq("name", "sky")}")

        dbManager.release()
    }
}