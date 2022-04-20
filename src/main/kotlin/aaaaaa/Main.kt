package aaaaaa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File
import java.io.PrintWriter
import kotlinx.cli.*
import java.io.BufferedReader
import java.io.FileWriter

@Composable
fun backgroundImage() {
    val image: Painter = painterResource("background.png")
    //Es relativo a su tamaño y ocupa todo el fondo. No hay barras negras/blancas
    Image(
        painter = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun botonAbrir(onClick: () -> Unit) {

    MaterialTheme {
        Button(onClick = onClick) {
            Text("Abrir")
        }
    }
}

fun abrelopapi(path:String) {

    //Como lo voy a abrir por CMD necesito la ruta pero quitandole el nombre del archivo al final, así que los separo aquí y posteriormente lo uso en el Runtime

    var rutaOriginal = path
    var rutaCortada = rutaOriginal.split("\\")
    var nombreArchivo = rutaCortada.last()
    rutaCortada = rutaCortada.dropLast(1)

    var rutaNueva = ""
    for (i in 0 until rutaCortada.size) {
        rutaNueva = rutaNueva + rutaCortada[i] + "\\"
    }

    Runtime.getRuntime().exec(arrayOf("cmd", "/c", "cd ${rutaNueva} & ${nombreArchivo}"))
}

@Composable
fun botonSalida(path:String){

    var pathSalida = "C:\\Users\\Ricar\\IdeaProjects\\ComposePractica\\src\\main\\resources\\salida.txt"

    val salida = PrintWriter(pathSalida)
    var filtro = true

    if (filtro == true) {File(path).forEachLine { salida.write(it+"\n") }}
    else{
        File(path).forEachLine {
            val listaIndulgente = mutableListOf<String>()
            var contador = 0

            File(path).forEachLine { listaIndulgente.add(it) }

             for (i in 0 until listaIndulgente.size) {
                 if (listaIndulgente[i].contains("f")) {contador++}
             }
            listaIndulgente.add("estas f :" + contador)

            listaIndulgente.forEach { salida.write(it + "\n") }


        }
    }
    salida.close()

    //escribo dentro deste archivo
    File(path).bufferedWriter().use {
        FileWriter("joja")
    }

    //Guarda contenidos de un archivo
    var listaDeFrases = File(path).bufferedReader().use(BufferedReader::readLines).toMutableList()

    //Pegar contenidos salida
    File(pathSalida).bufferedWriter().use{
        listaDeFrases.forEach { FileWriter(it + "\n") }
    }

}


@Composable
fun app() {
    val background = Color(0xFF234E70)
    var text = mutableStateOf("")
    //C:\Users\Ricar\IdeaProjects\ComposePractica\src\main\resources\entrada.txt

    Surface(color = Color.Gray, modifier = Modifier)
    {
        Row(Modifier.background(Color(0xFF085A92)).height(100.dp).fillMaxWidth().padding(12.dp)) {

            //.onKeyEvent { if (it.key == Key.Enter) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                TextField(value = text.value, onValueChange = { text.value = it }, label = { Text("Ruta") })

            }

        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Column(Modifier.padding(start = 4.dp).background(Color.Yellow)) {
                Column {
                    Column() {
                        botonAbrir {
                            if (validate(text.value)) {
                                abrelopapi(text.value)
                            }
                        }
                    }
                }
                Column(Modifier.padding(start = 4.dp)) { botonSalida(text.value) }
            }

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////

    }

}

fun main(args:Array<String>) = application {

    //implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
    val parser = ArgParser("jose")
    var input by parser.option(ArgType.String, shortName = "i", description = "es una i muy buena")
    var output by parser.option(ArgType.String, shortName = "o", description = "es una o muy buena")
    parser.parse(args)
    val i = input
    val o = output




    Window(onCloseRequest = ::exitApplication) {
        app()
    }

}




fun validate(ruta:String): Boolean {
    try {
        return File(ruta).isFile
    } catch (_: Exception) {
        return false
    }
}


