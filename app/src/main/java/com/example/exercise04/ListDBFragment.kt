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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
            //val result = bundle.getSerializable("bundleKey") as ProductModel
            //productRepo.insertData(result)
            //adapter.addProduct(result)
            adapter.data = productRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListDBBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_list_d_b, container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_d_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDB)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = adapter

        val fab = view.findViewById<View>(R.id.addProductActionButtonDB)
        fab.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_to_nav_product_add_d_b)
            /*val fragment = ProductAddDBFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()*/
        })

        adapter.setOnClickListener(object : DBAdapter.OnClickListener {
            override fun onClick(position: Int, product: DBProduct) {
                val bundle = Bundle()
                bundle.putString("name", product.name)
                bundle.putString("description", product.description)
                bundle.putDouble("price", product.price)
                bundle.putFloat("rating", product.rating)
                bundle.putInt("productType", product.productType)

                findNavController().navigate(R.id.action_to_nav_product_details, bundle)
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListDBFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}