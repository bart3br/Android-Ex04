package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = arguments?.getSerializable("product") as ProductModel
        binding.productNameTextView.text = product.name
        binding.productDescriptionTextView.text = product.description
        binding.productPriceTextView.text = product.price.toString()
        binding.productRatingBar.rating = product.rating
        binding.productImageView.setImageResource(product.image)
    }*/
}