package com.example.home_work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LinearLayoutActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)
        
        supportActionBar?.title = "线性布局实验"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
