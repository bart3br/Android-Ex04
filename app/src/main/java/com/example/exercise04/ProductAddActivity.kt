package com.example.exercise04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise04.databinding.ActivityProductAddBinding

class ProductAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener { _ ->
            var productType = ProdType.FOOD
            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.foodRadioButton -> productType = ProdType.FOOD
                R.id.drinkRadioButton -> productType = ProdType.DRINK
                R.id.cleaningRadioButton -> productType = ProdType.CLEANING
            }
            val product = ProductModel(
                binding.editName.text.toString(),
                binding.editTextTextMultiLine.text.toString(),
                productType,
                binding.editPrice.text.toString().toDouble(),
                binding.ratingBar3.rating,
            )
            val intent = intent
            intent.putExtra("product", product)
            setResult(RESULT_OK, intent)
            finish()


        }
        binding.cancelButton.setOnClickListener { _ ->
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
