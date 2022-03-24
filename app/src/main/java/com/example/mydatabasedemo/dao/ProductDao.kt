package com.example.mydatabasedemo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mydatabasedemo.entity.Product

//database access object file
@Dao
interface ProductDao {

    @Insert
    fun insertProduct(p: Product)

    @Query ("Select * From product_table")
    fun getAllProduct(): List<Product>

    @Query ("Select * From product_table Where price <= :price")
    fun getProductByPrice(price: Double): List<Product>

}