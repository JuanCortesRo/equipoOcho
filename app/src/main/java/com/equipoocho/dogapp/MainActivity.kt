package com.equipoocho.dogapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Single-Activity: solo aloja el NavHostFragment (res/navigation/nav_graph.xml). */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
