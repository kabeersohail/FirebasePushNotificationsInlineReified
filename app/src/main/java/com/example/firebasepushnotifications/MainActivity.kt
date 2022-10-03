package com.example.firebasepushnotifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marvel: Marvel = Marvel(Character.SpiderMan, Power.SpiderPower)

        val map: Map<String, Any> = mapOf(
            "Fav" to marvel
        )

        rND(map)
    }

    private fun rND(map: Map<String, Any>) {

        val x = map["Fav"]

        val y = when (x) {
            is Marvel -> x
            else -> throw Exception("invalid parameter")
        }

        val favSuperHero: Marvel = try {
            map has "Fav"
        } catch (e: Exception) {
            throw e
        }
    }

}

inline infix fun <reified T> Map<String, Any?>?.has(name: String): T = when {
    this == null -> throw Exception("Missing params")
    this.isEmpty() -> throw Exception("Missing params")
    !this.containsKey(name) -> throw  Exception("Missing params")
    this[name] !is T -> throw Exception("Invalid params")
    (this[name] is String) && (this[name] as String).isBlank() -> throw Exception("Missing params")
    this[name] is T -> this[name] as T
    else -> throw Exception("Missing params")
}

data class Marvel(val name: Character, val power: Power)

sealed class Character {
    object SpiderMan : Character()
    object SuperMan : Character()
    object Thor : Character()
    object AquaMan : Character()
    object BatMan : Character()
    object IronMan : Character()
}

sealed class Power {
    object SpiderPower : Power()
    object Levitation : Power()
    object IAmWorthy : Power()
    object KingOfWater : Power()
    object Technology : Power()
    object Intelligence : Power()
}