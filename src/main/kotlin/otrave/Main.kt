package otrave

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import java.io.File

var text = mutableStateOf("")

@Composable
fun backgroundImage() {
    val image: Painter = painterResource("background.png")
    //Es relativo a su tamaño y ocupa todo el fondo. No hay barras negras/blancas
    Image(painter = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize())
}

@Composable
fun campoTexto(){

    TextField(value = text.value, onValueChange = { text.value = it }, label = { Text("Ruta") })

}

@Composable
fun app(){
    var background = Color(0xFF234E70)
    var yellow = Color(0xffffeb46)
    var red = Color(0xffff0000)

    Surface(color = background, modifier = Modifier)
    {
        //Fondo
        backgroundImage()

        ////////////////////////////////////////////////Top footer////////////////////////////////////////////////////
        Row(Modifier.background(Color(0xFF085A92)).fillMaxWidth().height(100.dp).padding(12.dp))
        {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
            {
                campoTexto()
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////Botones Centrales//////////////////////////////////////////////////
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,)
        {
            Column(Modifier.padding(start = 4.dp)) {
                Column { Column() { botonOutput() }}
                Column(Modifier.padding(start = 4.dp)) {botonOpen(text.value) }
            }
            //Divider(color = Color.Blue, thickness = 1.dp) /* Horizontal Line */
            //Column(Modifier.padding(start = 4.dp)) { button() }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}

fun main() = application  {

    Window(onCloseRequest = ::exitApplication) {
        app()
    }

}
