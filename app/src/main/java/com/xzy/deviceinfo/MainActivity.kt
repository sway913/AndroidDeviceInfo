package com.xzy.deviceinfo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xzy.deviceinfo.util.SystemUtils
import com.zy.devicelibrary.UtilsApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDeviceInfo()
        initDeviceInf2()
    }

    private fun initDeviceInf2() {
        UtilsApp.init(this.application)
    }

    private fun initDeviceInfo() {
        val basic = SystemUtils.getSysVersionInfo(this)
        val memory = SystemUtils.getMemoInfo(this)
        val cpu = SystemUtils.getCpuInfo()
        val imei = SystemUtils.getIMEI(this)
        val storage = SystemUtils.getTotalInternalMemorySize() + SystemUtils.getAvailableInternalMemorySize() +
                SystemUtils.getTotalExternalMemorySize() + SystemUtils.getAvailableExternalMemorySize()
        val sensor = SystemUtils.showSensorInfo(this)
        val mac = SystemUtils.getMacAddress(MainActivity@this)
        basicInfo.text = basic
        memoryInfo.text = memory
        imeiInfo.text = imei
        cpuInfo.text = cpu
        storageInfo.text = storage
        sensorInfo.text = sensor
        macInfo.text = mac
    }

}
