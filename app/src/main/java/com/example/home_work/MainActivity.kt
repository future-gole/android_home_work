package com.example.home_work // 确保这个包名和你的项目一致

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar // 确保导入正确的 Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. 设置内容视图
        // 直接加载 R.layout.activity_main
        setContentView(R.layout.activity_main)

        // 4. 使用 findViewById 查找视图
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val btnExperimentTwo = findViewById<Button>(R.id.btn_experiment_two)
        val btnExperimentThree = findViewById<Button>(R.id.btn_experiment_three)

        // 5. 设置 Toolbar
        setSupportActionBar(toolbar)

        // 6. 设置按钮点击事件

        // "实验二" 按钮
        btnExperimentTwo.setOnClickListener {
            startActivity(Intent(this, ExperimentTwoActivity::class.java))
        }

        // "实验三" 按钮
        btnExperimentThree.setOnClickListener {
            startActivity(Intent(this, ExperimentThreeActivity::class.java))
        }
    }
}
