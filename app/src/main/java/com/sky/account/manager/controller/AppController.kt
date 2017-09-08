/*
 * Copyright (c) 2017 The sky Authors.
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

package com.sky.account.manager.controller

import com.sky.account.manager.PolarBear
import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.util.ResUtil
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle

/**
 * Created by sky on 17-9-5.
 */
class AppController(private val polarBear: PolarBear) {

    lateinit var stage: Stage

    fun start(primaryStage: Stage) {

        // 初始化
        initStage(primaryStage)

        if (isCreateAdminAccount()) {

            // 显示注册界面
            setAppScene("PolarBear - 注册", "layout/register.fxml", 400.0, 300.0)
            return
        }

        // 显示登录界面
        setAppScene("PolarBear - 登录", "layout/login.fxml", 400.0, 300.0)
//        setAppStage("PolarBear - sky", "layout/home.fxml", 650.0, 500.0)
    }

    fun setAppStage(title: String, layout: String, width: Double, height: Double) {

        stage.close()
        initStage(Stage())

        setAppScene(title, layout, width, height)
    }

    fun setAppScene(title: String, layout: String, width: Double, height: Double) {

        val loader = ResUtil.getFXMLLoader(layout)
        val scene = Scene(loader.load(), width, height)

        val controller = loader.getController<BaseController<Any>>()
        controller.setPolarBear(polarBear)
        controller.initParam(stage, Any())
        controller.setResultCallback { /** 什么也不做 */ }

        setAppScene(title, scene)
    }

    fun setAppScene(title: String, scene: Scene) {

        stage.title = title
        stage.scene = scene

        if (!stage.isShowing) stage.show()
    }

    fun <T> showDialog(title: String, layout: String, width: Double, height: Double, param: T, callback: (T) -> Unit) {

        val loader = ResUtil.getFXMLLoader(layout)
        val scene = Scene(loader.load(), width, height)

        val dialogStage = newDialogStage()

        loader.setControllerFactory {  }
        val controller = loader.getController<BaseController<T>>()
        controller.setPolarBear(polarBear)
        controller.initParam(dialogStage, param)
        controller.setResultCallback(callback)

        dialogStage.title = title
        dialogStage.scene = scene

        if (!dialogStage.isShowing) dialogStage.show()
    }

    fun exitApp() {
        Platform.exit()
    }

    private fun initStage(primaryStage: Stage) {

        stage = primaryStage

        stage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        stage.isResizable = false
        stage.setOnCloseRequest {
            Platform.exit()
        }
    }

    private fun newDialogStage(): Stage {

        val dialogStage = Stage()

        dialogStage.initModality(Modality.APPLICATION_MODAL)
        dialogStage.initOwner(stage)
        dialogStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        dialogStage.isResizable = false

        return dialogStage
    }

    private fun isCreateAdminAccount(): Boolean {

        val adminDao = polarBear.getDBManager().getAdminDao()

        return adminDao.queryForAll().isEmpty()
    }
}