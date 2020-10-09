package com.amansingh63.zomatoapidemo.util.network

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

object InternetUtil : LiveData<Boolean>() {

    private var broadcastReceiver: BroadcastReceiver? = null
    private lateinit var application: Application

    fun init(application: Application) {
        InternetUtil.application = application
    }

    fun isInternetOn(): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onActive() {
        registerBroadCastReceiver()
    }

    override fun onInactive() {
        unRegisterBroadCastReceiver()
    }

    private fun registerBroadCastReceiver() {
        if (broadcastReceiver == null) {
            val filter = IntentFilter()
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(_context: Context, intent: Intent) {
                    val extras = intent.extras
                    val info = extras!!.getParcelable<NetworkInfo>("networkInfo")
                    value = info!!.state == NetworkInfo.State.CONNECTED
                }
            }

            application.registerReceiver(
                broadcastReceiver, filter
            )
        }
    }

    private fun unRegisterBroadCastReceiver() {
        if (broadcastReceiver != null) {
            application.unregisterReceiver(
                broadcastReceiver
            )
            broadcastReceiver = null
        }
    }
}