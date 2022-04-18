package scratch

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

class DatosArchivo(){

    var pathReferencia = mutableStateOf("")
    val userPath = System.getProperty("user.dir")

    /////////////////////Datos para el output del archivo/////////////////////////
    val nombreOutput = "salida.txt"
    val outputArchivo = userPath + "\\src\\main\\resources\\" + nombreOutput
    var archivoSalida = File("C:\\Users\\Ricar\\IdeaProjects\\ComposePractica\\src\\main")
    /////////////////////////////////////////////////////////////////////////////

    fun procesarArchivo(){

        //Hago un cambio según los contenidos del archivo y lo outputeo
        println("AAAAAAAAAAAAAAAAAA")
        println("valor PathReferencia.value 2 ->" + pathReferencia.value)
        println("AAAAAAAAAAAAAAAAAA")

        if (archivoSalida.exists())
        {
            archivoSalida.delete()
            procesarArchivo()
        }
        else{escribirOutput()}
    }

    fun escribirOutput(){
        println(pathReferencia.value)

        val contenidoInput = mutableListOf<String>()
        File(pathReferencia.value).useLines { lines -> lines.forEach { contenidoInput.add(it) }}

        //Creo la variable filtro que indicará si queremos que los contenidos del archivo sean modificados con un paso extra
        var filtro = true
        if (filtro==true) {
            filtro(contenidoInput)

            var contenidoInputConcatenado:String = ""
            contenidoInput.forEach { contenidoInputConcatenado = contenidoInputConcatenado + "\n" + it }

             archivoSalida.writeText(contenidoInputConcatenado)
        }
        else {
            contenidoInput.forEach { archivoSalida.writeText(it) }
        }


    }

    fun filtro(contenidoInput : MutableList<String>): MutableList<String>{
        var contadorFrases = contenidoInput.count()

        contenidoInput.add("======================================================")
        contenidoInput.add("El archivo original contenia ${contadorFrases} líneas")
        contenidoInput.add("======================================================")

        return contenidoInput
    }


}

@Composable
fun button(){

    var buttonColor = Color(0xFFFFE77A)
    var twitterBlue = Color(0xFF00ACEE)
    Button(
        onClick = { TODO() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = twitterBlue)
    ) {
        Text("File", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }

}

@Composable
fun buttonValidate(text: MutableState<String>){

    val accesoDatosArchivo = DatosArchivo()
    println("=====================")
    println("VALOR DE TEXT.VALUE ->" + text.value)
    accesoDatosArchivo.pathReferencia.value = text.value
    println("VALOR pathReferencia.VALUE ->" + accesoDatosArchivo.pathReferencia.value)
    println("=====================")
    //{accesoDatosArchivo.pathReferencia.value = text.value}

    Button(
        onClick = { TODO() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Yellow)
    ) {
        Text("ㅤㅤㅤValidarㅤㅤㅤ", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.Black,)
    }



}

@Composable
fun buttonWrite(){

    val accesoDatosArchivo = DatosArchivo()

    Button(
        onClick = { accesoDatosArchivo.procesarArchivo() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("ㅤOutputㅤ", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }

}

@Composable
fun buttonOpen(){

    val accesoDatosArchivo = DatosArchivo()

    Button(
        onClick = {

            //Como lo voy a abrir por CMD necesito la ruta pero quitandole el nombre del archivo al final, así que los separo aquí y posteriormente lo uso en el Runtime

            var rutaOriginal = accesoDatosArchivo.pathReferencia.value
            var rutaCortada = rutaOriginal.split("\\")
            var nombreArchivo = rutaCortada.last()
            rutaCortada = rutaCortada.dropLast(1)

            var rutaNueva = ""
            for (i in 0 until rutaCortada.size) {
                rutaNueva = rutaNueva + rutaCortada[i] + "\\"
            }

            Runtime.getRuntime().exec(arrayOf("cmd", "/c", "cd ${rutaNueva} & ${nombreArchivo}")) },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("ㅤAbrirㅤ", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }

}
