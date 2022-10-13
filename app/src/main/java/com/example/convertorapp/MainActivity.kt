package com.example.convertorapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convertorapp.R
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
@Composable
fun Layout1(){
    var x = remember {
        mutableStateOf(0)
    }
    //Using x for shifting in the different layouts
    when (x.value) {
        0 -> {
            Column(modifier = Modifier.fillMaxSize()) {
                TitleBar(onTap = {x.value=0})
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)

                ) {
                    // 3 Buttons in home screen
                    Button1(Modifier.weight(1f), "UNIT Converter", onTap = {
                        x.value = 1
                    })
                    Button1(Modifier.weight(1f), "CURRENCY Converter", onTap = {
                        x.value = 2
                    })
                    Button1(Modifier.weight(1f), "TIMEZONE Converter", onTap = {
                        x.value = 3
                    })
                }
            }
        }
        1 -> {
            Layout2(x)
        }
        2 -> {
            Layout3(x)
        }
        3 -> {
            Layout4(x)
        }
    }
}


//TitleBar function
@Composable
private fun TitleBar(onTap: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Gray)
        .height(70.dp)
    ) {
        Button(onClick = onTap,
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .align(alignment = Alignment.CenterVertically)
            , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Image(painter = painterResource(id = R.drawable.back_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize())
        }
        Text(text = "Converter",
            modifier = Modifier
                .weight(10f)
                .padding(top = 13.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
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

//function for Small font text
@Composable
fun EnterText1(s:String, modifier: Modifier=Modifier){
    Text(text = s,
        modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(2.dp),
        fontSize = 22.sp,
    )
}

//function for Large Font text
@Composable
private fun EnterText2(s:String) {
    Text(text = s, modifier = Modifier.fillMaxWidth(),
        fontSize = 40.sp,
        color = Color(0xFF052DC0),
        fontWeight = FontWeight.Bold
    )
}





//Layout when you click on Distance Converter Button
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout2(x:MutableState<Int>){
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
    Column(modifier = Modifier.fillMaxSize()) {

        //Use TitleBar function
        TitleBar(onTap = { x.value = 0 })
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



//Layout when Currency Converter Button clicked
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout3(x:MutableState<Int>){
    val list1 = listOf("Choose any from below","Rupees(India)","Dollar(USA)", "Pound(UK)", "Rubles(Russia)")
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
    Column(modifier = Modifier.fillMaxSize()) {

        //TitleBar
        TitleBar(onTap = {x.value = 0})
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

            //Textfield
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
                if(input==""){

                    //OnClick event
                    result= "Converted Amount"
                }
                else if(currentState1==currentState2 && currentState1 != "Choose any from below")
                    result=input
                else if(currentState1 == "Rupees(India)") {
                    when (currentState2) {
                        "Dollar(USA)" -> result=function2(0.012,input)
                        "Pound(UK)" -> result=function2(0.011,input)
                        "Rubles(Russia)" -> result=function2(0.74,input)
                    }
                }
                else if(currentState1 == "Dollar(USA)"){
                    when (currentState2) {
                        "Rupees(India)" -> result=function2(81.59,input)
                        "Pound(UK)" -> result=function2(0.88,input)
                        "Rubles(Russia)" -> result=function2(60.25,input)
                    }
                }
                else if(currentState1 == "Pound(UK)"){
                    when (currentState2) {
                        ("Rupees(India)") -> result=function2(92.53,input)
                        ("Dollar(USA)") -> result=function2(1.13,input)
                        ("Rubles(Russia)") -> result=function2(68.33,input)
                    }
                }
                else if(currentState1 == ("Rubles(Russia)")){
                    when (currentState2) {
                        ("Rupees(India)") -> result=function2(1.31,input)
                        ("Dollar(USA)") -> result=function2(0.016,input)
                        ("Pound(UK)") -> result=function2(0.014,input)
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
            EnterText1(s = "result :")
            Spacer(modifier = Modifier.height(1.dp))

            //Result value
            Text(text = result, modifier = Modifier
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
fun function2(a:Double,input:String) :String {
    return (input.toDouble() * a ).toString()
}



//Layout when Timezone Converter Button Clicked
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Layout4(x: MutableState<Int>){
    val list1 = listOf("Choose any from below","IST(UTC+05:30)","GMT(UTC+00:00)", "England,UK(UTC+01:00)", "EDT(UTC-04:00)")
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
    var day by remember {
        mutableStateOf("")
    }
    var list = mutableListOf<String>("","")
    Column(modifier = Modifier.fillMaxSize()) {

        //TitleBar
        TitleBar(onTap = {x.value = 0})
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
            Spacer(modifier = Modifier.height(20.dp))
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
            Spacer(modifier = Modifier.height(20.dp))
            val keyboard = LocalSoftwareKeyboardController.current
            OutlinedTextField(value = input,
                onValueChange = {
                    input = it
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
                if(input==""){
                    result= "Converted Amount"
                }
                else if(currentState1==currentState2 && currentState1 != "Choose any from below")
                    list= function3(0,input)
                else if(currentState1 == "IST(UTC+05:30)") {
                    when (currentState2) {
                        "GMT(UTC+00:00)" -> list=function3(-530,input)
                        "England,UK(UTC+01:00)" -> list=function3(-430,input)
                        "EDT(UTC-04:00)" -> list=function3(-930,input)
                    }
                }
                else if(currentState1 == "GMT(UTC+00:00)"){
                    when (currentState2) {
                        "IST(UTC+05:30)" -> list=function3(530,input)
                        "England,UK(UTC+01:00)" -> list=function3(100,input)
                        "EDT(UTC-04:00)" -> list=function3(-400,input)
                    }
                }
                else if(currentState1 == "England,UK(UTC+01:00)"){
                    when (currentState2) {
                        ("IST(UTC+05:30)") -> list=function3(430,input)
                        ("GMT(UTC+00:00)") -> list=function3(-100,input)
                        ("EDT(UTC-04:00)") -> list=function3(-500,input)
                    }
                }
                else if(currentState1 == ("EDT(UTC-04:00)")){
                    when (currentState2) {
                        ("IST(UTC+05:30)") -> list=function3(930,input)
                        ("GMT(UTC+00:00)") -> list=function3(400,input)
                        ("England,UK(UTC+01:00)") -> list=function3(500,input)
                    }
                }
                result=list[0]
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
            EnterText1(s = "result :")
            Spacer(modifier = Modifier.height(5.dp))

            //Result values
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFFF0F0F0))
                .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = result, modifier = Modifier.weight(1f),
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
    val list = mutableListOf<String>("Converted Value ","")
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
            if(b + b1<60){
                a1+=a-2400
            }
            else {
                a1+=a-2360
            }
        }
    }
    else {
        if(a+a1>0){
            list[1]="today"
            var b=a%100
            b=b+(-2*b)
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

