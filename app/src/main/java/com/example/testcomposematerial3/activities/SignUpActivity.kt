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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcomposematerial3.ui.theme.btnSignIn
import com.example.testcomposematerial3.ui.theme.textLinkSignUp


val loginSignUpTextField = mutableStateOf("")
val passwordSignUpTextField =  mutableStateOf("")
val fullNameSignUpTextField = mutableStateOf("")
val login = mutableStateOf("")
val password = mutableStateOf("")
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(android.graphics.Color.WHITE)) )
                {
                    SignUp()
                }
            }
        }
    }
}

@Composable
fun SignUp(){
    val context = LocalContext.current
    val passwordVisible: Boolean by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }
    val checkedTerms = remember { mutableStateOf(false) }
    val annotatedStringForPassword = buildAnnotatedString {
        pushStringAnnotation(tag="password", annotation = "https://google.com")
        withStyle(style = SpanStyle(color = btnSignIn)) {
            append("Forgot Password?")
        }
        pop()
    }
    val annotatedStringForSignUp = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("By signing up you agree to our")
        }
        pushStringAnnotation(tag = "signup", annotation = "https://google.com/policy")
        withStyle(style = SpanStyle(color = textLinkSignUp)) {
            append(" Terms\n")
        }
        pop()
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("and")
        }
        pushStringAnnotation(tag = "conditions", annotation = "https://google.com/policy")
        withStyle(style = SpanStyle(color = textLinkSignUp)) {
            append(" Conditions of Use")
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
            Text(text = "Create Account", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Lorem ipsum dolor sit amet, consectetur", fontSize = 15.sp, fontWeight = FontWeight.Light)
        }
    }

    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp), text = "Full name", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),value = fullNameSignUpTextField.value, onValueChange = { fullNameSignUpTextField.value = it},
                    placeholder = {Text("Enter your name")}, shape = CircleShape
                )

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp), text = "Email Address", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),value = loginSignUpTextField.value, onValueChange = { loginSignUpTextField.value = it},
                    placeholder = {Text("Enter your email address")}, shape = CircleShape
                )

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp) , text = "Password", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp), value = passwordSignUpTextField.value, onValueChange = { passwordSignUpTextField.value = it}, singleLine = true,
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
        Button(onClick = { if (!checkedTerms.value) {
            Toast.makeText(context, "Вы не приняли условия!", Toast.LENGTH_SHORT).show()
        } else {
            if (fullNameSignUpTextField.value.isEmpty() and loginSignUpTextField.value.isEmpty()
                and passwordSignUpTextField.value.isEmpty()) {
                Toast.makeText(context, "Вы что-то пропустили!", Toast.LENGTH_SHORT).show()
            } else {
                login.value = loginSignUpTextField.value
                password.value = passwordSignUpTextField.value
                context.startActivity(Intent(context, SignInActivity::class.java))
            }
        } }, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 20.dp)
            .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = btnSignIn, contentColor = Color.White)
        ) {
            Text("Create An Account")
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp)
            .align(Alignment.CenterHorizontally)) {
            Row() {
                Checkbox(checkedTerms.value, onCheckedChange = { checkedTerms.value = it }, Modifier.padding(start = 10.dp))
                ClickableText(style = TextStyle(fontSize = 17.sp),text = annotatedStringForSignUp, onClick = { it ->
                    annotatedStringForSignUp.getStringAnnotations(tag = "signup", start = it, end = it).firstOrNull()?.let {
                        Log.d("signUPLink", it.item)
                    }
                    annotatedStringForSignUp.getStringAnnotations(tag = "conditions", start = it, end = it).firstOrNull()?.let {
                        Log.d("ConditionsLink", it.item)
                    }})
            }
        }

    }
}