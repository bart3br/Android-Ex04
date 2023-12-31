package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.exercise04.data.DBProduct
import com.example.exercise04.data.ProductRepository
import com.example.exercise04.databinding.FragmentProductAddDBBinding


class ProductAddDBFragment : Fragment() {
    private lateinit var binding: FragmentProductAddDBBinding
    private lateinit var productRepo: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductAddDBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepo = ProductRepository.getInstance(requireContext())!!

        //modify mode
        if (arguments?.getInt("fragment_mode") == 1) {
            val productId = arguments?.getInt("product_id")
            if (productId != null) {
                val product = productRepo.getItemById(productId)
                binding.editNameDB.setText(product?.name)
                binding.editTextTextMultiLineDB.setText(product?.description)
                binding.editPriceDB.setText(product?.price.toString())
                binding.ratingBar3DB.rating = product?.rating!!
                when (product.productType) {
                    0 -> binding.foodRadioButtonDB.isChecked = true
                    1 -> binding.drinkRadioButtonDB.isChecked = true
                    2 -> binding.cleaningRadioButtonDB.isChecked = true
                }
            }
        }

        binding.saveButtonDB.setOnClickListener { _ ->
            if (arguments?.getInt("fragment_mode") == 0)
                addNewProduct() //add mode
            else
                modifyProduct() //modify mode

            parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)
            findNavController().navigateUp()
        }

        binding.cancelButtonDB.setOnClickListener { _ ->
            findNavController().navigateUp()
        }
    }

    private fun addNewProduct() {
        var productType = 0
        when (binding.radioGroupDB.checkedRadioButtonId) {
            R.id.foodRadioButtonDB -> productType = 0
            R.id.drinkRadioButtonDB -> productType = 1
            R.id.cleaningRadioButtonDB -> productType = 2
        }
        val product = DBProduct(
            binding.editNameDB.text.toString(),
            binding.editTextTextMultiLineDB.text.toString(),
            productType,
            binding.editPriceDB.text.toString().toDouble(),
            binding.ratingBar3DB.rating,
        )
        productRepo.addItem(product)
        Toast.makeText(requireContext(), "New product added!", Toast.LENGTH_SHORT).show()
    }

    private fun modifyProduct() {
        val productId = arguments?.getInt("product_id")
        if (productId != null) {
            val product = productRepo.getItemById(productId)
            product?.apply {
                this.name = binding.editNameDB.text.toString()
                this.description = binding.editTextTextMultiLineDB.text.toString()
                this.rating = binding.ratingBar3DB.rating
                this.price = binding.editPriceDB.text.toString().toDouble()
                when (binding.radioGroupDB.checkedRadioButtonId) {
                    R.id.foodRadioButtonDB -> this.productType = 0
                    R.id.drinkRadioButtonDB -> this.productType = 1
                    R.id.cleaningRadioButtonDB -> this.productType = 2
                    else -> this.productType = 0
                }
                productRepo.updateItem(this)
                Toast.makeText(requireContext(), "Product updated!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}