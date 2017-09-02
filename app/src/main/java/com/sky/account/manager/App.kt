package com.sky.account.manager

import com.sky.account.manager.util.Log
import com.sky.account.manager.util.ResUtil
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
class App : Application() {

    override fun start(primaryStage: Stage) {

        val root = ResUtil.getLayout<Parent>("layout/login.fxml")
        primaryStage.scene = Scene(root, 550.0, 400.0)
        primaryStage.title = "账号注册"
        primaryStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        primaryStage.setOnCloseRequest {
//            Platform.exit()
        }
        primaryStage.show()

        test()
    }

    fun test() {

        var primaryStage = Stage()

        val root = ResUtil.getLayout<Parent>("layout/login.fxml")
        primaryStage.scene = Scene(root, 550.0, 400.0)
        primaryStage.title = "账号注册"
        primaryStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
        primaryStage.setOnCloseRequest {
//            Platform.exit()
        }
        primaryStage.show()
    }
}


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}