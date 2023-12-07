package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exercise04.data.DBProduct
import com.example.exercise04.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product_details, container, false)
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProductData()

        binding.buttonReturnDB.setOnClickListener { _ ->
            findNavController().navigateUp()
        }
    }

    fun loadProductData() {
        val name = arguments?.getString("name")
        val description = arguments?.getString("description")
        val price = arguments?.getDouble("price")
        val rating = arguments?.getFloat("rating")
        val productType = arguments?.getInt("productType")

        binding.productNameTextViewDB.text = name
        binding.descriptionTextViewDB.text = description
        binding.priceTextViewDB.text = price.toString()
        binding.ratingBar2DB.rating = rating!!
        when (productType) {
            0 -> binding.productImageViewDB.setImageResource(R.drawable.food_icon)
            1 -> binding.productImageViewDB.setImageResource(R.drawable.water_icon)
            2 -> binding.productImageViewDB.setImageResource(R.drawable.cleaning_icon)
        }
    }
}