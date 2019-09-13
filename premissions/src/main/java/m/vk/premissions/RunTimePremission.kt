package m.vk.premissions

import android.content.Context
import android.content.pm.PackageManager

import androidx.core.content.ContextCompat

import java.util.ArrayList

abstract class RunTimePremission : StatedFragment() {

    abstract fun onPermissionsGranted(requestCode: Int, status: Int, lisPermiss: List<ModelPremiss>)

    fun requestAppPermissions(
        mContext: Context,
        requestedPermissions: Array<String>, /*final int stringId,*/
        requestCode: Int
    ) {
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        var showRequestPermissions = false
        val arrPermiss = ArrayList<ModelPremiss>()
        for (permission in requestedPermissions) {
            permissionCheck += ContextCompat.checkSelfPermission(mContext, permission)
            showRequestPermissions =
                showRequestPermissions || shouldShowRequestPermissionRationale(permission)

            val permiss = ModelPremiss(requestCode,ContextCompat.checkSelfPermission(mContext, permission),permission)
            arrPermiss.add(permiss)

            //Log.e("Check 1",permission + " : " + ContextCompat.checkSelfPermission(mContext, permission));
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (showRequestPermissions) {//ทำงานครั้งต่อไปเมื่อไม่มีการอนุญาต
                //onPermissionsGranted(requestCode,STATUS_NO_GRANTED);
                requestPermissions(requestedPermissions, requestCode)
            } else {//ทำงานครั้งแรกครั้งเดียว
                //requestPermissions(requestedPermissions, requestCode);
                requestPermissions(requestedPermissions, requestCode)
            }
        } else {//ถ้าได้กดอนุญาติแล้ว
            onPermissionsGranted(requestCode, STATUS_GRANTED, arrPermiss)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        val arrPermiss = ArrayList<ModelPremiss>()

        var i = 0
        for (permission in grantResults) {
            permissionCheck += permission
            //Log.e("Check 2",permissions[i] + " : " + grantResults[i]);
            val permiss = ModelPremiss(requestCode,grantResults[i],permissions[i])
            arrPermiss.add(permiss)
            i++
        }

        if (grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED == permissionCheck) {//กดอนุญาต
            onPermissionsGranted(requestCode, STATUS_GRANTED, arrPermiss)
        } else {//กดไม่อนุญาต
            onPermissionsGranted(requestCode, STATUS_NO_GRANTED, arrPermiss)
            //Display message when contain some Dangerous permission not accept
        }
    }

    companion object {
        var STATUS_GRANTED = 1
        var STATUS_NO_GRANTED = 0
    }
}

