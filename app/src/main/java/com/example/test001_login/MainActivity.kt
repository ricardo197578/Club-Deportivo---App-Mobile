package com.example.test001_login

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("username",MODE_PRIVATE);
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)

        if(isLoggedIn){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent);
            finish();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.titleDashboard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnLogin = findViewById<Button>(R.id.btnLogin);

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}