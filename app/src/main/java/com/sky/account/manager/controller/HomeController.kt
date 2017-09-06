package com.sky.account.manager.controller

import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.util.ResUtil
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.*
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.StageStyle
import javafx.util.Callback
import java.net.URL
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class HomeController : BaseController<Any, Any>(), Initializable {

    @FXML lateinit var menu:Menu
    @FXML lateinit var settings:Menu
    @FXML lateinit var help:Menu

    @FXML lateinit var miNewAccount: MenuItem
    @FXML lateinit var mtModifyPassword: MenuItem
    @FXML lateinit var miImportAccount: MenuItem
    @FXML lateinit var miExportAccount: MenuItem
    @FXML lateinit var miExit: MenuItem
    @FXML lateinit var miSettings: MenuItem
    @FXML lateinit var miAbout: MenuItem

    @FXML lateinit var tVersion: Text

    @FXML lateinit var tvTable: TableView<AccountModel>
    @FXML lateinit var name: TableColumn<AccountModel, String>
    @FXML lateinit var password: TableColumn<AccountModel, String>
    @FXML lateinit var url: TableColumn<AccountModel, String>
    @FXML lateinit var desc: TableColumn<AccountModel, String>


    override fun initialize(location: URL?, resources: ResourceBundle?) {

        tVersion.text = "version: 2.0.1"

        initTable()
    }

    private fun initTable() {

        name.cellValueFactory = PropertyValueFactory<AccountModel, String>("name")
        password.cellValueFactory = PropertyValueFactory<AccountModel, String>("password")
        desc.cellValueFactory = PropertyValueFactory<AccountModel, String>("desc")
        url.cellValueFactory = PropertyValueFactory<AccountModel, String>("url")

        tvTable.items = FXCollections.observableArrayList(newData())

    }

    fun onMenuAction(event: ActionEvent) {

        when(event.source) {
            miNewAccount -> {
                // 创建账号
                Platform.runLater { showNewAccountDialog() }
            }
            mtModifyPassword -> println("创建")
            miImportAccount -> println("创建")
            miExportAccount -> println("创建")
            miExit -> {
                // 退出程序
                getAppController().exitApp()
            }
            miSettings -> println("创建")
            miAbout -> println("创建")
        }
    }

    fun onSearchKeyPressedAction(event: KeyEvent) {

    }

    fun onSearchAction() {

    }

    fun onTableMouseEvent(event: MouseEvent) {

        if (MouseButton.SECONDARY == event.button) {

            println(">>>>>>>>>>>>>>>>>>>>>> $event")
        }
    }

    private fun newData(): List<AccountModel> {
        return (1..2).map { AccountModel(it, 0, "sky", "**********************", "test", "淡淡的忧伤", System.currentTimeMillis()) }
    }

    private fun showNewAccountDialog() {

        getAppController().showDialog(
                "PolarBear - 新建账号",
                "layout/new_account.fxml",
                400.0, 300.0,
                AccountModel(),
                T())
    }

    class T : Callback<AccountModel> {
        override fun onResult(result: AccountModel) {

            println(">>>>>>>>>>>>>>>  $result")
        }
    }
}
