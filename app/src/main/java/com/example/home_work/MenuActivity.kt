package com.example.home_work

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_work.ui.theme.Home_workTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home_workTheme {
                MenuScreen(onBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var fontSize by remember { mutableStateOf(16.sp) }
    var sampleColor by remember { mutableStateOf(Color.Black) }

    val showToast: (String) -> Unit = {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "MenuTest") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "菜单")
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        Text(
                            text = "字体大小",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        DropdownMenuItem(
                            text = { Text("小") },
                            onClick = {
                                fontSize = 10.sp
                                showToast("字体：小")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("中") },
                            onClick = {
                                fontSize = 16.sp
                                showToast("字体：中")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("大") },
                            onClick = {
                                fontSize = 20.sp
                                showToast("字体：大")
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("普通菜单项") },
                            onClick = {
                                showToast("普通菜单项被点击")
                                expanded = false
                            }
                        )

                        Text(
                            text = "字体颜色",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        DropdownMenuItem(
                            text = { Text("红色") },
                            onClick = {
                                sampleColor = Color.Red
                                showToast("颜色：红色")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("黑色") },
                            onClick = {
                                sampleColor = Color.Black
                                showToast("颜色：黑色")
                                expanded = false
                            }
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "用于测试的内容",
                fontSize = fontSize,
                color = sampleColor,
                textAlign = TextAlign.Center
            )
        }
    }
}