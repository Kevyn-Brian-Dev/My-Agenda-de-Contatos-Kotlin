package com.example.myagenda.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myagenda.databinding.ActivityContactListBinding


class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter(ContactRepository.contacts)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}