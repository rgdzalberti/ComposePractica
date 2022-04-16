import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import java.io.File

interface FlujoTexto{

    var inputFileName: String
    var outputFileName: String
    var nombreArchivo : String

}

class DatosFile(path: String): FlujoTexto{

    var path = path

    override var inputFileName = "entrada.txt"
    override var outputFileName = "salida.txt"
    override var nombreArchivo = path + "\\" + outputFileName

    val miArchivo = File(nombreArchivo)

    fun writeOutput(){

        var rutaInput = ".\\main\\resources" + "\\" + inputFileName
        var contenidoInput = mutableListOf<String>()

        File(rutaInput).useLines { lines -> lines.forEach { contenidoInput.add(it) }}

         contenidoInput.forEach { miArchivo.writeText(it) }
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
fun buttonRead(path: String){

    var accesoDatosFile = DatosFile(path)

    Button(
        onClick = { TODO() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("INPUT", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }
}

@Composable
fun buttonWrite(path: String){

    var accesoDatosFile = DatosFile(path)

    Button(
        onClick = { accesoDatosFile.writeOutput() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("OUTPUT", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }
}