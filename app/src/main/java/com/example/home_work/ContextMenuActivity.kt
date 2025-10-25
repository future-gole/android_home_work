package com.example.home_work

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_work.ui.theme.Home_workTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay

class ContextMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home_workTheme {
                ContextMenuScreen(onBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ContextMenuScreen(onBack: () -> Unit) {
    val labels = listOf("One", "Two", "Three", "Four", "Five", "Six")
    val selectedIndexes = remember { mutableStateListOf<Int>() }

    // Toast 状态
    var toastMessage by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }

    val isSelectionMode = selectedIndexes.isNotEmpty()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // 显示 Toast 的函数
    fun showMessage(message: String) {
        toastMessage = message
        showToast = true
    }

    fun toggleSelection(index: Int) {
        if (selectedIndexes.contains(index)) {
            selectedIndexes.remove(index)
        } else {
            selectedIndexes.add(index)
        }
    }

    fun clearSelection() {
        selectedIndexes.clear()
    }

    fun selectedLabels(): List<String> = selectedIndexes.sorted().map { labels[it] }

    Box(modifier = Modifier.fillMaxSize()) {  // ✅ 新增 Box
        Scaffold(
            topBar = {
                if (isSelectionMode) {
                    ActionModeBar(
                        count = selectedIndexes.size,
                        onClose = { clearSelection() },
                        onEdit = {
                            showMessage("编辑: ${selectedLabels().joinToString()}")  // ✅ 改
                            clearSelection()
                        },
                        onDelete = {
                            showMessage("删除: ${selectedLabels().joinToString()}")  // ✅ 改
                            clearSelection()
                        },
                        onShare = {
                            showMessage("分享: ${selectedLabels().joinToString()}")  // ✅ 改
                            clearSelection()
                        }
                    )
                } else {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "上下文菜单") },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "返回"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                itemsIndexed(labels) { index, label ->
                    val selected = selectedIndexes.contains(index)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (selected) MaterialTheme.colorScheme.secondaryContainer
                                else Color.Transparent
                            )
                            .combinedClickable(
                                onClick = {
                                    if (isSelectionMode) {
                                        toggleSelection(index)
                                    } else {
                                        showMessage(label)  // ✅ 改
                                    }
                                },
                                onLongClick = {
                                    toggleSelection(index)
                                }
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_android_logo),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = label,
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // 自定义 Toast UI
        AnimatedVisibility(
            visible = showToast,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            enter = fadeIn() + slideInVertically { it / 2 },
            exit = fadeOut() + slideOutVertically { it / 2 }
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.inverseSurface,
                tonalElevation = 6.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = toastMessage,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // 自动隐藏
        LaunchedEffect(showToast) {
            if (showToast) {
                delay(2000)
                showToast = false
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActionModeBar(
    count: Int,
    onClose: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "$count selected", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "取消选择", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "编辑", tint = Color.White)
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "删除", tint = Color.White)
            }
            IconButton(onClick = onShare) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "分享", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF00796B),
            titleContentColor = Color.White
        )
    )
}