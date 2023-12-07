package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exercise04.data.ProductRepository
import com.example.exercise04.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

            findNavController().navigate(R.id.action_to_nav_product_add_d_b, bundle)
        })
    }

    private fun loadProductData() {
        val productId = arguments?.getInt("product_id")!!

        val productRepo = ProductRepository.getInstance(requireContext())!!
        val product = productRepo.getItemById(productId)

        binding.productNameTextViewDB.text = product?.name
        binding.descriptionTextViewDB.text = product?.description
        binding.priceTextViewDB.text = product?.price.toString()
        binding.ratingBar2DB.rating = product?.rating!!
        when (product.productType) {
            0 -> binding.productImageViewDB.setImageResource(R.drawable.food_icon)
            1 -> binding.productImageViewDB.setImageResource(R.drawable.water_icon)
            2 -> binding.productImageViewDB.setImageResource(R.drawable.cleaning_icon)
        }
    }
}