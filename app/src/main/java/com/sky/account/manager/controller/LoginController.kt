package com.sky.account.manager.controller

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.VerifyUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class LoginController : BaseController<Any>(), Initializable {

    @FXML lateinit var jtfName: TextField
    @FXML lateinit var jtfPassword: PasswordField

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    fun onKeyPressedAction(event: KeyEvent) {

        if (KeyCode.ENTER == event.code) {
            // 登录
            Platform.runLater { onLoginAction() }
        }
    }

    fun onLoginAction() {

        if (VerifyUtil.isEmpty(jtfName, "请输入用户名！")
                || VerifyUtil.isEmpty(jtfPassword, "请输入登录的密码！")) {
            return
        }

        // 创建管理员对象
        val model = AdminModel(jtfName.text, jtfPassword.text)

        val accountManager = mPolarBear.getAccountManager()

        if (accountManager.loginAdmin(model)) {
            // 登录成功了
            setAppStage("PolarBear - sky", "layout/home.fxml", 650.0, 500.0)
            return
        }

        DialogUtil.showMessage(
                Alert.AlertType.ERROR, "登录失败，请确认你输入的用户名跟密码是否正确！")
    }
}
