package com.example.myapplication

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log

class DeviceOwnerReceiver : DeviceAdminReceiver() {

    companion object {
        private const val TAG = "DeviceOwnerReceiver"
    }


    override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        Log.d(TAG, "Profile provisioning complete")
        val manager = context.getSystemService(
            Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(
            context.applicationContext, DeviceOwnerReceiver::class.java)

        try {
            manager.setProfileName(componentName,
                context.getString(R.string.profile_name))
        } catch (e: Exception) {
            Log.e(TAG, "Failed to set profile name", e)
        }

        launchMainActivity(context)
    }

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d("DeviceOwnerReceiver", "Device admin enabled")

        val manager = context.getSystemService(
            Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(
            context.applicationContext, DeviceOwnerReceiver::class.java)

        try {
            manager.setProfileName(componentName, context.getString(R.string.profile_name))
        } catch (e: Exception) {
            Log.e(TAG, "Failed to set profile name", e)
        }

        launchMainActivity(context)
    }

    private fun launchMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}