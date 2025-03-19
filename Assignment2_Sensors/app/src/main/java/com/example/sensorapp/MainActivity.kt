package com.example.sensorapp

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.sensorapp.ui.theme.SensorAppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen1()
            SensorAppTheme {
                Scaffold { innerPadding -> // Scaffold expects a lambda
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), // Apply scaffold padding
                        color = Color(0xFFbfe3d0)
                    ){

                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color(0xFF222b42))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {}
            }
            Row{}
            Row{}

        }
    }
}

@Composable
fun ContactBox(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(
            start = 10.dp,
            top = 40.dp,
            end = 10.dp,
            bottom = 40.dp
        ),
        contentAlignment = Alignment.BottomCenter){
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Column (Modifier
                    .weight(1.5f)
                    .padding(end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End){
                    //PhoneIcon()
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(Modifier.weight(4f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 10.dp,
                                end = 0.dp,
                                bottom = 10.dp
                            ),
                        color = Color.Black
                    )
                }


            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Column(Modifier
                    .weight(1.5f)
                    .padding(end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End) {
                    ShareIcone()
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(Modifier.weight(4f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start)  {
                    Text(
                        text = "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 10.dp,
                                end = 0.dp,
                                bottom = 10.dp
                            ),
                        color = Color.Black
                    )
                }



            }

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Column(Modifier
                    .weight(1.5f)
                    .padding(end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End) {

                    EmailIcon()
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(Modifier.weight(4f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 10.dp,
                                end = 0.dp,
                                bottom = 10.dp
                            ),
                        color = Color.Black
                    )
                }



            }
        }

    }
}



@Composable
fun PhoneIcon(){
    Icon(
        imageVector = Icons.Filled.Phone,
        contentDescription = "Phone Icon",
        tint = Color(0xFF268755),
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun ShareIcone(){
    Icon(
        imageVector = Icons.Filled.Share,
        contentDescription = "Share Icon",
        tint = Color(0xFF268755),
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun EmailIcon(){
    Icon(
        imageVector = Icons.Filled.Email,
        contentDescription = "Email Icon",
        tint = Color(0xFF268755),
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun MainScreen1() {
    val context = LocalContext.current

    // Create the file as soon as the Composable is launched
    LaunchedEffect(Unit) {
        try {
            val file = File(context.filesDir, "example.txt")
            file.writeText("Sample data from Jetpack Compose.")
            Log.d("FileCheck", "File created: ${file.absolutePath}")

            val file2 = File(context.filesDir, "example2.txt")
            file2.writeText("Sample data for second file.")
            Log.d("FileCheck", "File created: ${file2.absolutePath}")

            val file3 = File(context.filesDir, "example3.txt")
            file2.writeText("Sample data for second file.")
            Log.d("FileCheck", "File created: ${file3.absolutePath}")
        }
        catch (e: Exception) {
            Log.e("FileCheck", "Error creating file", e)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SensorAppTheme {

    }
}