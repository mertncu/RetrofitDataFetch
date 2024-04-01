package com.mert.mvvmandroidarchitecture.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.CoroutineName
import android.widget.Toast
import com.mert.mvvmandroidarchitecture.data.repository.ApiClient.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteShoeActivity : AppCompatActivity(){

    private lateinit var textViewShoeId: TextView
    private lateinit var textViewBrand: TextView
    private lateinit var textViewModel: TextView
    private lateinit var textViewColor: TextView
    private lateinit var textViewSize: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var buttonDeleteShoe: Button
    private lateinit var buttonBack: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_shoe)
        setTheme(R.style.Theme_AppCompat)

        textViewShoeId = findViewById(R.id.textViewShoeId)
        textViewBrand = findViewById(R.id.textViewBrand)
        textViewModel = findViewById(R.id.textViewModel)
        textViewColor = findViewById(R.id.textViewColor)
        textViewSize = findViewById(R.id.textViewSize)
        textViewPrice = findViewById(R.id.textViewPrice)
        buttonDeleteShoe = findViewById(R.id.buttonDeleteShoe)
        buttonBack = findViewById(R.id.buttonBack)

        val shoeId = intent.getIntExtra("shoeId", -1)

        textViewShoeId.setText(shoeId.toString())

        buttonBack.setOnClickListener {
            finish()
        }

        buttonDeleteShoe.setOnClickListener {
            val shoeId = textViewShoeId.text.toString().toIntOrNull()

            if (shoeId != null) {
                deleteShoe(shoeId)
            } else {
                showToast("Please fill all fields.")
            }
        }
    }

    private fun deleteShoe(shoeId: Int) {
        val apiService = retrofit.create(ApiService::class.java)
        CoroutineScope(Dispatchers.IO + CoroutineName("deleteShoe")).launch {
            val response = apiService.deleteShoe(shoeId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    showToast("Shoe deleted successfully.")
                    finish()
                } else {
                    showToast("Error occurred.")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}