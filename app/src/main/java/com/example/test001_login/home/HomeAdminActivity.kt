// app/src/main/java/com/example/test001_login/home/HomeAdminActivity.kt
package com.example.test001_login.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.admin.SociosListActivity

class HomeAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_admin)

        findViewById<Button>(R.id.btnSocios).setOnClickListener {
            startActivity(Intent(this, SociosListActivity::class.java))
        }
    }
}
