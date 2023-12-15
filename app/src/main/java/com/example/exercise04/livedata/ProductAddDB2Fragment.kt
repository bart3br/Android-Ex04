package com.example.exercise04.livedata

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.exercise04.R
import com.example.exercise04.databinding.FragmentProductAddDB2Binding


class ProductAddDB2Fragment : Fragment() {
    private lateinit var binding: FragmentProductAddDB2Binding
    //private lateinit var productRepo: Product2Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductAddDB2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //productRepo = Product2Repository.getInstance(requireContext())!!

        //modify mode
        if (arguments?.getInt("fragment_mode") == 1) {
            val productId = arguments?.getInt("id")
            if (productId != null) {
                //val product = productRepo.getItemById(productId)
                binding.editNameDB.setText(arguments?.getString("name"))
                binding.editTextTextMultiLineDB.setText(arguments?.getString("description"))
                binding.editPriceDB.setText(arguments?.getDouble("price").toString())
                binding.ratingBar3DB.rating = arguments?.getFloat("rating")!!
                when (arguments?.getInt("type")) {
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

            //parentFragmentManager.setFragmentResult("item_add", Bundle.EMPTY)
            //findNavController().navigateUp()
        }

        binding.cancelButtonDB.setOnClickListener { _ ->
            findNavController().navigateUp()
        }
    }

    private fun addNewProduct() {
        val name = binding.editNameDB.text.toString().trim()
        val description = binding.editTextTextMultiLineDB.text.toString().trim()
        val priceText = binding.editPriceDB.text.toString().trim()

        //null fields check
        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val type = when (binding.radioGroupDB.checkedRadioButtonId) {
            R.id.foodRadioButtonDB -> 0
            R.id.drinkRadioButtonDB -> 1
            R.id.cleaningRadioButtonDB -> 2
            else -> 0
        }
        val price = priceText.toDouble()
        val rating = binding.ratingBar3DB.rating

        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("description", description)
        bundle.putInt("type", type)
        bundle.putDouble("price", price)
        bundle.putFloat("rating", rating)

        parentFragmentManager.setFragmentResult("item_add", bundle)
        findNavController().navigateUp()
        /*val product = DBProduct(
            binding.editNameDB.text.toString(),
            binding.editTextTextMultiLineDB.text.toString(),
            productType,
            binding.editPriceDB.text.toString().toDouble(),
            binding.ratingBar3DB.rating,
        )*/
        //productRepo.addItem(product)

        //Toast.makeText(requireContext(), "New product added!", Toast.LENGTH_SHORT).show()
    }

    private fun modifyProduct() {
        val productId = arguments?.getInt("id")
        if (productId != null) {
            val name = binding.editNameDB.text.toString()
            val description = binding.editTextTextMultiLineDB.text.toString()
            val priceText = binding.editPriceDB.text.toString().trim()

            //null fields check
            if (name.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return
            }

            val type = when (binding.radioGroupDB.checkedRadioButtonId) {
                R.id.foodRadioButtonDB -> 0
                R.id.drinkRadioButtonDB -> 1
                R.id.cleaningRadioButtonDB -> 2
                else -> 0
            }
            val price = priceText.toDouble()
            val rating = binding.ratingBar3DB.rating

            val bundle = Bundle()
            bundle.putInt("id", productId)
            bundle.putString("name", name)
            bundle.putString("description", description)
            bundle.putInt("type", type)
            bundle.putDouble("price", price)
            bundle.putFloat("rating", rating)
            Log.d("ProductAddDB2Fragment", "modifyProduct: $bundle")
            parentFragmentManager.setFragmentResult("item_modify", bundle)
            //findNavController().navigateUp()
            findNavController().navigate(
                R.id.action_to_nav_recycler_view_d_b2,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.nav_product_details2, false)
                    .build())
        }
    }
}