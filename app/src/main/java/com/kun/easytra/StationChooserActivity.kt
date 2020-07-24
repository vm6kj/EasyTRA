package com.kun.easytra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StationChooserActivity : AppCompatActivity() {

    private val TAG = "StationChooserActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
//        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, StationPickerFragment::class.java, null)
//                    .commit()
//        }
    }
}