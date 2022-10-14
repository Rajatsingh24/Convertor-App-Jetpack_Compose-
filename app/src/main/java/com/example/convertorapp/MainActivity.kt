package com.example.convertorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convertorapp.ui.theme.ConvertorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertorAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Layout1()
                    Toast.makeText(this, "choose any Converter", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


//Home screen
@Preview
@Composable
fun Layout1(){
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)){
        Text(text = "Converter",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Gray),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ) {
            // 3 Buttons in home screen
            Button1(Modifier.weight(1f), "UNIT Converter", onTap = {
                context.startActivity(Intent(context,DistanceActivity::class.java))
            })
            Button1(Modifier.weight(1f), "CURRENCY Converter", onTap = {
                context.startActivity(Intent(context,CurrencyActivity::class.java))
            })
            Button1(Modifier.weight(1f), "TIMEZONE Converter", onTap = {
                context.startActivity(Intent(context,TimezoneActivity::class.java))
            })
        }
    }
}

//Button function home Button
@Composable
private fun Button1(modifier: Modifier,text1: String,onTap : () ->Unit) {
    Button(onClick = onTap,
        modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xffD3D3D3)
        )
    ) {
        Text(
            text = text1 ,
            modifier = Modifier
                .wrapContentSize(),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }
}
