package com.example.exercise04.livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.exercise04.R
import com.example.exercise04.data.ProductRepository
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

        val bundle = arguments
        var name = bundle!!.getString("name", "default name")
        var description = bundle.getString("description", "default description")
        var type = bundle.getInt("type", 0)
        var price = bundle.getDouble("price", 10.0)
        var rating = bundle.getFloat("rating", 3.0f)

        loadProductData(name, description, type, price, rating)

        binding.buttonReturnDB.setOnClickListener { _ ->
            findNavController().navigateUp()
        }

        binding.buttonModifyDB.setOnClickListener {
            /*parentFragmentManager.setFragmentResult("msgtoedit", bundleOf(
                "name" to binding.productNameTextViewDB.text.toString(),
                "description" to binding.descriptionTextViewDB.text.toString(),
                "price" to binding.priceTextViewDB.text.toString(),
                "rating" to binding.ratingBar2DB.rating.toString(),
            )
            )*/
            val bundle = Bundle()
            bundle.putInt("fragment_mode", 1) // 0 for add, 1 for edit
            bundle.putInt("product_id", arguments?.getInt("product_id")!!)
            bundle.putString("name", name)
            bundle.putString("description", description)
            bundle.putDouble("price", price)
            bundle.putFloat("rating", rating)
            bundle.putInt("type", type)

            findNavController().navigate(R.id.action_to_nav_product_add_d_b2, bundle)
        }
    }

    private fun loadProductData(name :String, description :String, type :Int, price :Double, rating :Float) {
        //val productId = arguments?.getInt("product_id")!!

        //val productRepo = ProductRepository.getInstance(requireContext())!!
        //val product = productRepo.getItemById(productId)

        binding.productNameTextViewDB.text = name
        binding.descriptionTextViewDB.text = description
        binding.priceTextViewDB.text = price.toString()
        binding.ratingBar2DB.rating = rating!!
        when (type) {
            0 -> binding.productImageViewDB.setImageResource(R.drawable.food_icon)
            1 -> binding.productImageViewDB.setImageResource(R.drawable.water_icon)
            2 -> binding.productImageViewDB.setImageResource(R.drawable.cleaning_icon)
        }
    }
}