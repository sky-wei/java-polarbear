package com.sky.account.manager.util

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

/**
 * Created by sky on 17-9-6.
 */
object DialogUtil {

    fun showMessage(alertType: Alert.AlertType, msg: String) {
        val alert = Alert(alertType, msg, ButtonType.OK)
        alert.dialogPane.stylesheets.add(ResUtil.getResourceUrl("style/bootstrap2.css"))
        alert.show()
    }
}