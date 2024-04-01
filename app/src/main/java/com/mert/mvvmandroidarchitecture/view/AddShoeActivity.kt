package com.mert.mvvmandroidarchitecture.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import android.widget.Button
import android.widget.EditText
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.data.repository.ApiClient
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddShoeActivity : AppCompatActivity() {

    private lateinit var editTextBrand: EditText
    private lateinit var editTextColor: EditText
    private lateinit var editTextModel: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextSize: EditText
    private lateinit var buttonAddShoe: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shoe)
        setTheme(R.style.Theme_AppCompat)

        editTextBrand = findViewById(R.id.editTextBrand)
        editTextColor = findViewById(R.id.editTextColor)
        editTextModel = findViewById(R.id.editTextModel)
        editTextPrice = findViewById(R.id.editTextPrice)
        editTextSize = findViewById(R.id.editTextSize)
        buttonAddShoe = findViewById(R.id.buttonAddShoe)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonAddShoe.setOnClickListener {
            val shoeBrand = editTextBrand.text.toString()
            val shoeColor = editTextColor.text.toString()
            val shoeModel = editTextModel.text.toString()
            val shoePrice = editTextPrice.text.toString().toIntOrNull()
            val shoeSize = editTextSize.text.toString().toIntOrNull()

            if (shoeBrand.isNotEmpty() && shoeColor.isNotEmpty() && shoeModel.isNotEmpty() && shoePrice != null && shoeSize != null) {
                val shoe = Shoe(shoeBrand, shoeColor, 0, shoeModel, shoePrice, shoeSize)
                addShoe(shoe)
            } else {
                showToast("Please fill fields.")
            }
        }
    }

    private fun addShoe(shoe: Shoe) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.retrofit.create(ApiService::class.java).addShoes(shoe)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful == true) {
                        showToast("Failed to add shoe.")
                    } else {
                        showToast("Shoe Added Successfully")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.localizedMessage}")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

