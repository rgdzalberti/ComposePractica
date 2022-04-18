package otrave

import java.io.File

var archivoSalida = File("C:\\Users\\Ricar\\IdeaProjects\\ComposePractica\\src\\main\\resources\\salida.txt")

fun procesarArchivo(){

    //Hago un cambio según los contenidos del archivo y lo outputeo

    if (archivoSalida.exists())
    {
        archivoSalida.delete()
        procesarArchivo()
    }
    else{escribirOutput()}
}

fun escribirOutput(){

    val contenidoInput = mutableListOf<String>()
    File(text.value).useLines { lines -> lines.forEach { contenidoInput.add(it) }}

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