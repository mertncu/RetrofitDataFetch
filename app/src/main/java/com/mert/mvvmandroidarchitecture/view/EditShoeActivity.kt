package com.mert.mvvmandroidarchitecture.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.data.repository.ApiClient
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditShoeActivity : AppCompatActivity() {

    private lateinit var editTextShoeId: EditText
    private lateinit var editTextBrand: EditText
    private lateinit var editTextColor: EditText
    private lateinit var editTextModel: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextSize: EditText
    private lateinit var buttonUpdateShoe: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_shoe)
        setTheme(R.style.Theme_AppCompat)

        editTextBrand = findViewById(R.id.editTextBrand)
        editTextColor = findViewById(R.id.editTextColor)
        editTextModel = findViewById(R.id.editTextModel)
        editTextPrice = findViewById(R.id.editTextPrice)
        editTextSize = findViewById(R.id.editTextSize)
        buttonUpdateShoe = findViewById(R.id.buttonUpdateShoe)
        buttonBack = findViewById(R.id.buttonBack)
        editTextShoeId = findViewById(R.id.editTextShoeId)

        val shoeId = intent.getIntExtra("shoeId", -1)
        val shoeBrand = intent.getStringExtra("shoeBrand")
        val shoeColor = intent.getStringExtra("shoeColor")
        val shoeModel = intent.getStringExtra("shoeModel")
        val shoePrice = intent.getIntExtra("shoePrice", -1)
        val shoeSize = intent.getIntExtra("shoeSize", -1)

        editTextShoeId.setText(shoeId.toString())
        editTextBrand.setText(shoeBrand)
        editTextColor.setText(shoeColor)
        editTextModel.setText(shoeModel)
        editTextPrice.setText(shoePrice.toString())
        editTextSize.setText(shoeSize.toString())

        buttonBack.setOnClickListener {
            finish()
        }

        buttonUpdateShoe.setOnClickListener {
            val shoeId = editTextShoeId.text.toString().toIntOrNull()
            val shoeBrand = editTextBrand.text.toString()
            val shoeColor = editTextColor.text.toString()
            val shoeModel = editTextModel.text.toString()
            val shoePrice = editTextPrice.text.toString().toIntOrNull()
            val shoeSize = editTextSize.text.toString().toIntOrNull()



            if (shoeId != null && shoeBrand.isNotEmpty() && shoeColor.isNotEmpty() && shoeModel.isNotEmpty() && shoePrice != null && shoeSize != null) {
                val updatedShoe = Shoe(shoeBrand, shoeColor, shoeId, shoeModel, shoePrice, shoeSize)
                updateShoe(updatedShoe)
            } else {
                showToast("Please fill all fields.")
            }
        }
    }

    private fun updateShoe(shoe: Shoe) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.retrofit.create(ApiService::class.java).updateShoe(shoe.id, shoe)
                withContext(Dispatchers.Main) {
                    if (response != null && response.isSuccessful) {
                        showToast("Shoe updated successfully.")
                    } else {
                        showToast("Error updating shoe.")
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