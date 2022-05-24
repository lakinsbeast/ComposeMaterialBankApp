@file:OptIn(InternalPlatformTextApi::class)

package com.example.testcomposematerial3.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.twotone.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.android.InternalPlatformTextApi
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dt.composedatepicker.ComposeCalendar
import com.dt.composedatepicker.SelectDateListener
import com.example.testcomposematerial3.R
import com.example.testcomposematerial3.ui.theme.blackCardColor
import com.example.testcomposematerial3.ui.theme.btnSignIn
import com.example.testcomposematerial3.ui.theme.fabColor
import com.example.testcomposematerial3.ui.theme.purpleCardColor
import com.google.accompanist.pager.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

val selectedNavIcon = mutableStateOf(0)
val selectedIndex =  mutableStateOf(0)
val showDatePicker = mutableStateOf(false)
class MainMenuActivity : ComponentActivity() {
    val balance: MutableList<String> = arrayListOf("\$4.614,94", "\$1.312,10", "\$101.784,60", "\$6.742,87")
    val paySystem: MutableList<String> = arrayListOf("Union Pay", "Visa", "Master Card", "Mir")

    data class FruitModel(val name:String, val numberCard : String, val numOfTrans: String)
    private val contactLists = mutableListOf<FruitModel>()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactLists.add(FruitModel("Tiana Saris", "2468 3545 ****", "$456,00"))
        contactLists.add(FruitModel("Vladimir Zelensky", "2468 3545 ****", "$1488,00"))
        contactLists.add(FruitModel("Kayia Baptista", "2468 3545 ****", "$567,00"))
        contactLists.add(FruitModel("Desirae Bergson", "2468 3545 ****", "$456,00"))
        contactLists.add(FruitModel("Emery Schleifner", "2468 3545 ****", "$986,00"))
        contactLists.add(FruitModel("Roger Levin", "2468 3545 ****", "$986,00"))
        contactLists.add(FruitModel("Jaydon Batosh", "2468 3545 ****", "$986,00"))
        contactLists.add(FruitModel("Alexey Navalny", "2468 3545 ****", "$986,00"))
        contactLists.add(FruitModel("Vladimir Putin", "2468 3545 ****", "$678,00"))
        setContent {
            Scaffold(bottomBar = {
                BottomNav() }) {
                if (selectedIndex.value == 0) {
                    Column(Modifier.verticalScroll(state = rememberScrollState(0), enabled = true)) {
                        WelcomeName(name = "Sophia Calzoni")
                        Cards(balance, paySystem)
                        Actions()
                    }
                }
                if (selectedIndex.value == 1) {
                    Column() {
                        if (showDatePicker.value) {
                            DateChoose()
                        }
                        StatisticsAppBar()
//                        StatisticsDateChooseAndGraphics()
                        StatisticsActionsCards()
                        StatisticsResentTransactions(contactList = contactLists)
                    }
                }
                if (selectedIndex.value == 2) {
                    Column {
                        MyCardsAppBar()

                        MyCardsList(balance, paySystem)


                        OutlinedButton(onClick = { /*TODO*/ },
                            Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp
                                )
                                .height(50.dp)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp, color = Color.Black,
                                    shape = RoundedCornerShape(50)
                                )
                                .clip(RoundedCornerShape(50)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black)) {
                            Text("Add Card")
                        }
                    }
                }
                if (selectedIndex.value == 3) {
                    Column(Modifier.verticalScroll(state = rememberScrollState(0), enabled = true)) {
                        MyProfileAppBar()
                        MyProfileAvatar("Tiana Saris")
                        MyProfileMenu()
                    }
                }
            }
        }
    }
}


@Composable
fun WelcomeName(name: String) {
    Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
            Image(painter = painterResource(id = R.drawable.i),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(100)), contentScale = ContentScale.Crop)
            Column(Modifier.padding(start = 10.dp)) {
                Text(text = "Welcome back", color = Color.Gray)
                Text(text = name, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
            }
        }
        IconButton(onClick = {  }, Modifier.border(1.dp, Color.Gray, shape = CircleShape)) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Cards(balance: MutableList<String>, paySystem: MutableList<String>) {
    var pagerState = rememberPagerState()
    var randomColor = arrayOf(purpleCardColor, blackCardColor).random()
    HorizontalPager(count = 4, state = pagerState, contentPadding = PaddingValues(horizontal = 32.dp)) {
        Card(
            elevation = 15.dp, modifier = Modifier
                .padding(start = 20.dp, 10.dp)
                .width(250.dp), shape = RoundedCornerShape(25.dp), backgroundColor = purpleCardColor
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .padding(
                            start = 30.dp,
                            end = 30.dp, top = 20.dp
                        )
                        .fillMaxWidth()
                ) {
                    Text("X-card", fontWeight = FontWeight.Bold, color = Color.White)
                    Text(paySystem[it], fontWeight = FontWeight.Bold, color = Color.White)
                }
                Column(modifier = Modifier.padding(top = 100.dp, start = 30.dp)) {
                    Text(text = "Balance", color = Color.Gray)
                    Text(text = balance[it], fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.White)
                }
                Row(
                    modifier = Modifier
                        .padding(top = 100.dp, start = 30.dp, end = 30.dp, bottom = 20.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Text(text = "****   ", color = Color.Gray, fontSize = 15.sp)
                        Text(text = "2423", color = Color.Gray, fontSize = 15.sp)
                    }
                    Text(text = "12/24", color = Color.White)
                }
            }
        }
    }
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.fillMaxWidth()) {
        HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier
            .padding(16.dp))
    }

}

@Composable
fun Actions(){
    val context = LocalContext.current
    Column(Modifier.padding(top = 20.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)) {
            Card(modifier = Modifier
                .size(160.dp)
                .clickable {
                    context.startActivity(Intent(context, SendMoneyActivity::class.java))
                }, shape = RoundedCornerShape(25.dp),
            border = BorderStroke(0.1.dp, Color.Gray)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.send48px),
                        contentDescription = "Send Money",
                        Modifier
                            .size(25.dp)
                            .clip(
                                RoundedCornerShape(25.dp)
                            )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                    )
                    Text(text = "Send Money", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "Take acc to acc", color = Color.Gray)
                }
            }
            Card(modifier = Modifier
                .size(160.dp)
                .clickable { }, shape = RoundedCornerShape(20.dp),
                border = BorderStroke(0.1.dp, Color.Gray)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.wallet48px),
                        contentDescription = "Pay the bill",
                        Modifier
                            .size(25.dp)
                            .clip(
                                RoundedCornerShape(25.dp)
                            )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                    )
                    Text(text = "Pay the bill", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "Lorem ipsum", color = Color.Gray)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 25.dp, end = 25.dp, bottom = 70.dp)) {
            Card(modifier = Modifier
                .size(160.dp)
                .clickable { }, shape = RoundedCornerShape(25.dp),
                border = BorderStroke(0.1.dp, Color.Gray)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.file_download48px),
                        contentDescription = "Request",
                        Modifier
                            .size(25.dp)
                            .clip(
                                RoundedCornerShape(25.dp)
                            )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                    )
                    Text(text = "Request",color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "Lorem ipsum", color = Color.Gray)
                }
            }
            Card(modifier = Modifier
                .size(160.dp)
                .clickable { }, shape = RoundedCornerShape(20.dp),
                border = BorderStroke(0.1.dp, Color.Gray)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.android_contacts48px),
                        contentDescription = "Contact",
                        Modifier
                            .size(25.dp)
                            .clip(
                                RoundedCornerShape(25.dp)
                            )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                    )
                    Text(text = "Contact", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "Lorem ipsum", color = Color.Gray)
                }
            }
        }

    }
}

@Composable
fun BottomNav(){
    BottomNavigation(backgroundColor = Color.White) {
        var Home = Icons.Outlined.Home
        var Statistic = R.drawable.analytics48px
        var MyCard = R.drawable.credit_card48px
        var Profile = Icons.Outlined.Person
        BottomNavigationItem(icon = {
            Icon(imageVector = Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = ImageVector.vectorResource(Statistic),"", modifier = Modifier.size(25.dp))
        },
            label = { Text(text = "Statistic") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })
        FloatingActionButtonMainMenu()

        BottomNavigationItem(icon = {
            Icon(imageVector = ImageVector.vectorResource(MyCard),"", modifier = Modifier.size(25.dp))
        },
            label = { Text(text = "My Card") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Profile,"", modifier = Modifier.size(25.dp))
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 3),
            onClick = {
                selectedIndex.value = 3
            })
    }
}

@Composable
fun FloatingActionButtonMainMenu() {
    FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = fabColor, elevation = FloatingActionButtonDefaults.elevation(0.dp)) {
        Icon(Icons.Filled.Add, contentDescription = "", modifier = Modifier.background(Color.White, shape = RoundedCornerShape(4.dp)))
    }
}

@Composable
fun StatisticsAppBar() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)) {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(text = "Reload", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
    }
}
@Composable
fun StatisticsDateChooseAndGraphics () {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Text("Hello")
        Button(onClick = {
            showDatePicker.value = true
        }) {

        }
    }
}

@Composable
fun DateChoose() {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, 2018)
    calendar.set(Calendar.MONTH, 1)
    val calendarMax = Calendar.getInstance()
    calendarMax.set(Calendar.YEAR, 2032)
    calendarMax.set(Calendar.MONTH, 12)
    Column(Modifier.padding(end = 30.dp, start = 30.dp, top = 30.dp, bottom = 100.dp)) {
        ComposeCalendar(minDate = calendar.time, maxDate = calendarMax.time, locale = Locale("ru"),
            title = "Select Date", listener = object : SelectDateListener {
                override fun onCanceled() {
                }
                override fun onDateSelected(date: Date) {
                    Log.d("Date", date.toString())
//                    Log.d("Date.time", date.time.toString())
                    val simpleDateFormat = SimpleDateFormat("MM.yyyy")
                    val dateTime = simpleDateFormat.format(date).toString()
                    Log.d("datetime", dateTime)
                    showDatePicker.value = false
                }

            })
    }

}

@Composable
fun StatisticsActionsCards() {
    val context = LocalContext.current
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 25.dp, end = 25.dp)) {
        Card(modifier = Modifier
            .size(160.dp)
            .clickable {
                context.startActivity(Intent(context, SendMoneyActivity::class.java))
            }, shape = RoundedCornerShape(25.dp),
            border = BorderStroke(0.1.dp, Color.Gray)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(imageVector = ImageVector.vectorResource(R.drawable.file_download48px),
                    contentDescription = "Send Money",
                    Modifier
                        .size(25.dp)
                        .clip(
                            RoundedCornerShape(25.dp)
                        )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                )
                Text(text = "$6,213.32", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "Income", color = Color.Gray)
            }
        }
        Card(modifier = Modifier
            .size(160.dp)
            .clickable { }, shape = RoundedCornerShape(20.dp),
            border = BorderStroke(0.1.dp, Color.Gray)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(imageVector = ImageVector.vectorResource(R.drawable.file_upload48px),
                    contentDescription = "Pay the bill",
                    Modifier
                        .size(25.dp)
                        .clip(
                            RoundedCornerShape(25.dp)
                        )
//                            .background(Color.Gray.copy(alpha = 0.1f))
                )
                Text(text = "$1,487.78", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "Outcome", color = Color.Gray)
            }
        }
    }
}
@Composable
fun StatisticsResentTransactions(contactList: List<MainMenuActivity.FruitModel>){
    Row(Modifier.padding(20.dp)) {
        Text("Resent Transactions", fontWeight = FontWeight.Medium, fontSize = 18.sp)
    }
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)) {
        items(contactList) {
            ListRow(model = it)
        }
    }

}
@Composable
fun ListRow(model: MainMenuActivity.FruitModel) {
    Row(Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        Image(painter = painterResource(id = R.drawable.i),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 10.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(100)), contentScale = ContentScale.Crop)
        Column() {
            Text(
                text = model.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = model.numberCard,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {
            Text(model.numOfTrans,fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black)
        }


    }
}

@Composable
fun MyCardsAppBar() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)) {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(text = "All Cards", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyCardsList(balance: MutableList<String>, paySystem: MutableList<String>) {
    var pagerState = rememberPagerState()
    Row(
        Modifier
            .fillMaxWidth()
            .height(400.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        VerticalPager(count = 4, state = pagerState) {
            Card(
                elevation = 15.dp, modifier = Modifier
                    .padding(start = 29.dp, 5.dp, bottom = 5.dp)
                    .width(250.dp), shape = RoundedCornerShape(25.dp), backgroundColor = purpleCardColor
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .padding(
                                start = 30.dp,
                                end = 30.dp, top = 20.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Text("X-card", fontWeight = FontWeight.Bold, color = Color.White)
                        Text(paySystem[it], fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Column(modifier = Modifier.padding(top = 100.dp, start = 30.dp)) {
                        Text(text = "Balance", color = Color.Gray)
                        Text(text = balance[it], fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.White)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 100.dp, start = 30.dp, end = 30.dp, bottom = 20.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row() {
                            Text(text = "****   ", color = Color.Gray, fontSize = 15.sp)
                            Text(text = "2423", color = Color.Gray, fontSize = 15.sp)
                        }
                        Text(text = "12/24", color = Color.White)
                    }
                }
            }
        }
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            VerticalPagerIndicator(pagerState = pagerState)
        }
    }
}
@Composable
fun MyProfileAppBar() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)) {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(text = "Profile", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
        IconButton(onClick = {  }, enabled = false) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu", Modifier.alpha(0f))
        }
    }
}
@Composable
fun MyProfileAvatar(name: String) {
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.i),
            contentDescription = "avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(100)), contentScale = ContentScale.Crop)
        Text(text = name, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 22.sp, modifier = Modifier.padding(top = 10.dp))
        Text(text = "@Brooklyn", color = Color.Gray, fontSize = 18.sp, modifier = Modifier.padding(top = 6.dp))
    }
}
@Composable
fun MyProfileMenu() {
    Column(Modifier.fillMaxWidth()) {
        Text("Personal Info", Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp), color = Color.Gray)
        Column(Modifier.padding()) {
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.account_circle_full48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text(text = "Your Profile", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }

            }
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.download48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("History Transactions", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
        }
        Text("Security", Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp), color = Color.Gray)
        Column(Modifier.padding()) {
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.face_id_48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Face Id", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.lock48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Change Password", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.lock_open48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Forgot Password", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
        }
        Text("General", Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp), color = Color.Gray)
        Column(Modifier.padding(bottom = 70.dp)) {
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.notifications48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Notification", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.language48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Languages", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 5.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.dp, color = Color.Transparent,
                    )
                ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                elevation = ButtonDefaults.elevation(0.dp)) {
                Row(Modifier.fillMaxSize() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.help48px), contentDescription = "",
                        Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(25.dp)))
                    Text("Help and Support", fontSize = 19.sp, style = TextStyle(letterSpacing = 0.1.sp))
                }
            }
        }

    }
}