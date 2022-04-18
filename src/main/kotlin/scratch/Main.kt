package scratch

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource


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

    //var accesoDatosArchivo = DatosArchivo(text)

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
        Row(Modifier.background(Color(0xFF085A92)).fillMaxWidth().height(130.dp).padding(12.dp))
        {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
            {
                //Text("Trabajo Ricardo", fontSize = 23.sp,fontFamily = FontFamily.Serif,color = Color.White)
                campoTexto()
                Column { buttonValidate(text) }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////Botones Centrales//////////////////////////////////////////////////
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,)
        {
            Column(Modifier.padding(start = 4.dp)) {
                Column { Column() { buttonWrite() } /* /*userPath + "\\src\\main\\resources\\" + archivoReferencia*/*/ }
                Column(Modifier.padding(start = 4.dp)) { buttonOpen() }
            }
            Divider(color = Color.Blue, thickness = 1.dp) /* Horizontal Line */
            Column(Modifier.padding(start = 4.dp)) { button() }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}