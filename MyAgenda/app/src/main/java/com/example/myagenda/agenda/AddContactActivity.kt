package com.example.myagenda.agenda

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myagenda.databinding.ActivityAddContactBinding


class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {

                ContactRepository.contacts.add(
                    Contact(name, phone)
                )

                Toast.makeText(this, "Contato adicionado!", Toast.LENGTH_SHORT).show()

                binding.edtName.text.clear()
                binding.edtPhone.text.clear()

            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}