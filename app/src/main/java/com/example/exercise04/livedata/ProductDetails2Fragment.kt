package com.example.exercise04.livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.exercise04.R
import com.example.exercise04.databinding.FragmentProductDetails2Binding

class ProductDetails2Fragment : Fragment() {

    lateinit var binding : FragmentProductDetails2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product_details2, container, false)
        binding = FragmentProductDetails2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonModifyDB.setOnClickListener {
            parentFragmentManager.setFragmentResult("msgtoedit", bundleOf(
                "name" to binding.productNameTextViewDB.text.toString(),
                "description" to binding.descriptionTextViewDB.text.toString(),
                "price" to binding.priceTextViewDB.text.toString(),
                "rating" to binding.ratingBar2DB.rating.toString(),
            )
            )
        }
    }
}