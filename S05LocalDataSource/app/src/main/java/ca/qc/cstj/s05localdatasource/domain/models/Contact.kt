package ca.qc.cstj.s05localdatasource.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @ColumnInfo(name = "firstName") val firstName: String,
    val lastName: String,
    var isOnline: Boolean)
{
    @PrimaryKey(autoGenerate = true) var idContact: Int = 0
    val fullName:String get() = "$firstName $lastName"
}