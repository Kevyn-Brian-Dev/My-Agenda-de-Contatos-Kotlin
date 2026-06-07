package com.example.myagenda.agenda.telas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myagenda.agenda.model.Contact
import com.example.myagenda.databinding.ItemContactBinding

class ContactAdapter(

    private var list: List<Contact>,
    private val onDeleteClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = list[position]

        holder.binding.txtName.text = contact.name
        holder.binding.txtPhone.text = contact.phone

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(contact)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<Contact>) {
        this.list = newList
        notifyDataSetChanged()
    }
}