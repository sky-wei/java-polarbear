package com.sky.account.manager.util

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import java.net.URL

/**
 * Created by sky on 17-9-1.
 */
object ResUtil {

    fun getResource(name: String): URL {
        return javaClass.classLoader.getResource(name)
    }

    fun getResourceUrl(name: String): String {
        return getResource(name).toExternalForm()
    }

    fun getImage(name: String): Image {
        return Image(getResourceUrl(name))
    }

    fun <T> getLayout(name: String): T {
        return FXMLLoader.load(getResource(name))
    }

    fun getFXMLLoader(name: String): FXMLLoader {
        return FXMLLoader(getResource(name))
    }
}
