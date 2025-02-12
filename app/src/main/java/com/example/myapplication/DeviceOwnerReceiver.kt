package com.example.myapplication

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.getSystemService

class DeviceOwnerReceiver : DeviceAdminReceiver() {

    @Override
    override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(context.applicationContext, DeviceOwnerReceiver::class.java)

        manager.setProfileName(componentName, context.getString(R.string.profile_name))

        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}