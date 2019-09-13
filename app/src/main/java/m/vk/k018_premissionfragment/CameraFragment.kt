package m.vk.k018_premissionfragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import m.vk.k018_premissionfragment.databinding.FragmentCameraBinding
import m.vk.premissions.ModelPremiss
import m.vk.premissions.RunTimePremission

class CameraFragment : RunTimePremission() {

    private var RESULT_SINGLE_REQUEST = 100
    private var RESULT_MULTIPLE_REQUEST = 200

    private lateinit var binding : FragmentCameraBinding
    companion object {
        @JvmStatic
        fun newInstance() =
            CameraFragment().apply {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_camera,container , false)
        return binding.root
    }
    override fun onPermissionsGranted(requestCode: Int,status: Int,lisPermiss: List<ModelPremiss>) {
        if (requestCode == RESULT_SINGLE_REQUEST){
            if (status == STATUS_GRANTED){
                onShowMsg("Allow Single Permission Success.")
            }else{
                onShowMsg("User Deny.")
            }
        }else if (requestCode == RESULT_MULTIPLE_REQUEST){
            if (status == STATUS_GRANTED){
                onShowMsg("Allow Multiple Permission Success.")
            }else{
                onShowMsg("User Deny.")
            }
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnSinglePermission.setOnClickListener {
            requestAppPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                RESULT_SINGLE_REQUEST)
        }

        binding.btnMultiPermission.setOnClickListener{
            requestAppPermissions(
                activity!!,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                RESULT_MULTIPLE_REQUEST)
        }
    }
    private fun onShowMsg(msg: String){
        Toast.makeText(activity!!,"$msg",Toast.LENGTH_LONG).show()
    }
}
