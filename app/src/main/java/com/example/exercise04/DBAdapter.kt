package com.example.exercise04

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise04.data.DBProduct
import com.example.exercise04.data.ProductRepository

class DBAdapter (var data: MutableList<DBProduct>, var productRepo: ProductRepository) : RecyclerView.Adapter<DBAdapter.ViewHolder>() {
    private val MAX_TEXT_LENGTH = 20
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = data[position]

        when (ItemsViewModel.productType) {
            0 -> {
                holder.imageView.setImageResource(R.drawable.food_icon)
            }
            1 -> {
                holder.imageView.setImageResource(R.drawable.water_icon)
            }
            2 -> {
                holder.imageView.setImageResource(R.drawable.cleaning_icon)
            }
        }

        holder.textView.text = ItemsViewModel.name
        if (ItemsViewModel.description!!.length < MAX_TEXT_LENGTH)
            holder.textView2.text = ItemsViewModel.description
        else
            holder.textView2.text = ItemsViewModel.description!!.removeRange(MAX_TEXT_LENGTH, ItemsViewModel.description!!.length) + "..."
        //display item details
        holder.itemView.setOnClickListener {
            if(onClickListener != null){
                onClickListener!!.onClick(position, ItemsViewModel)
            }
        }

        holder.itemView.setOnLongClickListener {
            if (productRepo.deleteItem(data[position])) {
                data = productRepo.getData()!!
                notifyDataSetChanged()
            }
            true
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    open fun getProduct(position: Int): DBProduct {
        return data[position]
    }

    fun addProduct(product: DBProduct){
        data.add(product)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun deleteProduct(product: DBProduct) {
        data.remove(product)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(position: Int, product: DBProduct)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
    }

    companion object {
        private const val TAG = "DBAdapter"
        private val DiffCallback = object : DiffUtil.ItemCallback<DBProduct>() {
            override fun areItemsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem == newItem
            }
        }
    }


}