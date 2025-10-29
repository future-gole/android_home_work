package com.example.home_work

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConstraintLayout1Activity : AppCompatActivity() {
    
    private lateinit var displayText: TextView
    private var currentInput = "0.0"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout1)
        
        supportActionBar?.title = "约束布局实验1 - 计算器"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        displayText = findViewById(R.id.tv_display)
        
        // 数字按钮
        val numberButtons = arrayOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
            R.id.btn_8, R.id.btn_9
        )
        
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val number = (it as Button).text.toString()
                if (currentInput == "0.0") {
                    currentInput = number
                } else {
                    currentInput += number
                }
                displayText.text = currentInput
            }
        }
        
        // 运算符按钮
        findViewById<Button>(R.id.btn_add).setOnClickListener { }
        findViewById<Button>(R.id.btn_subtract).setOnClickListener { }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { }
        findViewById<Button>(R.id.btn_divide).setOnClickListener { }
        
        // 小数点
        findViewById<Button>(R.id.btn_dot).setOnClickListener {
            if (!currentInput.contains(".")) {
                currentInput += "."
                displayText.text = currentInput
            }
        }
        
        // 等号
        findViewById<Button>(R.id.btn_equals).setOnClickListener {
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
