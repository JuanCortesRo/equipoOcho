package com.equipoocho.dogapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.equipoocho.dogapp.ui.common.applyStatusBarPadding

/**
 * Single-Activity: solo aloja el NavHostFragment (res/navigation/nav_graph.xml).
 *
 * Las pantallas empiezan debajo de la barra de estado en todas las versiones de Android: el contenedor
 * de fragments recibe ese alto como padding y su fondo gris oscuro pinta la franja de la barra de estado.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.nav_host_fragment).applyStatusBarPadding()
    }
}
