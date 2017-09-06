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

        val controller = loader.getController<BaseController<Any, Any>>()
        controller.setPolarBear(polarBear)

        setAppScene(title, scene)
    }

    fun setAppScene(title: String, scene: Scene) {

        stage.title = title
        stage.scene = scene

        stage.centerOnScreen()

        if (!stage.isShowing) stage.show()
    }

    fun <P, R> showDialog(title: String, layout: String, width: Double, height: Double, param: P, callback: BaseController.Callback<R>) {

        val dialogStage = Stage()
        dialogStage.initModality(Modality.APPLICATION_MODAL)
        dialogStage.initOwner(stage)
        dialogStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        dialogStage.isResizable = false

        val loader = ResUtil.getFXMLLoader(layout)
        val scene = Scene(loader.load(), width, height)

        val controller = loader.getController<BaseController<P, R>>()
        controller.setPolarBear(polarBear)
        controller.initParam(dialogStage, param)
        controller.setResultCallback(callback)

        dialogStage.title = title
        dialogStage.scene = scene
        dialogStage.centerOnScreen()

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

    private fun isCreateAdminAccount(): Boolean {

        val adminDao = polarBear.getDBManager().getAdminDao()

        return adminDao.queryForAll().isEmpty()
    }
}