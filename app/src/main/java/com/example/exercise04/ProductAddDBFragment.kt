package com.example.exercise04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
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
    ): View? {
        binding = FragmentProductAddDBBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_product_add_d_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepo = ProductRepository.getInstance(requireContext())!!

        binding.saveButtonDB.setOnClickListener { _ ->
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
            parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)
            findNavController().navigateUp()
            //requireActivity().onBackPressed()


        }
        binding.cancelButtonDB.setOnClickListener { _ ->
            findNavController().navigateUp()
        }
    }
}