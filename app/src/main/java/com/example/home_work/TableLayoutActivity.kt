package com.example.home_work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TableLayoutActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_layout)
        
        supportActionBar?.title = "表格布局实验"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
