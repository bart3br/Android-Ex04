package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise04.data.DBProduct
import com.example.exercise04.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var product: DBProduct
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
        product = arguments?.getSerializable("product_key") as DBProduct

        //display product info
        binding.productNameTextViewDB.text = product.name
        binding.descriptionTextViewDB.text = product.description
        binding.priceTextViewDB.text = product.price.toString()
        binding.ratingBar2DB.rating = product.rating
        when (product.productType) {
            0 -> binding.productImageViewDB.setImageResource(R.drawable.food_icon)
            1 -> binding.productImageViewDB.setImageResource(R.drawable.water_icon)
            2 -> binding.productImageViewDB.setImageResource(R.drawable.cleaning_icon)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(product: DBProduct): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle().apply {
                putSerializable("product_key", product)
            }
            fragment.arguments = args
            return fragment
        }
    }
}