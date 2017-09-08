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

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AdminModel
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.VerifyUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class RegisterController : BaseController<Any>(), Initializable {

    @FXML lateinit var jtfName: TextField
    @FXML lateinit var jtfPassword: PasswordField
    @FXML lateinit var jftTwicePassword: PasswordField
    @FXML lateinit var jBtm: Button

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