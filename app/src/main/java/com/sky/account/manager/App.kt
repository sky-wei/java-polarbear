/*
 * Copyright (c) 2017. The sky Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.account.manager

import com.sky.account.manager.controller.AppController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.data.disk.ConfigurationManager
import com.sky.account.manager.data.disk.DBManager
import com.sky.account.manager.data.disk.impl.AccountManagerImpl
import com.sky.account.manager.data.disk.impl.ConfigurationManagerImpl
import com.sky.account.manager.data.disk.impl.DBManagerImpl
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.ResUtil
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.text.Font
import javafx.stage.Stage

/**
 * Created by sky on 17-8-17.
 */
class App : Application(), PolarBear {

    private lateinit var mDbManager: DBManager
    private lateinit var mAccountManager: AccountManager
    private lateinit var mConfigurationManager: ConfigurationManager
    private lateinit var mAppController: AppController

    override fun init() {
        super.init()

        // 加载字体
        Font.loadFont(
                ResUtil.getResourceUrl("font/hwxh.ttf"), 14.0)

        // 异常处理
        Thread.setDefaultUncaughtExceptionHandler {
            t, e -> Platform.runLater { DialogUtil.showException(t, e) }
        }

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