package m.vk.k018_premissionfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var TAG_CameraFragment : String = "TagCameraFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
            CameraFragment.newInstance(),
            TAG_CameraFragment)/*.addToBackStack(TAG_CameraFragment)*/.commit()
    }
}
