package com.sky.account.manager.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import com.sky.account.manager.PolarBear
import com.sky.account.manager.model.AdminModel
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class RegisterController : Initializable, AppController {

    @FXML lateinit var jtfName: JFXTextField
    @FXML lateinit var jtfPassword: JFXPasswordField
    @FXML lateinit var jftTwicePassword: JFXPasswordField
    @FXML lateinit var JFXButton: JFXButton

    lateinit var mPolarBear: PolarBear

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    override fun setPolarBear(bear: PolarBear) {
        mPolarBear = bear
    }

    fun onRegisterAction() {

        val accountManager = mPolarBear.getAccountManager()

        val model = AdminModel(jtfName.text, jtfPassword.text, "admin", System.currentTimeMillis())

        if (accountManager.createAdmin(model)) {

            println(">>>>>>>>>>>>>>>>>>>>> 成功了")

            Platform.exit()
        }
    }
}