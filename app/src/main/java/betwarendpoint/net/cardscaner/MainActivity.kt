package betwarendpoint.net.cardscaner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,  Camera2BasicFragment.newInstance())
                    .commit()
//            val intent =  Intent(this,CameraPreviewActivity::class.java)
//            startActivity(intent)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.v("","")



        super.onActivityResult(requestCode, resultCode, data)
    }




}

