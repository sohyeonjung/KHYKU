//package com.example.khyku.facilityScreen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Place
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.khyku.HomeScreen.ItemUI
//import com.example.khyku.HomeScreen.SubjectAdder
//import com.example.khyku.HomeScreen.formatTime
//import com.example.khyku.R
//import com.example.khyku.User.Subject
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FacilityScreen(modifier: Modifier = Modifier) {
//    var showFacilitySheet by remember { mutableStateOf(false) }
//    var selectedFac by remember {
//        mutableStateOf(Facility("",false, false, "", false, 0))
//    }
//    Column {
//        Text(
//            text = "학교 시설 안내",
//            modifier = Modifier
//                .background(colorResource(id = R.color.konkukgreen))
//                .fillMaxWidth(),
//            color = Color.White,
//        )
//
//        LazyColumn (modifier = Modifier.background(Color.White)){
//            items(FacilityItems.Facilities){ item ->
//                FacilityUI(item){
//                    showFacilitySheet=true
//                    selectedFac = item
//                }
//                Divider(color = colorResource(id = R.color.konkukgreen))
//            }
//        }
//        if (showFacilitySheet) {
//            ModalBottomSheet(
//                onDismissRequest = { showFacilitySheet = false },
//            ) {
//                FacilityInfo(selectedFac) {
//                    showFacilitySheet = false
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun FacilityUI(item: Facility, onClick: (item:Facility) -> Unit) {
//    Column (modifier = Modifier
//        .fillMaxWidth()
//        .clickable { onClick(item) }){
//        Row(modifier = Modifier.padding(15.dp)) {
//            if(item.charge == true){
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(colorResource(id = R.color.konkukgreen)))
//                {
//                    Text(text = " 충전 가능 ", color = Color.White, modifier = Modifier.padding(3.dp))
//                }
//                Spacer(modifier = Modifier.padding(4.dp))
//            }
//            if(item.cafe == true){
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(colorResource(id = R.color.konkukgreen)))
//                {
//                    Text(text = " 카페 ", color = Color.White, modifier = Modifier.padding(3.dp))
//                }
//                Spacer(modifier = Modifier.padding(4.dp))
//            }
//            if(item.room == true){
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(colorResource(id = R.color.konkukgreen)))
//                {
//                    Text(text = " 회의실 ", color = Color.White, modifier = Modifier.padding(3.dp))
//                }
//            }
//        }
//        Text(
//            text = item.location,
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            modifier = Modifier.padding(start = 10.dp)
//        )
//        Row {
//            Icon(imageVector = Icons.Default.Place, contentDescription = "")
//            Text(text = item.cafeloction)
//        }
//    }
//
//}
//
//@Composable
//fun FacilityInfo(item:Facility,onClose: () -> Unit){
//    Column {
//        Text(text = item.location, fontWeight = FontWeight.Bold, fontSize = 20.sp)
//        Text(text = "좌석 사진", fontWeight = FontWeight.Bold, fontSize = 15.sp)
//        Text(text = "인근 카페 위치", fontWeight = FontWeight.Bold, fontSize = 15.sp)
//        Text(text = "특이 사항", fontWeight = FontWeight.Bold, fontSize = 15.sp)
//    }
//}

package com.example.khyku.facilityScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khyku.HomeScreen.ItemUI
import com.example.khyku.HomeScreen.SubjectAdder
import com.example.khyku.HomeScreen.formatTime
import com.example.khyku.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacilityScreen(modifier: Modifier = Modifier) {
    var showFacilitySheet by remember { mutableStateOf(false) }
    var selectedFac by remember {
        mutableStateOf(Facility("",false, false, "", false, 0, 0, LatLng(37.0, 127.0), LatLng(37.0,127.0)))
    }
    Column {
        Text(
            text = "학교 시설 안내",
            modifier = Modifier
                .background(colorResource(id = R.color.konkukgreen))
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn (modifier = Modifier.background(Color.White)){
            items(FacilityItems.Facilities){ item ->
                FacilityUI(item){
                    showFacilitySheet=true
                    selectedFac = item
                }
                Divider(color = colorResource(id = R.color.konkukgreen))
            }
        }
        if (showFacilitySheet) {
            ModalBottomSheet(
                onDismissRequest = { showFacilitySheet = false },
            ) {
                FacilityInfo(selectedFac) {
                    showFacilitySheet = false
                }
            }
        }
    }
}

@Composable
fun FacilityUI(item: Facility, onClick: (item:Facility) -> Unit) {
    Column (modifier = Modifier
        .fillMaxWidth().padding(start = 10.dp, top = 3.dp, bottom = 4.dp)
        .clickable { onClick(item) })
    {
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
            if(item.charge == true){
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    //.background(colorResource(id = R.color.konkukgreen))
                    .background(Color.White)
                    .border(
                        1.dp,
                        colorResource(id = R.color.konkukgreen),
                        shape = RoundedCornerShape(16.dp)
                    ))
                {
                    Text(text = " 충전 가능 ", 
                        //color = Color.White, 
                        color = colorResource(id = R.color.konkukgreen),
                        modifier = Modifier.padding(3.dp))
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            if(item.cafe == true){
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(colorResource(id = R.color.konkukgreen)))
//                {
//                    Text(text = " 카페 ", color = Color.White, modifier = Modifier.padding(3.dp))
//                }
//                Spacer(modifier = Modifier.padding(4.dp))
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    //.background(colorResource(id = R.color.konkukgreen))
                    .background(Color.White)
                    .border(
                        1.dp,
                        colorResource(id = R.color.konkukgreen),
                        shape = RoundedCornerShape(16.dp)
                    ))
                {
                    Text(text = " 카페 ",
                        //color = Color.White,
                        color = colorResource(id = R.color.konkukgreen),
                        modifier = Modifier.padding(3.dp))
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            if(item.room == true){
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(colorResource(id = R.color.konkukgreen)))
//                {
//                    Text(text = " 회의실 ", color = Color.White, modifier = Modifier.padding(3.dp))
//                }
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    //.background(colorResource(id = R.color.konkukgreen))
                    .background(Color.White)
                    .border(
                        1.dp,
                        colorResource(id = R.color.konkukgreen),
                        shape = RoundedCornerShape(16.dp)
                    ))
                {
                    Text(text = " 회의실 ",
                        //color = Color.White,
                        color = colorResource(id = R.color.konkukgreen),
                        modifier = Modifier.padding(3.dp))
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
        Text(
            text = item.location,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
        )
        Row {
            Icon(imageVector = Icons.Default.Place, contentDescription = "")
            Text(text = item.cafeloction)
        }
    }

}

@Composable
fun FacilityInfo(item:Facility,onClose: () -> Unit){
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(item.buildingLatLng, 17f)
    }
    Column (){
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.Center){
            Text(text = item.location, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Text(text = "건물 사진", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start =20.dp, top = 10.dp))
        Image(painter = painterResource(id = item.buildingImg), contentDescription = "" , modifier = Modifier.padding(20.dp, 10.dp))
        Text(text = "건물 및 카페 위치", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start=20.dp))
        Box(modifier = Modifier.fillMaxWidth()){
            GoogleMap(
                modifier = Modifier
                    .padding(20.dp)
                    .height(230.dp),
                cameraPositionState =  cameraPositionState
            ){
                Marker(
                    state =  MarkerState(position = item.buildingLatLng),
                    title = item.location
                )
                Marker(
                    state =  MarkerState(position = item.cafeLatlng),
                    title = item.cafeloction
                )
            }
        }
        Text(text = "좌석 사진", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(start = 20.dp))
        Image(painter = painterResource(id = item.seatImg), contentDescription = "" , modifier = Modifier.padding(20.dp))
        Row {
            if(item.charge==true){
                Text(text = "충전 가능 ")
            }
            if(item.cafe == true){
                Text(text = " /음료 반입 가능")
            }
            if(item.cafe==false){
                Text(text = " /음료 반입 불가능")
            }
            if(item.room == true){
                Text(text = " /회의실 사용 가능")
            }
            Text(text = " /대화 가능")
        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.Center){
            Button(
                onClick = { onClose()},
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.konkukgreen),
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.White,
                )
            ) {
                Text(text = "닫기")
            }
        }

    }
}