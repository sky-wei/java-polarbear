package com.sky.account.manager.data.disk

import com.j256.ormlite.dao.Dao
import com.sky.account.manager.data.disk.entity.AccountEntity
import com.sky.account.manager.data.disk.entity.AdminEntity

/**
 * Created by sky on 17-9-4.
 */
interface DBManager {

    fun getAdminDao(): Dao<AdminEntity, Int>

    fun getAccountDao(): Dao<AccountEntity, Int>

    fun release()
}