package com.example.home_work

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ExperimentTwoActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiment_two)
        
        // 线性布局实验
        findViewById<Button>(R.id.btn_linear_layout).setOnClickListener {
            startActivity(Intent(this, LinearLayoutActivity::class.java))
        }
        
        // 表格布局实验
        findViewById<Button>(R.id.btn_table_layout).setOnClickListener {
            startActivity(Intent(this, TableLayoutActivity::class.java))
        }
        
        // 约束布局1实验
        findViewById<Button>(R.id.btn_constraint_layout1).setOnClickListener {
            startActivity(Intent(this, ConstraintLayout1Activity::class.java))
        }
        
        // 约束布局2实验
        findViewById<Button>(R.id.btn_constraint_layout2).setOnClickListener {
            startActivity(Intent(this, ConstraintLayout2Activity::class.java))
        }
    }
}