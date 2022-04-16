package scratch

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import java.io.File

class DatosArchivo(pathReferencia: String){

    val pathReferencia = pathReferencia
    val userPath = System.getProperty("user.dir")

    val nombreOutput = "salida.txt"
    val outputArchivo = userPath + "\\src\\main\\resources\\" + nombreOutput
    var archivoSalida = File(outputArchivo)

    fun procesarArchivo(){

        //Hago un cambio según los contenidos del archivo y lo outputeo

        if (archivoSalida.exists())
        {
            archivoSalida.delete()
            procesarArchivo()
        }
        else{ escribirOutput() }
    }

    fun escribirOutput(){
        val contenidoInput = mutableListOf<String>()
        File(pathReferencia).useLines { lines -> lines.forEach { contenidoInput.add(it) }}

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
fun buttonRead(){}

@Composable
fun buttonWrite(pathReferencia: String){

    val accesoDatosArchivo = DatosArchivo(pathReferencia)

    Button(
        onClick = { accesoDatosArchivo.procesarArchivo() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("OUTPUT", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }

}