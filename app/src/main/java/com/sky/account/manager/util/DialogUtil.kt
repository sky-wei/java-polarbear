package com.sky.account.manager.util

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

/**
 * Created by sky on 17-9-6.
 */
object DialogUtil {

    fun showMessage(alertType: Alert.AlertType, msg: String) {
        Alert(alertType, msg, ButtonType.OK).show()
    }
}