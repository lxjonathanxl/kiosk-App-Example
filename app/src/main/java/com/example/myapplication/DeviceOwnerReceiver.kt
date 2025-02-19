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
        Log.d(TAG, "Profile provisioning complete!")
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

        allowlistApp(context, manager, componentName)

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

    private fun allowlistApp(context: Context, manager: DevicePolicyManager, componentName: ComponentName) {
        // Check if the app is already allowlisted
        val isAlreadyAllowlisted = manager.getLockTaskPackages(componentName).contains(context.packageName)
        if (!isAlreadyAllowlisted) {
            // Allowlist the app
            manager.setLockTaskPackages(componentName, arrayOf(context.packageName))
            Log.d(TAG, "App allowlisted for lock task mode")
        } else {
            Log.d(TAG, "App already allowlisted for lock task mode")
        }
    }
}