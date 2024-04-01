package com.mert.mvvmandroidarchitecture.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.data.repository.ApiClient
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import com.mert.mvvmandroidarchitecture.ui.adapter.ShoeAdapter

class GetShoeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var ShoeAdapter: ShoeAdapter

    private lateinit var buttonBackMain: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_shoe)
        setTheme(R.style.Theme_AppCompat)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonBackMain = findViewById(R.id.buttonBackMain)
        buttonBackMain.setOnClickListener {
            finish()
        }

        getProductList()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getProductList() {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val productList: List<Shoe> = apiService.getShoes()
            ShoeAdapter = ShoeAdapter(productList)
            recyclerView.adapter = ShoeAdapter
        }
    }
}