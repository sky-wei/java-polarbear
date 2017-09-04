package com.sky.account.manager

import com.sky.account.manager.controller.AppController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.impl.AccountManagerImpl
import com.sky.account.manager.data.disk.impl.DBManagerImpl
import com.sky.account.manager.util.ResUtil
import com.sun.org.apache.regexp.internal.REUtil
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 * Created by sky on 17-8-17.
 */
class App : Application(), PolarBear {

    lateinit var mDbManager: DBManager
    lateinit var mAccountManager: AccountManager
    lateinit var mStage: Stage

    override fun init() {
        super.init()

        // 初始化
        mDbManager = DBManagerImpl()
        mAccountManager = AccountManagerImpl(mDbManager)
    }

    override fun start(primaryStage: Stage) {

        mStage = primaryStage

        primaryStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        primaryStage.isResizable = false
        primaryStage.setOnCloseRequest {
            Platform.exit()
        }

        val admins = mDbManager.getAdminDao().queryForAll()

        if (admins.isEmpty()) {

            val loader = ResUtil.getFXMLLoader("layout/register.fxml")
            val scene = Scene(loader.load(), 400.0, 300.0)
            loader.getController<AppController>().setPolarBear(this)

            primaryStage.title = "PolarBear - 注册"
            primaryStage.scene = scene
        } else {

            val loader = ResUtil.getFXMLLoader("layout/login.fxml")
            val scene = Scene(loader.load(), 400.0, 300.0)
            loader.getController<AppController>().setPolarBear(this)

            primaryStage.title = "PolarBear - 登录"
            primaryStage.scene = scene
        }

        primaryStage.show()
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
}


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}