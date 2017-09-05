package com.sky.account.manager

import com.sky.account.manager.controller.AppController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.ConfigurationManager
import com.sky.account.manager.data.disk.DBManager

/**
 * Created by sky on 17-9-4.
 */
interface PolarBear {

    fun getConfigurationManager(): ConfigurationManager

    fun getAccountManager(): AccountManager

    fun getDBManager(): DBManager

    fun getAppController(): AppController
}
