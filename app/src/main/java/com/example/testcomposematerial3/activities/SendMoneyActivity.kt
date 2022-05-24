package com.example.testcomposematerial3.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SendMoneyActivity : ComponentActivity() {
    data class FruitModel(val name:String, val numberCard : String)
    private val contactLists = mutableListOf<FruitModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactLists.add(FruitModel("Tiana Saris", "2468 3545 ****"))
        contactLists.add(FruitModel("Kayia Baptista", "2468 3545 ****"))
        contactLists.add(FruitModel("Desirae Bergson", "2468 3545 ****"))
        contactLists.add(FruitModel("Emery Schleifner", "2468 3545 ****"))
        contactLists.add(FruitModel("Roger Levin", "2468 3545 ****"))
        contactLists.add(FruitModel("Jaydon Batosh", "2468 3545 ****"))
        contactLists.add(FruitModel("Alexey Navalny", "2468 3545 ****"))
        contactLists.add(FruitModel("Vladimir Putin", "2468 3545 ****"))

        setContent {
            Column {
                AppBar()
                SearchBar()
                ContactChoice()
                ListContact(contactList = contactLists)
            }
        }
    }
}

@Composable
fun AppBar() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)) {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(text = "Send Money", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Add, contentDescription = "Menu")
        }
    }
}
@Composable
fun SearchBar() {
    OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        value = "", onValueChange = { }, leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "") },
        placeholder = {Text("Search contact..")}, shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun ContactChoice() {
    var frstBtn = remember { mutableStateOf(Color.White)}
    var scndBtn = remember { mutableStateOf(Color.Gray)}
    var thrdBtn = remember { mutableStateOf(Color.Gray)}
    var frthBtn = remember { mutableStateOf(Color.Gray)}
//    var frstBtn = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black)
//    var scndBtn = ButtonDefaults.buttonColors(backgroundColor = Color.Gray, contentColor = Color.Black)
//    var thrdBtn = ButtonDefaults.buttonColors(backgroundColor = Color.Gray, contentColor = Color.Black)
//    var frthBtn = ButtonDefaults.buttonColors(backgroundColor = Color.Gray, contentColor = Color.Black)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp, top = 0.dp, end = 20.dp)
            .background(Color.Gray, shape = RoundedCornerShape(10))) {
        Button(onClick = {
            frstBtn.value = Color.White
            scndBtn.value = Color.Gray
            thrdBtn.value = Color.Gray
            frthBtn.value = Color.Gray
        },
            Modifier
                .weight(1f)
                .padding(4.dp)
                .size(40.dp), colors = ButtonDefaults.buttonColors(backgroundColor = frstBtn.value, contentColor = Color.Black),
            elevation = ButtonDefaults.elevation(0.dp)) {
            Text(text = "All", fontSize = 13.sp, style = TextStyle(letterSpacing = 0.1.sp), fontWeight = FontWeight.Medium)
        }
        Button(onClick = {
            frstBtn.value = Color.Gray
            scndBtn.value = Color.White
            thrdBtn.value = Color.Gray
            frthBtn.value = Color.Gray
        },
            Modifier
                .weight(1f)
                .padding(4.dp)
                .size(40.dp), colors = ButtonDefaults.buttonColors(backgroundColor = scndBtn.value, contentColor = Color.Black)
        ,elevation = ButtonDefaults.elevation(0.dp)) {
            Text(text = "Favorite", fontSize = 12.sp, style = TextStyle(letterSpacing = 0.1.sp), fontWeight = FontWeight.Medium)
        }
        Button(onClick = {
            frstBtn.value = Color.Gray
            scndBtn.value = Color.Gray
            thrdBtn.value = Color.White
            frthBtn.value = Color.Gray
        },
            Modifier
                .weight(1f)
                .padding(4.dp)
                .size(40.dp), colors = ButtonDefaults.buttonColors(backgroundColor = thrdBtn.value, contentColor = Color.Black)
                , elevation = ButtonDefaults.elevation(0.dp)) {
            Text(text = "Bank", fontSize = 13.sp, style = TextStyle(letterSpacing = 0.1.sp), fontWeight = FontWeight.Medium)
        }
        Button(onClick = {
            frstBtn.value = Color.Gray
            scndBtn.value = Color.Gray
            thrdBtn.value = Color.Gray
            frthBtn.value = Color.White
        },
            Modifier
                .weight(1f)
                .padding(4.dp)
                .size(40.dp), colors = ButtonDefaults.buttonColors(backgroundColor = frthBtn.value, contentColor = Color.Black)
        ,elevation = ButtonDefaults.elevation(0.dp)) {
            Text(text = "e-Wallet", fontSize = 12.sp, style = TextStyle(letterSpacing = 0.1.sp), fontWeight = FontWeight.Medium)
        }
    }
}
@Composable
fun ListContact(contactList: List<SendMoneyActivity.FruitModel>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(contactList) {
            ListRow(model = it)
        }
    }
}
@Composable
fun ListRow(model: SendMoneyActivity.FruitModel) {
    Row(Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        Image(
            painter = painterResource(R.drawable.i),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(100)))
        Column() {
            Text(
                text = model.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = model.numberCard,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

    }
}