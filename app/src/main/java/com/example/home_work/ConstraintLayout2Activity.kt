package com.example.home_work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ConstraintLayout2Activity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout2)
        
        supportActionBar?.title = "约束布局实验2 - 太空旅行"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
