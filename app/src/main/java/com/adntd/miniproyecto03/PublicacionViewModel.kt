package com.adntd.miniproyecto03

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PublicacionFormState(
    val titulo: String = "",
    val descripcion: String = "",
    val fotoUri: String? = null,
    val fecha: Long = System.currentTimeMillis(),
    val guardando: Boolean = false,
    val mensaje: String? = null
)

class PublicacionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PublicacionRepository(DatabaseProvider.get(application).publicacionDao())

    private val _form = MutableStateFlow(PublicacionFormState())
    val form: StateFlow<PublicacionFormState> = _form.asStateFlow()

    private val _publicaciones = MutableStateFlow<List<Publicacion>>(emptyList())
    val publicaciones: StateFlow<List<Publicacion>> = _publicaciones.asStateFlow()

    init {
        viewModelScope.launch {
            repository.obtenerTodas().collect { lista ->
                _publicaciones.value = lista
            }
        }
    }

    fun onTituloChange(v: String) = _form.update { it.copy(titulo = v) }
    fun onDescripcionChange(v: String) = _form.update { it.copy(descripcion = v) }
    fun onFotoUriChange(uri: String?) = _form.update { it.copy(fotoUri = uri) }
    fun onFechaChange(fecha: Long) = _form.update { it.copy(fecha = fecha) }

    fun guardar() {
        val f = _form.value
        if (f.titulo.isBlank()) {
            _form.update { it.copy(mensaje = "El título es obligatorio") }
            return
        }
        viewModelScope.launch {
            _form.update { it.copy(guardando = true, mensaje = null) }
            repository.guardar(
                Publicacion(
                    titulo = f.titulo.trim(),
                    descripcion = f.descripcion.trim(),
                    fotoUri = f.fotoUri,
                    fecha = f.fecha
                )
            )
            _form.value = PublicacionFormState()
            _form.update { it.copy(mensaje = "Publicación guardada") }
        }
    }

    fun limpiarMensaje() = _form.update { it.copy(mensaje = null) }
}