package com.example.mydatabasedemo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity create a table
@Entity (tableName = "product_table")
data class Product(
    @PrimaryKey (autoGenerate = true)
    var id: Int,

    @ColumnInfo (name = "description")
    var name: String,

    @ColumnInfo
    var price: Double
)
