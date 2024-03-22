package com.newspaperdirect.example.samplesdk

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.newspaperdirect.pressreader.android.oem.Main

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)
        findViewById<View>(R.id.button_start).setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                Main::class.java
            )
            startActivity(intent)
        }
    }
}
