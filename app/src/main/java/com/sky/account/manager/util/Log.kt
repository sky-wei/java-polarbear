package com.sky.account.manager.util

import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator

/**
 * Created by sky on 17-9-1.
 */
object Log {

    private val TAG = Log::class.java.simpleName
    private val LOG = Logger.getLogger("PolarBear")

    init {
        PropertyConfigurator.configure(ResUtil.getResource("log4j.properties"))
    }

    fun i( msg: String) {
        i(TAG, msg)
    }

    fun i(tag: String, msg: String) {
        LOG.info("$tag:$msg")
    }

    fun i(msg: String, tr: Throwable) {
        i(TAG, msg, tr)
    }

    fun i(tag: String, msg: String, tr: Throwable) {
        LOG.info("$tag:$msg", tr)
    }

    fun d( msg: String) {
        d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        LOG.debug("$tag:$msg")
    }

    fun d(msg: String, tr: Throwable) {
        d(TAG, msg, tr)
    }

    fun d(tag: String, msg: String, tr: Throwable) {
        LOG.debug("$tag:$msg", tr)
    }

    fun w( msg: String) {
        w(TAG, msg)
    }

    fun w(tag: String, msg: String) {
        LOG.warn("$tag:$msg")
    }

    fun w(msg: String, tr: Throwable) {
        w(TAG, msg, tr)
    }

    fun w(tag: String, msg: String, tr: Throwable) {
        LOG.warn("$tag:$msg", tr)
    }

    fun e( msg: String) {
        e(TAG, msg)
    }

    fun e(tag: String, msg: String) {
        LOG.error("$tag:$msg")
    }

    fun e(msg: String, tr: Throwable) {
        e(TAG, msg, tr)
    }

    fun e(tag: String, msg: String, tr: Throwable) {
        LOG.error("$tag:$msg", tr)
    }
}
