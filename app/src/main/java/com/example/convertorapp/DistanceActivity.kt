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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convertorapp.ui.theme.ConvertorAppTheme

class DistanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.White) {
                        Layout2()
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout2(){
    val list1 = listOf("Choose any from below","centimeter","meter", "kilometer", "miles")
    val expanded1 = remember {
        mutableStateOf(false)
    }
    val expanded2 = remember {
        mutableStateOf(false)
    }
    var currentState1 by remember {
        mutableStateOf(list1[0])
    }
    var currentState2 by remember {
        mutableStateOf(list1[0])
    }
    var result by remember {
        mutableStateOf("Converted Value")
    }
    var input by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {

        //Use TitleBar function
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
                //Text function
                EnterText2(s = "Distance" )
                EnterText2(s = "Converter")
            }
            Spacer(modifier = Modifier.height(15.dp))
            EnterText1(s = "From")
            Spacer(modifier = Modifier.height(1.dp))

            //Spinner for From option
            Row(modifier = Modifier.fillMaxWidth()){

                Text(text = currentState1 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded1.value = !expanded1.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded1.value, onDismissRequest = {
                    expanded1.value =false
                }) {
                    list1.forEach {
                        DropdownMenuItem(onClick = {
                            currentState1 =it
                            expanded1.value=false
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            EnterText1(s = "To")
            Spacer(modifier = Modifier.height(1.dp))

            //spinner for To options
            Row(modifier = Modifier.fillMaxWidth()){

                Text(text = currentState2 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded2.value = !expanded2.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded2.value, onDismissRequest = {
                    expanded2.value =false
                }) {
                    list1.forEach {
                        DropdownMenuItem(onClick = {
                            currentState2 =it
                            expanded2.value=false
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            val keyboard = LocalSoftwareKeyboardController.current

            //TextField for Entering values
            OutlinedTextField(value = input,
                onValueChange = {
                    input = it
                }, label = { Text(text = "Enter value...")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xffffffff))
                    .padding(2.dp)
                    .height(60.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboard?.hide()
                }))
            Spacer(modifier = Modifier.height(35.dp))
            Button(onClick = {

                //OnClick events
                if(input==""){
                    result= "Converted Amount"
                }
                else if(currentState1==currentState2 && currentState1 != "Choose any from below")
                    result=input
                else if(currentState1 == "centimeter") {
                    when (currentState2) {
                        "meter" -> result=function1(0.01,input)
                        "kilometer" -> result=function1(0.00001,input)
                        "miles" -> result=function1(0.0000062137,input)
                    }
                }
                else if(currentState1 == "meter"){
                    when (currentState2) {
                        "centimeter" -> result=function1(100.0,input)
                        "kilometer" -> result=function1(0.001,input)
                        "miles" -> result=function1(0.00062137,input)
                    }
                }
                else if(currentState1 == "kilometer"){
                    when (currentState2) {
                        ("centimeter") -> result=function1(100000.0,input)
                        ("meter") -> result=function1(1000.0,input)
                        ("miles") -> result=function1(0.62137,input)
                    }
                }
                else if(currentState1 == ("miles")){
                    when (currentState2) {
                        ("centimeter") -> result=function1(160934.0,input)
                        ("meter") -> result=function1(1609.34,input)
                        ("kilometer") -> result=function1(1.60934,input)
                    }
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .height(50.dp)
                , colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2DD491))
            ) {
                Text(text = "Convert", modifier = Modifier, color = Color.White, fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(25.dp))
            EnterText1(s = "result :")
            Spacer(modifier = Modifier.height(1.dp))

            //Result
            Text(text = result, modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xFFF0F0F0))
                .padding(2.dp),
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
//function
fun function1(a:Double,input:String):String{
    return (input.toDouble() * a ).toString()
}