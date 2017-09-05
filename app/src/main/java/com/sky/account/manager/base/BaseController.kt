package com.sky.account.manager.base

import com.sky.account.manager.PolarBear
import com.sky.account.manager.controller.AppController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.ConfigurationManager
import com.sky.account.manager.data.disk.DBManager
import javafx.scene.Scene

/**
 * Created by sky on 17-9-4.
 */
abstract class BaseController {

    lateinit var mPolarBear: PolarBear

    fun setPolarBear(bear: PolarBear) {
        mPolarBear = bear


    }

    fun getAccountManager(): AccountManager {
        return mPolarBear.getAccountManager()
    }

    fun getDBManager(): DBManager {
        return mPolarBear.getDBManager()
    }

    fun getConfigurationManager(): ConfigurationManager {
        return mPolarBear.getConfigurationManager()
    }

    fun getAppController(): AppController {
        return mPolarBear.getAppController()
    }

    fun setAppStage(title: String, layout: String, width: Double, height: Double) {
        getAppController().setAppStage(title, layout, width, height)
    }

    fun setAppScene(title: String, layout: String, width: Double, height: Double) {
        getAppController().setAppScene(title, layout, width, height)
    }

    fun setAppScene(title: String, scene: Scene) {
        getAppController().setAppScene(title, scene)
    }
}