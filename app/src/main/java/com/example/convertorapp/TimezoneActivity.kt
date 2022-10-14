package com.example.convertorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convertorapp.ui.theme.ConvertorAppTheme

class TimezoneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Layout4()
                }
            }
        }
    }
}

//Layout when Timezone Converter Button Clicked
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout4(){
    val list3 = listOf("Choose any from below","IST(UTC+05:30)","GMT(UTC+00:00)", "England,UK(UTC+01:00)", "EDT(UTC-04:00)")
    val expanded5 = remember {
        mutableStateOf(false)
    }
    val expanded6 = remember {
        mutableStateOf(false)
    }
    var currentState5 by remember {
        mutableStateOf(list3[0])
    }
    var currentState6 by remember {
        mutableStateOf(list3[0])
    }
    var result3 by remember {
        mutableStateOf("Converted Value")
    }
    var input3 by remember {
        mutableStateOf("")
    }
    var day by remember {
        mutableStateOf("")
    }
    var list = mutableListOf("","")
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {

        //TitleBar
        Text(text = "Converter",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Gray)
            ,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
            ) {
                EnterText2(s = "Timezone" )
                EnterText2(s = "Converter")
            }
            Spacer(modifier = Modifier.height(15.dp))
            EnterText1(s = "From")
            Spacer(modifier = Modifier.height(1.dp))

            //Spinner for From Option
            Row(modifier = Modifier.fillMaxWidth()){

                Text(text = currentState5 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded5.value = !expanded5.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded5.value, onDismissRequest = {
                    expanded5.value =false
                }) {
                    list3.forEach {
                        DropdownMenuItem(onClick = {
                            currentState5 =it
                            expanded5.value=false
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            EnterText1(s = "To")
            Spacer(modifier = Modifier.height(1.dp))

            //spinner for To options
            Row(modifier = Modifier.fillMaxWidth()){

                Text(text = currentState6 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded6.value = !expanded6.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded6.value, onDismissRequest = {
                    expanded6.value =false
                }) {
                    list3.forEach {
                        DropdownMenuItem(onClick = {
                            currentState6 =it
                            expanded6.value=false
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            val keyboard = LocalSoftwareKeyboardController.current
            OutlinedTextField(value = input3,
                onValueChange = {
                    input3 = it
                }, label = { Text(text = "Enter Time in HH:MM format")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xffffffff))
                    .padding(2.dp)
                    .height(60.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboard?.hide()
                }),


                )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {

                //OnClick Event
                if(input3==""){
                    result3= "Converted Amount"
                }
                else if(currentState5==currentState6 && currentState5 != "Choose any from below")
                    list= function3(0,input3)
                else if(currentState5 == "IST(UTC+05:30)") {
                    when (currentState6) {
                        "GMT(UTC+00:00)" -> list=function3(-530,input3)
                        "England,UK(UTC+01:00)" -> list=function3(-430,input3)
                        "EDT(UTC-04:00)" -> list=function3(-930,input3)
                    }
                }
                else if(currentState5 == "GMT(UTC+00:00)"){
                    when (currentState6) {
                        "IST(UTC+05:30)" -> list=function3(530,input3)
                        "England,UK(UTC+01:00)" -> list=function3(100,input3)
                        "EDT(UTC-04:00)" -> list=function3(-400,input3)
                    }
                }
                else if(currentState5 == "England,UK(UTC+01:00)"){
                    when (currentState6) {
                        ("IST(UTC+05:30)") -> list=function3(430,input3)
                        ("GMT(UTC+00:00)") -> list=function3(-100,input3)
                        ("EDT(UTC-04:00)") -> list=function3(-500,input3)
                    }
                }
                else if(currentState5 == ("EDT(UTC-04:00)")){
                    when (currentState6) {
                        ("IST(UTC+05:30)") -> list=function3(930,input3)
                        ("GMT(UTC+00:00)") -> list=function3(400,input3)
                        ("England,UK(UTC+01:00)") -> list=function3(500,input3)
                    }
                }
                result3=list[0]
                day=list[1]
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .height(60.dp)
                , colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2DD491))
            ) {
                Text(text = "Convert", modifier = Modifier, color = Color.White, fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(25.dp))
            EnterText1(s = "result3 :")
            Spacer(modifier = Modifier.height(5.dp))

            //result3 values
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFFF0F0F0))
                .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = result3, modifier = Modifier.weight(1f),
                    fontSize = 26.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(text = day, modifier = Modifier.weight(1f),
                    fontSize = 28.sp,
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}
//function
fun function3(a:Int,s:String):MutableList<String>{
    val list = mutableListOf("Converted Value ","")
    val h: Int = s[0].digitToInt()*10 + s[1].digitToInt()
    val m: Int=s[3].digitToInt()*10 + s[4].digitToInt()

    if(s[2]!=':' || s.length != 5 ||m>=60 || h>23){
        list[0]="wrong"
        list[1]="format"
        return list
    }
    var a1 = h*100+m
    if(a>=0){
        if(a+a1<2360){
            list[1]= "today"
            a1+=a
            val a2=a1%100
            if(a2/60==1)
                a1+=40
        }
        else {
            list[1]="tomorrow"
            val b=a%100
            val b1=a1%100
            a1 += if(b + b1<60){
                a-2400
            } else {
                a-2360
            }
        }
    }
    else {
        if(a+a1>0){
            list[1]="today"
            var b=a%100
            b += (-2 * b)
            val b1=a1%100
            if(b<=b1){
                a1+=a
            }
            else{
                a1+=a
                a1-=40
            }
            val a2=a1%100
            if(a2/60==1)
                a1+=40
        }
        else{
            list[1]="last day"
            var b=a%100
            b += (-2 * b)
            val b1=a1%100
            a1 += if(b<=b1){
                a + 2360+40
            } else {
                a+2360
            }
            val a2=a1%100
            if(a2/60==1)
                a1+=40
        }
    }
    //change again to output format
    val a3=a1%100
    val a4=a1/100
    if(a3<10 && a4<10)
        list[0]="0$a4:0$a3"
    else if(a3>=10&&a4<10)
        list[0]="0$a4:$a3"
    else if(a3>=10)
        list[0]="$a4:$a3"
    else
        list[0]="$a4:0${a3}"
    return list
}
