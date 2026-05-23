package com.example.myagenda.agenda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myagenda.agenda.AddContactActivity
import com.example.myagenda.agenda.ContactListActivity
import com.example.myagenda.databinding.ActivityMainBinding

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
    }
}