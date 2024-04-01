package com.mert.mvvmandroidarchitecture.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.view.AddShoeActivity
import com.mert.mvvmandroidarchitecture.view.DeleteShoeActivity
import com.mert.mvvmandroidarchitecture.view.GetShoeActivity
import com.mert.mvvmandroidarchitecture.view.EditShoeActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addShoeButton: Button = findViewById(R.id.addShoeButton)
        val getShoeButton: Button = findViewById(R.id.getShoeButton)
        val editShoeButton: Button = findViewById(R.id.editShoeButton)
        val deleteShoeButton: Button = findViewById(R.id.deleteShoeButton)

        addShoeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddShoeActivity::class.java)
            startActivity(intent)
        }

        getShoeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GetShoeActivity::class.java)
            startActivity(intent)
        }

        editShoeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditShoeActivity::class.java)
            startActivity(intent)
        }

        deleteShoeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteShoeActivity::class.java)
            startActivity(intent)
        }

    }
}
