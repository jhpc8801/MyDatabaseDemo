package com.example.mydatabasedemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydatabasedemo.dao.ProductDao
import com.example.mydatabasedemo.entity.Product

//increase the version everytime make changes to the entity or add/remove table
@Database (entities = [Product::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract val productDao : ProductDao

    //like static variable and method
    companion object{

        @Volatile
        private var INSTANCE: MyDatabase? = null

        //context means activity
        fun getInstance(context: Context): MyDatabase {

            //if there are multiple thread, there will only one run at a time
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "Mydatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }



}