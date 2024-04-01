package com.mert.mvvmandroidarchitecture.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mert.mvvmandroidarchitecture.R
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.view.DeleteShoeActivity
import com.mert.mvvmandroidarchitecture.view.EditShoeActivity

class ShoeAdapter(private val shoeList: List<Shoe>) : RecyclerView.Adapter<ShoeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shoe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = shoeList[position]
        holder.bind(shoe)
    }

    override fun getItemCount(): Int {
        return shoeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        private val brandTextView: TextView = itemView.findViewById(R.id.brandTextView)
        private val modelTextView: TextView = itemView.findViewById(R.id.modelTextView)
        private val colorTextView: TextView = itemView.findViewById(R.id.colorTextView)
        private val sizeTextView: TextView = itemView.findViewById(R.id.sizeTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        private val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)

        init {
            deleteIcon.setOnClickListener {
                val shoe = shoeList[adapterPosition]
                val intent = Intent(itemView.context, DeleteShoeActivity::class.java)
                intent.putExtra("shoeId", shoe.id)
                itemView.context.startActivity(intent)
            }
        }

        init {

            editIcon.setOnClickListener {
                val shoe = shoeList[adapterPosition]
                val intent = Intent(itemView.context, EditShoeActivity::class.java)
                intent.putExtra("shoeId", shoe.id)
                intent.putExtra("shoeBrand", shoe.brand)
                intent.putExtra("shoeModel", shoe.model)
                intent.putExtra("shoeColor", shoe.color)
                intent.putExtra("shoeSize", shoe.size)
                intent.putExtra("shoePrice", shoe.price)
                itemView.context.startActivity(intent)
            }
        }

        fun bind(shoe: Shoe){
            idTextView.text = shoe.id.toString()
            brandTextView.text = shoe.brand
            modelTextView.text = shoe.model
            colorTextView.text = shoe.color
            sizeTextView.text = shoe.size.toString()
            priceTextView.text = shoe.price.toString()
        }
    }
}