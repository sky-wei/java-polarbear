package com.sky.account.manager.controller

import com.sky.account.manager.Constant
import com.sky.account.manager.base.BaseController
import com.sky.account.manager.data.disk.AccountManager
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.util.DialogUtil
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.*
import javafx.scene.text.Text
import javafx.stage.Stage
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.util.*

/**
 * Created by sky on 17-8-17.
 */
class HomeController : BaseController<Any>(), Initializable {

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

    @FXML lateinit var jtfSearchKey: TextField
    @FXML lateinit var jBtmSearch: Button
    @FXML lateinit var tVersion: Text

    @FXML lateinit var tvTable: TableView<AccountModel>
    @FXML lateinit var name: TableColumn<AccountModel, String>
    @FXML lateinit var password: TableColumn<AccountModel, String>
    @FXML lateinit var url: TableColumn<AccountModel, String>
    @FXML lateinit var desc: TableColumn<AccountModel, String>


    override fun initialize(location: URL?, resources: ResourceBundle?) {

        // 设置版本号
        tVersion.text = Constant.App.VERSION

        // 初始化表结构
        name.cellValueFactory = PropertyValueFactory<AccountModel, String>("name")
        password.cellValueFactory = PropertyValueFactory<AccountModel, String>("password")
        url.cellValueFactory = PropertyValueFactory<AccountModel, String>("url")
        desc.cellValueFactory = PropertyValueFactory<AccountModel, String>("desc")
    }

    override fun initParam(stage: Stage, param: Any) {
        super.initParam(stage, param)

        // 默认搜索
        Platform.runLater { onSearchAction() }
    }

    fun onMenuAction(event: ActionEvent) {

        when(event.source) {
            miNewAccount -> {
                // 创建账号
                showNewAccountDialog()
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

        if (KeyCode.ENTER == event.code) {
            // 直接调用搜索
            Platform.runLater { onSearchAction() }
        }
    }

    fun onSearchAction() {

        val accountManager = getAccountManager()

        // 搜索相应的账号
        val accountModels = accountManager.search(0, jtfSearchKey.text)

        // 设置信息
        tvTable.items = FXCollections.observableArrayList(accountModels)
    }

    fun onTableMouseEvent(event: MouseEvent) {

        if (MouseButton.SECONDARY == event.button) {

            println(">>>>>>>>>>>>>>>>>>>>>> $event")
        }
    }

    private fun showNewAccountDialog() {

        getAppController().showDialog(
                "PolarBear - 新建账号",
                "layout/edit_account.fxml",
                400.0, 300.0,
                AccountModel()) {
            // 创建账号
            createAccount(it)
        }
    }

    private fun createAccount(account: AccountModel) {

        val accountManager = getAccountManager()

        // 重新设置时间
        account.adminId = accountManager.getAdmin().id
        account.createTime = System.currentTimeMillis()

        if (accountManager.createAccount(account)) {
            // 刷新列表
            Platform.runLater { onSearchAction() }
            return
        }

        DialogUtil.showMessage(
                Alert.AlertType.ERROR, "创建账号异常，请查看相关日志信息！")
    }
}
