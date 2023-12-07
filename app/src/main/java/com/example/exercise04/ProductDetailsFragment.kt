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

        val fab = view.findViewById<View>(R.id.buttonModifyDB)
        fab.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putInt("fragment_mode", 1) // 0 for add, 1 for edit
            bundle.putInt("product_id", arguments?.getInt("product_id")!!)
            bundle.putString("name", arguments?.getString("name"))
            bundle.putString("description", arguments?.getString("description"))
            bundle.putDouble("price", arguments?.getDouble("price")!!)
            bundle.putFloat("rating", arguments?.getFloat("rating")!!)
            bundle.putInt("productType", arguments?.getInt("productType")!!)

            findNavController().navigate(R.id.action_to_nav_product_add_d_b, bundle)
        })
    }

    private fun loadProductData() {
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