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
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePracticaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var show by remember { mutableStateOf(false) }
                    Column {

                        OpenDialogButton(
                            onClickHandle = {
                                show = true
                            }
                        )
                        CloseWindowDialog(
                            show = show,
                            onDismiss = { show = false },
                            onConfirm = { show = false }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OpenDialogButton(
    onClickHandle: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClickHandle() },
        modifier = modifier
            .padding(145.dp)
    ) {
        Text(text = "Close Page")
    }
}

@Composable
fun CloseWindowDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                OutlinedButton(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = R.color.confirm_button_red)
                    ),
                    border = BorderStroke(
                        1.dp,
                        color = colorResource(id = R.color.confirm_button_red)
                    )
                )
                {
                    Text(text = "Si, cerrar")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = R.color.dismiss_button_green)
                    ),
                    border = BorderStroke(
                        1.dp,
                        color = colorResource(id = R.color.dismiss_button_green)
                    )
                )
                {
                    Text(text = "Cancelar")
                }
            },
            title = {
                Text(
                    text = "¿Estas seguro de salir de esta página?",
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = "Una ves que salgas de esta pagina, todo progreso que no hayas sido guardado se perdará"
                )
            },
            containerColor = Color.White,
            shape = RectangleShape,
            modifier = modifier
        )
    }

}

@Composable
fun MyTextComponent(name: String, modifier: Modifier = Modifier) {
    val myPerson = Person("Majo", "Euan")
    Text(
        text = "Hola, ${myPerson.name} ${myPerson.lastName}! Soy mira mi componente!",
        color = Color.Red
    )
}

@Composable
fun MySlider(modifier: Modifier = Modifier) {
    var sliderVal by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderVal,
            valueRange = 0f..10f,
            steps = 10,
            onValueChange = { sliderVal = it },
            colors = SliderDefaults.colors(
                thumbColor = colorResource(id = R.color.purple_200),
                activeTickColor = Color.White,
                activeTrackColor = Color.Cyan,
                inactiveTickColor = Color.Blue,
                inactiveTrackColor = Color.LightGray
            ),
            modifier = modifier
                .size(500.dp)
                .padding(20.dp) //Margin
                .padding(80.dp) //Padding
        )
        Text(text = sliderVal.toString())
    }
}

@Composable
fun MySwitch(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = false,
        onCheckedChange = { checked = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Blue,
            checkedBorderColor = Color.LightGray,
            checkedTrackColor = Color.LightGray,
            uncheckedThumbColor = Color.Black,
            uncheckedBorderColor = Color.Gray,
            uncheckedTrackColor = Color.Black
        ),
        modifier = modifier
            .padding(20.dp) //margin
            .padding(5.dp) // padding
    )
}


@Composable
fun MyDialog(
    modifier: Modifier = Modifier, showDialog: Boolean,
    dimissDialog: () -> Unit,
    confirmDialog: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { dimissDialog() },
            confirmButton = {
                TextButton(onClick = { confirmDialog() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { dimissDialog() })
                {
                    Text(text = "Cancelar")
                }
            },
            title = {
                Text(
                    text = "Eliminar Archivo",
                    fontWeight = FontWeight.Black
                )
            },
            text = {
                Text(
                    text = "¿Deseas eliminar este archivo?",
                    color = Color.DarkGray
                )
            },
            containerColor = Color.White,
            titleContentColor = colorResource(id = R.color.purple_200),
            shape = RoundedCornerShape(10.dp),
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        )
    }
}

class Person(n: String, l: String) {
    val name: String = n
    val lastName: String = l
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticaTheme {
    }
}