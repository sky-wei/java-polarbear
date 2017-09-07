package com.sky.account.manager.util

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import java.io.PrintWriter
import java.io.StringWriter


/**
 * Created by sky on 17-9-6.
 */
object DialogUtil {

    fun showMessage(alertType: Alert.AlertType, msg: String) {
        val alert = Alert(alertType, msg, ButtonType.OK)
        alert.dialogPane.stylesheets.add(ResUtil.getResourceUrl("style/bootstrap2.css"))
        alert.show()
    }

    fun showException(thread: Thread, tr: Throwable) {

        val alert = Alert(AlertType.ERROR)
        alert.dialogPane.stylesheets.add(ResUtil.getResourceUrl("style/bootstrap2.css"))

        alert.title = "程序异常"
        alert.headerText = "程序出现异常了,赶紧去上报异常情况!"

        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        val exceptionText = sw.toString()

        Log.e("Exception", "程序异常了 {$thread}", tr)

        val label = Label("The exception stacktrace was:")

        val textArea = TextArea(exceptionText)
        textArea.isEditable = false
        textArea.isWrapText = true

        textArea.maxWidth = java.lang.Double.MAX_VALUE
        textArea.maxHeight = java.lang.Double.MAX_VALUE
        GridPane.setVgrow(textArea, Priority.ALWAYS)
        GridPane.setHgrow(textArea, Priority.ALWAYS)

        val expContent = GridPane()
        expContent.maxWidth = java.lang.Double.MAX_VALUE
        expContent.add(label, 0, 0)
        expContent.add(textArea, 0, 1)

        // Set expandable Exception into the dialog pane.
        alert.dialogPane.content = expContent

        alert.show()
    }
}