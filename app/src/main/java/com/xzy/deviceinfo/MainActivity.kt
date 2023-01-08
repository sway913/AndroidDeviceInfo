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
        initDeviceInfo2()
        initDeviceInfo()
    }

    @SuppressLint("SetTextI18n")
    private fun initDeviceInfo2() {
        UtilsApp.init(this.application)
        val imei = UtilsApp.getIMEI()
        val info = UtilsApp.getInfo()
        basicInfo2.text = imei + info
    }

    @SuppressLint("SetTextI18n")
    private fun initDeviceInfo() {
        val basic = SystemUtils.getSysVersionInfo(this)
        val memory = SystemUtils.getMemoInfo(this)
        val cpu = SystemUtils.getCpuInfo()
        val imei = SystemUtils.getIMEI(this)
        val uiName = SystemUtils.getSystem()
        val storage = SystemUtils.getTotalInternalMemorySize() + SystemUtils.getAvailableInternalMemorySize() +
                SystemUtils.getTotalExternalMemorySize() + SystemUtils.getAvailableExternalMemorySize()
        val sensor = SystemUtils.showSensorInfo(this)
        val mac = SystemUtils.getMacAddress(MainActivity@this)
        basicInfo.text = basic + uiName
        memoryInfo.text = memory
        imeiInfo.text = imei
        cpuInfo.text = cpu
        storageInfo.text = storage
        sensorInfo.text = sensor
        macInfo.text = mac
    }

}
