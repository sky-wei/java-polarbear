package com.sky.account.manager

import com.sky.account.manager.controller.AppController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.ConfigurationManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.impl.AccountManagerImpl
import com.sky.account.manager.data.disk.impl.ConfigurationManagerImpl
import com.sky.account.manager.data.disk.impl.DBManagerImpl
import com.sky.account.manager.util.ResUtil
import javafx.application.Application
import javafx.scene.text.Font
import javafx.stage.Stage

/**
 * Created by sky on 17-8-17.
 */
class App : Application(), PolarBear {

    lateinit var mDbManager: DBManager
    lateinit var mAccountManager: AccountManager
    lateinit var mConfigurationManager: ConfigurationManager
    lateinit var mAppController: AppController

    override fun init() {
        super.init()

        Font.loadFont(
                ResUtil.getResourceUrl("font/hwxh.ttf"), 14.0)

        // 初始化
        mDbManager = DBManagerImpl()
        mAccountManager = AccountManagerImpl(mDbManager)
        mConfigurationManager = ConfigurationManagerImpl()
        mAppController = AppController(this)
    }

    override fun start(primaryStage: Stage) {
        mAppController.start(primaryStage)
    }

    override fun stop() {
        super.stop()

        // 释放
        mDbManager.release()
    }

    override fun getAccountManager(): AccountManager {
        return mAccountManager
    }

    override fun getDBManager(): DBManager {
        return mDbManager
    }

    override fun getConfigurationManager(): ConfigurationManager {
        return mConfigurationManager
    }

    override fun getAppController(): AppController {
        return mAppController
    }
}


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}