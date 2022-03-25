package com.vnguy23.numberpuzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.vnguy23.numberpuzzle.ui.main.WelcomeFragment
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NumberPuzzle)
        Timer().schedule(10000){
            TODO("Do somefing")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}