package com.sky.account.manager.controller

import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import com.sky.account.manager.base.BaseController
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class LoginController : BaseController(), Initializable {

    @FXML lateinit var jtfName: JFXTextField
    @FXML lateinit var jtfPassword: JFXPasswordField

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    fun onLoginAction() {
        setAppStage("PolarBear - sky", "layout/home.fxml", 650.0, 500.0)
    }
}
