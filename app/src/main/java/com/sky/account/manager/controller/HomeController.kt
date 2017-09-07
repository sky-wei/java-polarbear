/*
 * Copyright (c) 2017. The sky Authors
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

import com.sky.account.manager.Constant
import com.sky.account.manager.base.BaseController
import com.sky.account.manager.model.AccountModel
import com.sky.account.manager.util.DialogUtil
import com.sky.account.manager.util.Log
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.text.Text
import javafx.stage.Stage
import java.awt.Desktop
import java.awt.Toolkit
import java.net.URI
import java.net.URL
import java.text.SimpleDateFormat
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
    @FXML lateinit var jtaExpand: TextArea

    lateinit var tvTableSelection: TableView.TableViewSelectionModel<AccountModel>

    companion object {
        val DATA_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        // 设置版本号
        tVersion.text = "Version: ${Constant.App.VERSION}"

        // 初始化表结构
        name.cellValueFactory = PropertyValueFactory<AccountModel, String>("name")
        password.cellValueFactory = PropertyValueFactory<AccountModel, String>("password")
        url.cellValueFactory = PropertyValueFactory<AccountModel, String>("url")
        desc.cellValueFactory = PropertyValueFactory<AccountModel, String>("desc")

        tvTableSelection = tvTable.selectionModel
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
                showEditAccountDialog("PolarBear - 新建账号", AccountModel()) {
                    Platform.runLater { createAccount(it) }
                }
            }
            mtModifyPassword -> {
                // 修改密码
                DialogUtil.showMessage(Alert.AlertType.INFORMATION, "功能暂未实现!")
            }
            miImportAccount -> {
                // 导入账号
                DialogUtil.showMessage(Alert.AlertType.INFORMATION, "功能暂未实现!")
            }
            miExportAccount -> {
                // 导出账号
                DialogUtil.showMessage(Alert.AlertType.INFORMATION, "功能暂未实现!")
            }
            miExit -> {
                // 退出程序
                getAppController().exitApp()
            }
            miSettings -> {
                // 程序设置
                DialogUtil.showMessage(Alert.AlertType.INFORMATION, "功能暂未实现!")
            }
            miAbout -> {
                // 关于
                getAppController().showDialog(
                        "PolarBear - 关于", "layout/about.fxml", 400.0, 300.0, Any()) {
                    /** 什么也不需要操作 */
                }
            }
        }
    }

    private fun onMenuAction(item: MenuItem, event: ActionEvent) {

        if (!isTabSelected()) return

        val accountManager = getAccountManager()

        // 获取选择的内容
        val selectedItem = getTabSelectedItem()

        when(item.text) {
            "打开" -> {
                // 打开浏览器,目前打开浏览器有问题
//                try {
//                    Desktop.getDesktop()
//                            .browse(URI("http://www.baidu.com"))
//                } catch (tr: Throwable) {
//                    Log.e("打开页面异常", tr)
//                }
            }
            "显示" -> {
                // 显示账号详情
                getAppController().showDialog(
                        "PolarBear - 账号详情", "layout/details.fxml",
                        400.0, 260.0,
                        accountManager.decryptionAccount(selectedItem)) {
                    /** 什么也不用做 */
                }
            }
            "修改" -> {
                // 编辑账号
                showEditAccountDialog(
                        "PolarBear - 修改账号",
                        accountManager.decryptionAccount(selectedItem)) {
                    Platform.runLater { editAccount(it) }
                }
            }
            "删除" -> {
                // 删除账号
                accountManager.deleteAccount(selectedItem)
                Platform.runLater { onSearchAction() }
            }
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

        if (MouseButton.SECONDARY == event.button
                && isTabSelected()) {

            val menu = ContextMenu(
//                    buildMenuItem("打开"),  // 调用浏览器打开有问题
                    buildMenuItem("显示"),
                    buildMenuItem("修改"),
                    buildMenuItem("删除"))

            // 显示菜单
            menu.show(getStage(), event.screenX, event.screenY)
        }

        setExpandAccountInfo(getTabSelectedIndex())
    }

    private fun buildMenuItem(name: String): MenuItem {

        val menuItem = MenuItem(name)

        menuItem.addEventHandler(ActionEvent.ACTION) {

            // 处理菜单事件
            onMenuAction(it.source as MenuItem, it)
        }

        return menuItem
    }

    private fun showEditAccountDialog(title: String, account: AccountModel, callback: (AccountModel) -> Unit) {

        getAppController().showDialog(
                title, "layout/edit_account.fxml",
                400.0, 300.0,
                account, callback)
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

    private fun editAccount(account: AccountModel) {

        val accountManager = getAccountManager()

        if (accountManager.updateAccount(account)) {
            // 刷新列表
            Platform.runLater { onSearchAction() }
            return
        }

        DialogUtil.showMessage(
                Alert.AlertType.ERROR, "修改账号异常，请查看相关日志信息！")
    }

    private fun setExpandAccountInfo(index: Int) {

        if (index < 0) {
            // 设置扩展内容
            jtaExpand.text = ""
            return
        }

        // 获取选择内容
        val account = getTabSelectedItem()

        val info = StringBuilder().apply {
            append("用户名 : ${account.name}\n")
            append("密码 :  ${account.password}\n")
            append("网站地址 : ${account.url}\n")
            append("描述内容 : ${account.desc}\n")
            append("创建时间 : ${DATA_FORMAT.format(account.createTime)}")
        }

        // 设置扩展内容
        jtaExpand.text = info.toString()
    }

    private fun getTabSelectedIndex(): Int {
        return tvTableSelection.selectedIndex
    }

    private fun getTabSelectedItem(): AccountModel {
        return tvTableSelection.selectedItem
    }

    private fun isTabSelected(): Boolean {
        return tvTableSelection.selectedIndex > -1
    }
}
