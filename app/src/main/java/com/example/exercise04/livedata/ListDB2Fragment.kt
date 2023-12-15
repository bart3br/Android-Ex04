package com.example.exercise04.livedata

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise04.R
import com.example.exercise04.data.DBProduct
import com.example.exercise04.databinding.CardViewDesignBinding
import com.example.exercise04.databinding.FragmentListDB2Binding


class ListDB2Fragment : Fragment() {
    private lateinit var binding: FragmentListDB2Binding
    private lateinit var myViewModel: MyViewModel
    private lateinit var adapter: MyDB2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDB2Binding.inflate(inflater, container, false)
        //return binding.root
        val recView = binding.recyclerviewDB
        recView.layoutManager = LinearLayoutManager(requireContext())
        val repository = Product2Repository.getInstance(requireContext())
        myViewModel = MyViewModel(repository!!)
        adapter = MyDB2Adapter(myViewModel.products, myViewModel)
        recView.adapter = adapter

        myViewModel.products.observe(viewLifecycleOwner, Observer { items ->
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("item_add", viewLifecycleOwner) { _, bundle ->
            val productName = bundle.getString("name", "default product")
            val productDescription = bundle.getString("description", "some description")
            val productType = bundle.getInt("type", 0)
            val productPrice = bundle.getDouble("price", 10.0)
            val productRating = bundle.getFloat("rating", 3.0f)
            val newProduct = DBProduct(
                productName,
                productDescription,
                productType,
                productPrice,
                productRating
            )
            myViewModel.addProduct(newProduct)
        }

        parentFragmentManager.setFragmentResultListener("item_modify", viewLifecycleOwner) { _, bundle ->
            run {
                val productId = bundle.getInt("id", 0)
                val productName = bundle.getString("name", "default product")
                val productDescription = bundle.getString("description", "some description")
                val productType = bundle.getInt("type", 0)
                val productPrice = bundle.getDouble("price", 10.0)
                val productRating = bundle.getFloat("rating", 3.0f)
                myViewModel.updateProduct(productId, productName, productDescription, productType, productPrice, productRating)
            }
        }

        binding.addProductActionButtonDB.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("fragment_mode", 0) // 0 for add, 1 for edit
            findNavController().navigate(R.id.action_to_nav_product_add_d_b2, bundle)
        }
    }

    inner class MyDB2Adapter(var data: LiveData<List<DBProduct>>, private val myViewModel: MyViewModel)
        : RecyclerView.Adapter<MyDB2Adapter.ViewHolder>() {
        private val MAX_TEXT_LENGTH = 20

        override fun getItemCount(): Int {
            return data.value?.size ?: 0
        }

        inner class ViewHolder(viewHolder: CardViewDesignBinding) :
            RecyclerView.ViewHolder(viewHolder.root) {
            val imageView = viewHolder.imageview
            val textView = viewHolder.textView
            val textView2 = viewHolder.textView2
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = CardViewDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val position = holder.getAdapterPosition()
            val product = data.value?.get(position)!! ?: return

            when (product.productType) {
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

            //display short description
            holder.textView.text = product.name
            if (product.description!!.length < MAX_TEXT_LENGTH)
                holder.textView2.text = product.description
            else
                holder.textView2.text = product.description!!.removeRange(MAX_TEXT_LENGTH, product.description!!.length) + "..."


            //display item details on click
            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("fragment_mode", 1) // 0 for add, 1 for edit
                bundle.putInt("id", product.id)
                bundle.putString("name", product.name)
                bundle.putString("description", product.description)
                bundle.putInt("type", product.productType)
                bundle.putDouble("price", product.price)
                bundle.putFloat("rating", product.rating)

                //Log.d("MyDB2Adapter", "onBindViewHolder: $bundle")
                findNavController().navigate(R.id.action_to_nav_product_details2, bundle)
            }

            //delete item on long click
            holder.itemView.setOnLongClickListener {
                myViewModel.deleteProduct(product)
                true
            }
        }
    }
}