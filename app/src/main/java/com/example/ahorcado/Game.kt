package com.example.ahorcado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import com.example.ahorcado.databinding.ActivityGameBinding
import com.example.ahorcado.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class Game : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private var palabra: String = ""
    private lateinit var palabraEncontrar: CharArray
    private var Contador_Intentos: Int = 0
    private val imgs = listOf(
        R.drawable.try1,
        R.drawable.try2,
        R.drawable.try3,
        R.drawable.try4,
        R.drawable.try5,
        R.drawable.try6,
        R.drawable.try7,
        R.drawable.try8,
        R.drawable.try9
    )
    private lateinit var timer: CountDownTimer //Count down timer
    private var millis: Long = 120000 //Game Time in milliseconds
    private val companies = listOf(
        "Google", "Microsoft", "Apple",
        "Amazon", "Facebook", "IBM", "Oracle",
        "Intel", "Cisco", "Dell",
        "HP", "Lenovo", "Samsung",
        "Sony", "Nokia", "Tesla",
        "SpaceX", "Uber", "Airbnb",
        "Netflix", "Twitter", "Snapchat",
        "Pinterest", "Zoom"
    )
    private val videogames = listOf(
        "TheLegendofZelda", "SuperMarioOdyssey", "Persona5",
        "FinalFantasy", "RedDeadRedemption", "TheWitcher",
        "HorizonZeroDawn", "Overwatch", "CallofDuty",
        "CounterStrike", "Minecraft", "Fortnite",
        "LeagueofLegends", "ApexLegends", "GrandTheftAutoV",
        "AssassinsCreedValhalla", "Cyberpunk2077", "DarkSouls",
        "Bloodborne", "NieRAutomata", "ResidentEvilVillage",
        "DeathStranding", "Control", "DOOMEternal", "HollowKnight"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        try {

            super.onCreate(savedInstanceState)
            binding = ActivityGameBinding.inflate(layoutInflater)
            setContentView(binding.root)


            //Getting the difficulty and the category words
            var gameMode = intent.getStringExtra("gameMode") //Saves the difficulty
            var category = intent.getStringExtra("categoryGame") //Saves de category

            //Setting the game time
            millis = when (gameMode?.lowercase()?.trim()) {
                "fácil" -> 120000    // 2 minutes
                "medio" -> 90000    // 1 minute and a half
                "dificil" -> 60000 // 1 minute
                else -> {
                    120000 // 2 minutes
                }
            }

            //Setting the word to find
            palabra = if (category?.lowercase()?.trim() == "videojuegos") {
                videogames[Random.nextInt(0, videogames.size)].lowercase()
            } else {
                companies[Random.nextInt(0, companies.size)].lowercase()
            }
            palabraEncontrar = ocultarPalabra(palabra)
            timer = object : CountDownTimer(millis, 1000) {
                override fun onTick(p0: Long) {
                    //Substracting seconds and showing on the text view
                    millis = p0
                    binding.timeRemeaning.text = format(millis)
                }

                override fun onFinish() {
                    binding.timeRemeaning.text = "00:00"
                    MostrarResultados(
                        "HAS PERDIDO", "Se acabo el tiempo :( \nLa palabra era" + palabra
                    )
                }
            }.start()
            binding.txtPalabra.text = imprimirArray(palabraEncontrar)
            binding.btnInsertar.setOnClickListener {

                var intento = false
                var cont: Int = 0
                for (i in palabra.toCharArray()) {
                    if (i == ingresarLetra()) {
                        palabraEncontrar[cont] = ingresarLetra()
                        intento = true
                    }
                    cont++
                }
                if (!intento) {
                    binding.img.setImageResource(imgs.get(Contador_Intentos))
                    Contador_Intentos++
                }

                println(Contador_Intentos)
                if (imprimirArray(palabraEncontrar) != palabra && Contador_Intentos > 8) {
                    MostrarResultados("HAS PERDIDO", "NO se encontro la palabra oculta")
                    Contador_Intentos = 0
                } else if (imprimirArray(palabraEncontrar) == palabra && Contador_Intentos <= 8) {
                    MostrarResultados("HAS GANADO", "Se encontro la palabra oculta")
                    Contador_Intentos = 0
                }
                binding.txtPalabra.text = imprimirArray(palabraEncontrar)
            }
        }catch (ex: java.lang.Exception){
            error(ex)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
    fun format(millis: Long): String {
        val min = millis / 1000 / 60
        val sec = millis / 1000 % 60
        return String.format("%02d:%02d",min,sec)
    }


    fun ocultarPalabra(word :String): CharArray{
        var Acum: String = ""
        for (i in 0 .. word.length ){
            Acum+="_"
        }
        return Acum.toCharArray()
    }

    fun imprimirArray(arr: CharArray): String{
        var acum = ""
        for (i in arr.indices - arr.lastIndex) {
            acum += arr[i]
        }
        return acum
    }
    fun ingresarLetra() : Char{
        var letra: Char
        if(binding.txtLetra.text.toString().isEmpty()){
            letra = '{'
        }else{
           letra = binding.txtLetra.text.toString().get(0)
        }
        return letra
    }

    private fun MostrarResultados(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
            finish()
        }
        builder.setCancelable(false)
        builder.show()
    }

}