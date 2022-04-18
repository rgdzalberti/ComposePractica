package otrave

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import scratch.DatosArchivo

@Composable
fun botonOutput(){

    Button(
        onClick = { procesarArchivo() },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("ㅤOutputㅤ", fontSize = 11.sp,fontFamily = FontFamily.Serif,color = Color.White)
    }

}

@Composable
fun botonOpen(path: String) {

    Button(
        onClick = {

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
        },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Green)
    ) {
        Text("ㅤAbrirㅤ", fontSize = 11.sp, fontFamily = FontFamily.Serif, color = Color.White)
    }
}
