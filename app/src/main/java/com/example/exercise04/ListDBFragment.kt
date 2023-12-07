package com.example.exercise04

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise04.data.DBProduct
import com.example.exercise04.data.ProductRepository
import com.example.exercise04.databinding.FragmentListDBBinding


class ListDBFragment : Fragment() {
    private lateinit var binding: FragmentListDBBinding
    lateinit var productRepo: ProductRepository
    lateinit var adapter: DBAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productRepo = ProductRepository(requireContext())
        adapter = DBAdapter(productRepo.getData()!!, productRepo)

        parentFragmentManager.setFragmentResultListener("item_added", this) { requestKey, _ ->
            adapter.data = productRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDB)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = adapter

        val fab = view.findViewById<View>(R.id.addProductActionButtonDB)
        fab.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putInt("fragment_mode", 0) // 0 for add, 1 for edit
            findNavController().navigate(R.id.action_to_nav_product_add_d_b, bundle)
        })

        adapter.setOnClickListener(object : DBAdapter.OnClickListener {
            override fun onClick(position: Int, product: DBProduct) {
                val bundle = Bundle()
                bundle.putInt("fragment_mode", 1) // 0 for add, 1 for edit
                bundle.putInt("product_id", product.id)

                findNavController().navigate(R.id.action_to_nav_product_details, bundle)
            }
        })
    }
}