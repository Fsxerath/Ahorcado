package com.example.ahorcado

import kotlin.random.Random

class logica {
    val nivel_1 = listOf<String>("abeto","actor","agudo","alado","altar","avion","azul","ciego","cines","clavo","coral","corea","cosas","costo","crudo","curar","dados","dagas","datos","dunas")
    val nivel_2 = listOf<String>("pensar","lúdico","además","soltar","grande","cambio","fuerte","equipo","suelta","muerte","origen","modelo","acción","" +
            "imagen","aunque","quitar","óptimo","zapato","dinero","alegre","dormir","medida","hombre","eterno","camino","cuidar","formar","crecer","objeto",
            "blanco","perder","enviar","factor","pintar","poseer","método","lograr","servir","cuenta","tiempo","fuerza","imitar","relato","cuerpo","llegar","" +
            "contar","prueba","sector","básico","asunto","saltar","fuente","efecto")
    val nivel_3 = listOf<String>("conocer","proceso","hermosa","mejorar","interes","aspecto","momento","tambien","mostrar","valores","función","durante","cumplir",
        "ofrecer","recibir","impacto","calidad","motivar","sistema","atender","aplicar","estudio","emocion","control","gracias","extraño","ilusion","caminar",
        "influir","golpear","intenso","sublime","guardar","resumen","mensaje","otorgar","generar","empezar","montaña","inferir","cliente","obligar","señalar","" +
        "afirmar","asistir","detalle","existir","plasmar","muestra","delgado","definir","armonia","exponer","termino","obtener","iconico","dificil","honesto"
        ,"urgente","indicar","ejercer","entidad","negocio","desafio","primero","incluir","castigo","posible","aquello","enfermo","escasez","avanzar","trabajo",
        "esencia","enfoque","enseñar","curioso","revelar","sentido","inmenso","energia","ordenar","asombro","opinion","escuela","columna","jornada","entrada",
        "detener","dirigir","parecer","visitar","sincero","imponer","reclamo","pequeño","redimir","ampliar","perdido","aceptar","limitar","arrojar","incitar",
        "confiar","derrota","secarse","esperar","aportar","extraer","memoria","tecnica","aptitud","campaña","habitar","ventaja","separar","maestro","afectar",
        "después","colocar","enfocar","antiguo","adición","extremo","estatus","abordar")

    fun generarPalabra(nivel: Int): String{
        var palabra_generada = when (nivel){
            0 -> nivel_1.get(Random.nextInt(nivel_1.size))
            1 -> nivel_2.get(Random.nextInt(nivel_2.size))
            2 -> nivel_3.get(Random.nextInt(nivel_3.size))
            else -> {"ERROR"}
        }
        return palabra_generada;

    }
    fun ocultar_Palabra(palabra: String): String{
        var Resultado=""
        for (i in 1.. palabra.length){
            Resultado+="_"
        }
        return Resultado
    }

    fun Intento(Palabra: String,Palabra_Oculta: String): String{
        var acumlador = Palabra_Oculta.toCharArray()
        var Letra : Char
        var bandera = false

        for (i in 0..4) {
            println(acumlador)
            println("Ingrese Letra: ")
            Letra = readln().single()
            for (i in 0..Palabra.length - 1) {
                if (Palabra.get(i) == Letra) {
                    acumlador[i] = Letra
                    bandera = true
                }
            }
            if (bandera == true) {
                println("INTENTO EXITOSO")
            }else{
                println("INTENTO FALLIDO")
            }
            bandera = false
            if(ImprimirArray(acumlador) == Palabra){
                break
            }

        }
        return ImprimirArray(acumlador)
    }

    fun ImprimirArray(arr: CharArray): String{
        var acum = ""
        for (i in arr.indices) {
            acum += arr[i]
        }
        return acum
    }

    fun terminarJuego (){
        val palabra = "holoa"
        if(Intento(palabra, ocultar_Palabra(palabra)) == palabra){
            println("HAS GANADO")
        }else{
            println("HAS PERDIDO")
        }
    }
}

fun main() {
    var obj = logica()
    obj.terminarJuego()
}