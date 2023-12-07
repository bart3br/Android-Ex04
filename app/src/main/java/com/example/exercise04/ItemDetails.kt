package com.example.exercise04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise04.databinding.ActivityItemDetailsBinding

class ItemDetails : AppCompatActivity() {
    var binding:ActivityItemDetailsBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var plist:ProductModel?=null

        if(intent.hasExtra(ListFragment.NEXT_SCREEN)){
            plist =
                intent.getSerializableExtra(ListFragment.NEXT_SCREEN) as ProductModel
        }
        if(plist!=null){
            binding?.itemNameTextView?.text=plist.name
            when(plist.prodType){
                ProdType.FOOD->{
                    binding?.itemImageView?.setImageResource(R.drawable.food_icon)
                }
                ProdType.DRINK->{
                    binding?.itemImageView?.setImageResource(R.drawable.water_icon)
                }
                ProdType.CLEANING->{
                    binding?.itemImageView?.setImageResource(R.drawable.cleaning_icon)
                }
            }
        }
        binding?.ratingBar2?.rating=plist?.rating!!
        binding?.descriptionTextView?.text=plist.description
        binding?.priceTextView?.text= plist.price.toString()
        binding?.button?.setOnClickListener {
            finish()
        }
    }
}