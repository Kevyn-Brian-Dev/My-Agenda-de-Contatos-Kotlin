package com.example.myagenda.agenda.telas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.myagenda.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "user_preferences")
val NOTIFICATION_KEY = booleanPreferencesKey("notifications_enabled")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        binding.btnView.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }

        lifecycleScope.launch {
            readFilterPreference().collect { isEnabled ->
                if (isEnabled) {
                    Toast.makeText(this@MainActivity, "Modo Notificação: Ativado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.root.setOnLongClickListener {
            lifecycleScope.launch {
                saveFilterPreference()
            }
            true
        }
    }

    private suspend fun saveFilterPreference() {
        dataStore.edit { preferences ->
            val currentStatus = preferences[NOTIFICATION_KEY] ?: false
            preferences[NOTIFICATION_KEY] = !currentStatus
            Toast.makeText(this, "Preferência do DataStore alterada!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFilterPreference(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[NOTIFICATION_KEY] ?: false
        }
    }
}