package com.sky.account.manager.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.VerifyUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
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

    fun onKeyPressedAction(event: KeyEvent) {

        if (KeyCode.ENTER == event.code) {
            // 登录
            Platform.runLater { onRegisterAction() }
        }
    }

    fun onRegisterAction() {

        if (VerifyUtil.isEmpty(jtfName, "用户名不能为空！")
                || VerifyUtil.isEmpty(jtfPassword, "密码不能为空！")
                || VerifyUtil.isEmpty(jftTwicePassword, "确认密码不能为空！")
                || VerifyUtil.isMinLength(jtfName, 3, "用户名不能小于三个字符！")
                || VerifyUtil.isMinLength(jtfPassword, 9, "密码不能小于九个字符！")
                || !VerifyUtil.isEquals(jtfPassword, jftTwicePassword, "两个密码不相同，请重新输入！")) {
            return
        }

        val accountManager = mPolarBear.getAccountManager()

        // 创建管理员对象
        val model = AdminModel(
                jtfName.text, jtfPassword.text, "admin", System.currentTimeMillis())

        if (accountManager.createAdmin(model)) {
            // 显示登录界面
            setAppStage("PolarBear - sky", "layout/home.fxml", 650.0, 500.0)
            return
        }

        DialogUtil.showMessage(
                Alert.AlertType.ERROR, "创建管理员异常，请查看相关日志信息！")
    }
}