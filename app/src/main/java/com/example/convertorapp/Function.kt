package com.example.convertorapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//function for Small font text
@Composable
fun EnterText1(s:String){
    Text(text = s,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(2.dp),
        fontSize = 22.sp,
    )
}

//function for Large Font text
@Composable
fun EnterText2(s:String) {
    Text(text = s, modifier = Modifier.fillMaxWidth(),
        fontSize = 40.sp,
        color = Color(0xFF052DC0),
        fontWeight = FontWeight.Bold
    )
}