package com.example.mydatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydatabasedemo.database.MyDatabase
import com.example.mydatabasedemo.databinding.ActivityMainBinding
import com.example.mydatabasedemo.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inside database we got several Dao object
       val dao = MyDatabase.getInstance(applicationContext).productDao

        binding.btnInsert.setOnClickListener{
            val newProduct = Product(0, "iPhone", 3500.00)

            //throw to backend process, cannot run in on main thread
            CoroutineScope(IO).launch {
                dao.insertProduct((newProduct))
            }


        }

        binding.btnGet.setOnClickListener{

            var proName = ""

            CoroutineScope(IO).launch {
                val productlist = dao.getAllProduct()

                for(p in productlist)
                    proName += p.name + "\n"

                //UI process need to run on the main thread
                CoroutineScope(Main).launch {
                    binding.tvResult.text = proName
                }

            }

        }

    }
}