package com.example.ahorcado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityGameBinding
import com.example.ahorcado.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class Game : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private var palabra: String = "hola"
    private var palabraEncontrar: CharArray = ocultarPalabra(palabra)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtPalabra.text = imprimirArray(palabraEncontrar)
        binding.btnInsertar.setOnClickListener {
            var cont: Int = 0

            for (i in palabra.toCharArray()){
                binding.txtPalabra.text = imprimirArray(palabraEncontrar)
                if(i == ingresarLetra()){
                    palabraEncontrar[cont] = ingresarLetra()
                    Snackbar.make(binding.layout,"Se encontro la letra",Snackbar.LENGTH_LONG).show()
                }
                cont++
            }

        }
    }


    fun ocultarPalabra(word :String): CharArray{
        var Acum: String = ""
        for (i in 0 .. word.length ){
            Acum+="-"
        }
        return Acum.toCharArray()
    }
    fun imprimirArray(x: CharArray): String{
        var Acum: String = ""
        for (i in x.indices - 1){
            Acum += x[i]
        }
        return Acum
    }
    fun ingresarLetra() : Char{
        val letra = binding.txtLetra.text.toString().get(0)
        return letra
    }

}