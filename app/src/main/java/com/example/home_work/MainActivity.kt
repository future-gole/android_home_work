package com.example.home_work

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_work.ui.theme.Home_workTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home_workTheme {
                MainScreen(
                    onOpenMenu = { startActivity(Intent(this, MenuActivity::class.java)) },
                    onOpenContextMenu = { startActivity(Intent(this, ContextMenuActivity::class.java)) }
                )
            }
        }
    }
}

@Composable
private fun MainScreen(
    onOpenMenu: () -> Unit,
    onOpenContextMenu: () -> Unit
) {
    val animals = remember {
        listOf(
            ListItem("Lion", R.drawable.lion),
            ListItem("Tiger", R.drawable.tiger),
            ListItem("Monkey", R.drawable.monkey),
            ListItem("Dog", R.drawable.dog),
            ListItem("Cat", R.drawable.cat),
            ListItem("Elephant", R.drawable.elephant)
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // ✅ 添加自定义 Toast 状态
    var toastMessage by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }

    if (showDialog) {
        LoginDialog(
            username = username,
            password = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },
            onDismiss = { showDialog = false },
            onConfirm = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    toastMessage = "用户名: $username\n密码: $password"
                    showToast = true
                    username = ""
                    password = ""
                } else {
                    toastMessage = "请输入用户名和密码"
                    showToast = true
                }
                showDialog = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(animals) { index, item ->
                        AnimalRow(item = item) {
                            toastMessage = item.name
                            showToast = true
                        }
                        if (index != animals.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { showDialog = true }
                    ) {
                        Text(text = "显示对话框")
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onOpenMenu
                    ) {
                        Text(text = "打开菜单示例")
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onOpenContextMenu
                    ) {
                        Text(text = "打开上下文菜单")
                    }
                }
            }
        }

        // ✅ 自定义 Toast
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
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }

        LaunchedEffect(showToast) {
            if (showToast) {
                delay(2000)
                showToast = false
            }
        }
    }
}

@Composable
private fun AnimalRow(item: ListItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.name,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
private fun LoginDialog(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "登录") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = username,
                    onValueChange = onUsernameChange,
                    label = { Text(text = "Username") },
                    placeholder = { Text(text = "请输入用户名") }
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "输入密码") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "SIGN IN")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "CANCEL")
            }
        }
    )
}