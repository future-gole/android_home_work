package com.example.home_work

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView // 确保导入 RecyclerView

// 移除了 View Binding 的导入

class ExperimentThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 加载 XML 布局
        setContentView(R.layout.activity_experiment_three)

        // 准备数据
        val animals = listOf(
            ListItem("Lion", R.drawable.lion),
            ListItem("Tiger", R.drawable.tiger),
            ListItem("Monkey", R.drawable.monkey),
            ListItem("Dog", R.drawable.dog),
            ListItem("Cat", R.drawable.cat),
            ListItem("Elephant", R.drawable.elephant)
        )

        // 2. 使用 findViewById 查找所有视图
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_animals)
        val btnShowDialog = findViewById<Button>(R.id.btn_show_dialog)
        val btnOpenMenu = findViewById<Button>(R.id.btn_open_menu)
        val btnOpenContextMenu = findViewById<Button>(R.id.btn_open_context_menu)

        // 3. 设置 RecyclerView
        setupRecyclerView(animals, recyclerView)

        // 4. 设置按钮点击事件
        btnShowDialog.setOnClickListener {
            showLoginDialog()
        }

        btnOpenMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        btnOpenContextMenu.setOnClickListener {
            startActivity(Intent(this, ContextActionActivity::class.java))
        }
    }

    /**
     * 辅助函数：设置 RecyclerView
     */
    private fun setupRecyclerView(animals: List<ListItem>, recyclerView: RecyclerView) {
        // 创建适配器，并传入点击回调
        val adapter = AnimalAdapter(animals) { animal ->
            // 替换自定义 Toast 为标准 Toast
            Toast.makeText(this, animal.name, Toast.LENGTH_SHORT).show()
        }

        // 使用传入的 recyclerView 变量
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 添加分割线
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    /**
     * 辅助函数：显示登录对话框 (这个函数不变)
     */
    private fun showLoginDialog() {
        // 1. 加载自定义布局 dialog_login.xml
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)

        // 2. 从自定义布局中获取 EditText
        val etUsername = dialogView.findViewById<EditText>(R.id.et_username)
        val etPassword = dialogView.findViewById<EditText>(R.id.et_password)

        // 3. 构建 AlertDialog
        AlertDialog.Builder(this)
            .setTitle("登录")
            .setView(dialogView) // 设置自定义视图
            .setPositiveButton("SIGN IN") { dialog, _ ->
                // "确定" 按钮的点击逻辑
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                val message = if (username.isNotBlank() && password.isNotBlank()) {
                    "用户名: $username\n密码: $password"
                } else {
                    "请输入用户名和密码"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                // "取消" 按钮
                dialog.dismiss()
            }
            .create()
            .show()
    }
}

