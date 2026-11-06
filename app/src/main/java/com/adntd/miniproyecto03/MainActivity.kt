package com.adntd.miniproyecto03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adntd.miniproyecto03.ui.theme.MiniProyecto03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProyecto03Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val vm: PublicacionViewModel = viewModel()
                    var pantalla by remember { mutableStateOf("lista") }
                    if (pantalla == "lista") {
                        ListaScreen(
                            viewModel = vm,
                            onAgregar = { pantalla = "formulario" }
                        )
                    } else {
                        FormularioScreen(
                            viewModel = vm,
                            onVolver = { pantalla = "lista" }
                        )
                    }
                }
            }
        }
    }
}