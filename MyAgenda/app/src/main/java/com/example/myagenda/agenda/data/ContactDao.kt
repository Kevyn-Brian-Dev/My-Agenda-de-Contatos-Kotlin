package com.example.myagenda.agenda.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myagenda.agenda.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contacts_table ORDER BY name ASC")
    fun getAllContacts(): Flow<List<Contact>>

    @Delete
    suspend fun delete(contact: Contact)
}