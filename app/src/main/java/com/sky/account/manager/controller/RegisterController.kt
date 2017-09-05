package com.sky.account.manager.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AdminModel
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class RegisterController : BaseController(), Initializable {

    @FXML lateinit var jtfName: JFXTextField
    @FXML lateinit var jtfPassword: JFXPasswordField
    @FXML lateinit var jftTwicePassword: JFXPasswordField
    @FXML lateinit var jBtm: JFXButton

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    fun onRegisterAction() {

        val accountManager = mPolarBear.getAccountManager()

        val model = AdminModel(
                jtfName.text, jtfPassword.text, "admin", System.currentTimeMillis())

        if (accountManager.createAdmin(model)) {
            // 显示登录界面
            setAppStage("PolarBear - sky", "layout/home.fxml", 650.0, 500.0)
        }
    }
}