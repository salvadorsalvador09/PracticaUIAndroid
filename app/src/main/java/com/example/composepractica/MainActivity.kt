package com.example.composepractica

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.composepractica.ui.theme.ComposePracticaTheme
import android.util.Log
import androidx.collection.emptyLongSet
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePracticaTheme {
                MainCotainer()
            }
        }
    }
}


fun intToColor(color: Int): Color
{
    return when(color){
        1 -> Color.Red
        2 -> Color.Green
        3 -> Color.Blue
        4 -> Color.Yellow
        5 -> Color.Magenta
        6 -> Color.Cyan
        7 -> Color.White
        8 -> Color.Black
        9 -> Color.Gray
        else -> Color.Black
    }
}

@Composable
fun MainCotainer(){
    var backgroundColor by remember { mutableIntStateOf(1)  }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = intToColor(backgroundColor)
    ) {
        ChangeBackgroundScreen(
            changeBackgroundColor = {backgroundColor = it}
        )
    }
}

@Composable
fun ColorSlider(
    modifier: Modifier = Modifier,
    value: Float ,
    onValueChange: (Float) -> Unit
) {
    Column (Modifier.padding(top=60.dp)){
        Slider(
            value = value,
            onValueChange = { onValueChange(it) },
            valueRange = 1f..9f,
            steps = 7,
            modifier = Modifier.padding(20.dp)
                .padding(20.dp)
        )
        Text(
            text = value.toString(),
            modifier = Modifier.padding(20.dp)
        )
    }
}


@Composable
fun ChangeBackgroundScreen(
    modifier: Modifier = Modifier,
        changeBackgroundColor: (Int) -> Unit
){
    var value by remember { mutableStateOf(1f) }
    ColorSlider(
        value = value,
        onValueChange = {
            value = it
            changeBackgroundColor(it.toInt())}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var value by remember { mutableStateOf(1f) }
    MainCotainer()
}