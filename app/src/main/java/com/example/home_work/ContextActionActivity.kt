package com.example.home_work

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ContextActionActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ContextActionAdapter
    private lateinit var toolbar: MaterialToolbar

    private val myData = mutableListOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_action)

        // 设置 Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 设置 ListView
        listView = findViewById(R.id.context_action_listview)
        adapter = ContextActionAdapter(this, myData)
        listView.adapter = adapter

        setupMultiChoiceModeListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupMultiChoiceModeListener() {
        listView.setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                // 加载上下文菜单
                val inflater = mode.menuInflater
                inflater.inflate(R.menu.menu_context_action, menu)
                return true
            }

            override fun onItemCheckedStateChanged(
                mode: ActionMode,
                position: Int,
                id: Long,
                checked: Boolean
            ) {
                // 更新标题显示选中的项目数
                val checkedCount = listView.checkedItemCount
                mode.title = "$checkedCount selected"
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_delete_context -> {
                        deleteSelectedItems()
                        mode.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                // 清理工作
            }
        })
    }

    private fun deleteSelectedItems() {
        val selectedPositions: SparseBooleanArray = listView.checkedItemPositions
        val itemsToDelete = mutableListOf<String>()

        // 收集要删除的项目
        for (i in 0 until adapter.count) {
            if (selectedPositions.get(i)) {
                val item = adapter.getItem(i)
                if (item != null) {
                    itemsToDelete.add(item)
                }
            }
        }

        // 删除收集的项目
        for (item in itemsToDelete) {
            adapter.remove(item)
        }

        adapter.notifyDataSetChanged()
        Toast.makeText(this, "已删除 ${itemsToDelete.size} 项", Toast.LENGTH_SHORT).show()
    }
}