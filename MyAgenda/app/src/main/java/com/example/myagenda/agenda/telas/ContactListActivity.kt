package com.example.myagenda.agenda.telas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myagenda.agenda.data.AppDatabase
import com.example.myagenda.databinding.ActivityContactListBinding
import kotlinx.coroutines.launch

class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.Companion.getDatabase(this)
        val contactDao = database.contactDao()

        adapter = ContactAdapter(emptyList()) { contact ->
            lifecycleScope.launch {
                contactDao.delete(contact)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            contactDao.getAllContacts().collect { listaContatos ->
                adapter.updateList(listaContatos)
            }
        }
    }
}