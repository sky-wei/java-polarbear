package com.sky.account.manager

import com.sky.account.manager.util.ResUtil
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 * Created by sky on 17-8-17.
 */
class App : Application() {

    override fun start(primaryStage: Stage) {

        val root = ResUtil.getLayout<Parent>("layout/home.fxml")
        primaryStage.scene = Scene(root, 650.0, 500.0)
        primaryStage.title = "登录"
        primaryStage.icons.add(Image(ResUtil.getResourceUrl("image/icon.png")))
//        primaryStage.isResizable = false
        primaryStage.setOnCloseRequest {
//            Platform.exit()
        }
        primaryStage.show()
    }
}


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}