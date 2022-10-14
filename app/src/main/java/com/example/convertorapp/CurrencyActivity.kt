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

class CurrencyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize().background(color = Color.White),
                    color = Color(0xffffffff)) {
                    Layout3()
                }
            }
        }
    }
}

//Layout when Currency Converter Button clicked
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout3(){
    val list2 = listOf("Choose any from below","Rupees(India)","Dollar(USA)", "Pound(UK)", "Rubles(Russia)")
    val expanded3 = remember {
        mutableStateOf(false)
    }
    val expanded4 = remember {
        mutableStateOf(false)
    }
    var currentState3 by remember {
        mutableStateOf(list2[0])
    }
    var currentState4 by remember {
        mutableStateOf(list2[0])
    }
    var result2 by remember {
        mutableStateOf("Converted Value")
    }
    var input2 by remember {
        mutableStateOf("")
    }
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
                EnterText2(s = "Currency" )
                EnterText2(s = "Converter")
            }
            Spacer(modifier = Modifier.height(15.dp))
            EnterText1(s = "From")
            Spacer(modifier = Modifier.height(1.dp))

            //Spinner for Form option
            Row(modifier = Modifier.fillMaxWidth()){

                Text(text = currentState3 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded3.value = !expanded3.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded3.value, onDismissRequest = {
                    expanded3.value =false
                }) {
                    list2.forEach {
                        DropdownMenuItem(onClick = {
                            currentState3 =it
                            expanded3.value=false
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

                Text(text = currentState4 , modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xfff0f0f0))
                    .clickable {
                        expanded4.value = !expanded4.value
                    }
                    .padding(5.dp),
                    fontSize = 20.sp, color = Color.Black
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded4.value, onDismissRequest = {
                    expanded4.value =false
                }) {
                    list2.forEach {
                        DropdownMenuItem(onClick = {
                            currentState4 =it
                            expanded4.value=false
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            val keyboard = LocalSoftwareKeyboardController.current

            //Textfield
            OutlinedTextField(value = input2,
                onValueChange = {
                    input2 = it
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
                if(input2==""){

                    //OnClick event
                    result2= "Converted Amount"
                }
                else if(currentState3==currentState4 && currentState3 != "Choose any from below")
                    result2=input2
                else if(currentState3 == "Rupees(India)") {
                    when (currentState4) {
                        "Dollar(USA)" -> result2=function2(0.012,input2)
                        "Pound(UK)" -> result2=function2(0.011,input2)
                        "Rubles(Russia)" -> result2=function2(0.74,input2)
                    }
                }
                else if(currentState3 == "Dollar(USA)"){
                    when (currentState4) {
                        "Rupees(India)" -> result2=function2(81.59,input2)
                        "Pound(UK)" -> result2=function2(0.88,input2)
                        "Rubles(Russia)" -> result2=function2(60.25,input2)
                    }
                }
                else if(currentState3 == "Pound(UK)"){
                    when (currentState4) {
                        ("Rupees(India)") -> result2=function2(92.53,input2)
                        ("Dollar(USA)") -> result2=function2(1.13,input2)
                        ("Rubles(Russia)") -> result2=function2(68.33,input2)
                    }
                }
                else if(currentState3 == ("Rubles(Russia)")){
                    when (currentState4) {
                        ("Rupees(India)") -> result2=function2(1.31,input2)
                        ("Dollar(USA)") -> result2=function2(0.016,input2)
                        ("Pound(UK)") -> result2=function2(0.014,input2)
                    }
                }
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
            EnterText1(s = "result2 :")
            Spacer(modifier = Modifier.height(1.dp))

            //result2 value
            Text(text = result2, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFFF0F0F0))
                .padding(2.dp),
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
//function
fun function2(a:Double,input2:String) :String {
    return (input2.toDouble() * a ).toString()
}

