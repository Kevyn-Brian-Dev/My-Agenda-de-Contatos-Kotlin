package com.example.myagenda.agenda.telas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myagenda.agenda.data.AppDatabase
import com.example.myagenda.agenda.model.Contact
import com.example.myagenda.databinding.ActivityAddContactBinding
import kotlinx.coroutines.launch

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.Companion.getDatabase(this)
        val contactDao = database.contactDao()

        binding.btnSave.setOnClickListener {

            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {

                lifecycleScope.launch {
                    contactDao.insert(
                        Contact(name = name, phone = phone)
                    )

                    Toast.makeText(this@AddContactActivity, "Contato adicionado ao ROOM!", Toast.LENGTH_SHORT).show()

                    binding.edtName.text.clear()
                    binding.edtPhone.text.clear()
                }

            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}