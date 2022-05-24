package com.example.testcomposematerial3.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcomposematerial3.ui.theme.btnSignIn
import com.example.testcomposematerial3.ui.theme.textLinkSignIn

val loginTextField = mutableStateOf("")
val passwordTextField =  mutableStateOf("")

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(android.graphics.Color.WHITE)) )
                {
                    SignIn()
                }
            }
        }
    }
}

@Composable
fun SignIn() {
    val context = LocalContext.current
    val passwordVisible: Boolean by remember { mutableStateOf(false)}
    val checkedState = remember { mutableStateOf(false)}
    val annotatedStringForPassword = buildAnnotatedString {
        pushStringAnnotation(tag="password", annotation = "https://google.com")
        withStyle(style = SpanStyle(color = btnSignIn)) {
            append("Forgot Password?")
        }
        pop()
    }
    val annotatedStringForSignUp = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("Don't have an account?")
        }
        pushStringAnnotation(tag = "signup", annotation = "https://google.com/policy")
        withStyle(style = SpanStyle(color = textLinkSignIn)) {
            append(" Sign Up?")
        }
        pop()
    }

    Column() {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { context.startActivity(Intent(context, MainActivity::class.java)) }) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
            Text(text = "Sign In", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 90.dp))
        }
        Column(modifier = Modifier.padding(top = 50.dp, start = 20.dp)) {
            Text(text = "Hi, Welcome Back! \uD83D\uDC4B ", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Lorem ipsum dolor sit amet, consectetur", fontSize = 15.sp, fontWeight = FontWeight.Light)
        }
    }

    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp), text = "Email Address", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 10.dp),value = loginTextField.value, onValueChange = { loginTextField.value = it},
                    placeholder = {Text("Enter your email address")}, shape = CircleShape)

                Text(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 15.dp) , text = "Password", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 10.dp), value = passwordTextField.value, onValueChange = { passwordTextField.value = it}, singleLine = true,
                    placeholder = {Text("Enter your password")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = CircleShape
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row() {
                Checkbox(checkedState.value, onCheckedChange = { checkedState.value = it }, Modifier.padding(start = 10.dp))
                Text("Remember Me", Modifier.padding(top = 12.dp), fontSize = 16.sp)
            }
            ClickableText(style = TextStyle(fontSize = 16.sp) ,text = annotatedStringForPassword, onClick = { it -> annotatedStringForPassword.getStringAnnotations(tag = "password",
                start = it, end = it).firstOrNull()?.let {
                Log.d("policy URL", it.item)
            }}, modifier = Modifier.padding(top = 12.dp, end = 25.dp))
        }
        Button(onClick = { if ((loginTextField.value == login.value) and (passwordTextField.value == password.value)) {
            context.startActivity(Intent(context, MainMenuActivity::class.java))
        } else {
            Toast.makeText(context, "Неправильный пароль или логин!", Toast.LENGTH_SHORT).show()
        }
             }, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 20.dp)
            .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = btnSignIn, contentColor = Color.White)
        ) {
            Text("Sign In")
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
            ClickableText(style = TextStyle(fontSize = 17.sp) ,text = annotatedStringForSignUp, onClick = { it -> annotatedStringForSignUp.getStringAnnotations(tag = "signup", start = it, end = it).firstOrNull()?.let {
                context.startActivity(Intent(context, SignUpActivity::class.java))
            }})
        }
    }
}