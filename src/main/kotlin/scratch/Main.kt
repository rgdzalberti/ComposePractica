package scratch

import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window

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
fun app(){
    var background = Color(0xFF234E70)
    var yellow = Color(0xffffeb46)
    var red = Color(0xffff0000)

    Surface(color = background, modifier = Modifier)
    {
        //Fondo
        backgroundImage()

        ////////////////////////////////////////////////Top footer////////////////////////////////////////////////////
        Row(Modifier.background(Color(0xFF085A92)).fillMaxWidth().height(50.dp).padding(12.dp))
        {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text("Trabajo Ricardo", fontSize = 23.sp,fontFamily = FontFamily.Serif,color = Color.White)
            }

            //textField()
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////Botones Centrales//////////////////////////////////////////////////
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,)
        {
            //botonContador()
            Column(Modifier.padding(start = 4.dp)) {
                Column(Modifier.padding(start = 35.dp)) { button()  }
                Column {
                    Row {
                        Column { buttonRead() }

                        //Le digo que localice el path de esta carpeta y que indique el nombre de entrada del archivo el cual será el indicado abajo
                        val userPath = System.getProperty("user.dir")
                        val archivoReferencia = "entrada.txt"
                        Column(Modifier.padding(start = 4.dp)) { buttonWrite(userPath + "\\src\\main\\resources\\" + archivoReferencia) }
                    }
                }
                //Column() { Row { button() ; button()   }}

            }
            Divider(color = Color.Blue, thickness = 1.dp) /* Horizontal Line */
            //Divider(color = Color.Red, modifier = Modifier .fillMaxHeight().width(1.dp)) /* Vertical Line */
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