// MainActivity
package com.example.khyku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.ui.theme.KHYKUTheme
import com.example.khyku.yh.ProfileScreen.ProfileScreen
import com.example.khyku.yh.ProfileScreen.UserList
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userDB.UserProfileDatabase
import com.example.khyku.yh.userViewmodel.Repository
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import com.example.khyku.yh.userViewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KHYKUTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}
@Composable
fun UserProfileScreen() {
    val context = LocalContext.current
    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
    val viewModel:UserProfileViewModel =
        viewModel(factory = ViewModelFactory(Repository(userdb)))

    val userlist by viewModel.userList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
    var selectedUser: UserProfile? by remember{
        mutableStateOf<UserProfile?>(null)
    }
    val selectedEvent = {user: UserProfile -> selectedUser = user }

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileScreen(viewModel = viewModel, selectedUser)
        UserList(list = userlist , onClick = selectedEvent)
    }
}

