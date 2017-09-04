package com.sky.account.manager

import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.DBManager

/**
 * Created by sky on 17-9-4.
 */
interface PolarBear {

    fun getAccountManager(): AccountManager

    fun getDBManager(): DBManager
}
