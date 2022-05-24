package com.example.testcomposematerial3.activities

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcomposematerial3.ui.theme.btnSignIn
import com.example.testcomposematerial3.ui.theme.frstGradient
import com.example.testcomposematerial3.ui.theme.scndGradient
import com.google.accompanist.pager.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Column(modifier = Modifier.fillMaxSize()) {
                    // A surface container using the 'background' color from the theme
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        frstGradient,
                                        scndGradient
                                    )
                                )
                            )
                    ) {
                        WelcomePager()
                        Column(modifier = Modifier){
//                            .align(Alignment.TopStart)
//                            .padding(start = 10.dp, top = 30.dp, end = 25.dp)) {

//                            Button(onClick = { startActivity(Intent(this@MainActivity, SignInActivity::class.java)) }) {
//                                Text(text = "SignIn", fontSize = 25.sp)
//                            }
//                            Button(onClick = { startActivity(Intent(this@MainActivity, SignUpActivity::class.java)) }) {
//                                Text(text = "SignUp", fontSize = 25.sp)
//                            }
                        }
//                        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
//                            ImageDemonstrate()
//                        }

                    }
                }

        }
    }
}
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomePager() {
    val context = LocalContext.current
        val pagerState = rememberPagerState()
        val description = arrayOf("The best app for manage your finance",
            "Simple and easy to control your money", "Your savings are safe by smart invest")
        val drawables = arrayOf(R.drawable.money, R.drawable.maninmoney, R.drawable.contractpaper)
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        HorizontalPager(
            count = 3,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            verticalAlignment = Alignment.Top, state = pagerState
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            )
            {
                WelcomeLayout(
                    title = description[it],
                    text = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient."
                )
            }
            if (it == 2) {
                Column(Modifier.fillMaxSize().padding(start = 20.dp, bottom = 150.dp), verticalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        context.startActivity(
                            Intent(
                                context,
                                SignInActivity::class.java
                            )
                        )
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = btnSignIn, contentColor = White),
                        modifier = Modifier.padding(bottom = 20.dp)) {
                        Text(text = "SignIn", fontSize = 25.sp)
                    }
                    Button(onClick = {
                        context.startActivity(
                            Intent(
                                context,
                                SignUpActivity::class.java
                            )
                        )
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = btnSignIn, contentColor = White)) {
                        Text(text = "SignUp", fontSize = 25.sp)
                    }
                }
            }
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
                ImageDemonstrate(drawables[it])
            }

        }
    }

    Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
        HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier
            .padding(top = 270.dp, start = 20.dp), activeColor = White
        )
    }


}

@Composable
fun WelcomeLayout(title: String, text: String) {
    Text(text = title, fontSize = 45.sp, fontWeight = FontWeight.Bold, lineHeight = 45.sp, color = White)
    Text(text = text, fontSize = 15.sp, color = Gray)
}
@Composable
fun ImageDemonstrate(drawable: Int) {
    Image(bitmap = ImageBitmap.imageResource(id = drawable), contentDescription = "WOmAN")
}
