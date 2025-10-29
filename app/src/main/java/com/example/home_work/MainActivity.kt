package com.example.home_work

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.home_work.ui.theme.Home_workTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home_workTheme {
                HomeScreen(
                    onOpenExperimentTwo = {
                        startActivity(Intent(this, ExperimentTwoActivity::class.java))
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(onOpenExperimentTwo: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "课程实验") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "请选择要进入的实验", style = MaterialTheme.typography.titleLarge)

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onOpenExperimentTwo
            ) {
                Text(text = "实验二")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "实验三敬请期待", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "实验三")
            }
        }
    }
}