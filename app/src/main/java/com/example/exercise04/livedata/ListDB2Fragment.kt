package com.example.exercise04.livedata

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise04.R
import com.example.exercise04.data.DBProduct
import com.example.exercise04.databinding.CardViewDesignBinding
import com.example.exercise04.databinding.FragmentListDB2Binding


class ListDB2Fragment : Fragment() {
    private lateinit var binding: FragmentListDB2Binding
    private lateinit var myViewModel: MyViewModel
    private lateinit var adapter: MyDB2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDB2Binding.inflate(inflater, container, false)
        //return binding.root
        val recView = binding.recyclerviewDB
        recView.layoutManager = LinearLayoutManager(requireContext())
        val repository = Product2Repository.getInstance(requireContext())
        myViewModel = MyViewModel(repository!!)
        adapter = MyDB2Adapter(myViewModel.products.value!!)
        recView.adapter = adapter

        myViewModel.products.observe(viewLifecycleOwner, Observer { items ->
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("itemAdd", viewLifecycleOwner) { _, bundle ->
            val productName = bundle.getString("product_name", "default product")
            val productDescription = bundle.getString("product_description", "some description")
            val productType = bundle.getInt("product_type", 0)
            val productPrice = bundle.getDouble("product_price", 1.0)
            val productRating = bundle.getFloat("product_rating", 3.0f)
            val newProduct = DBProduct(
                productName,
                productDescription,
                productType,
                productPrice,
                productRating
            )
            myViewModel.addProduct(newProduct)

        }

        parentFragmentManager.setFragmentResultListener("itemModify", viewLifecycleOwner) { _, bundle ->
            val productId = bundle.getInt("product_id", 0)
            val productName = bundle.getString("product_name", "default product")
            val productDescription = bundle.getString("product_description", "some description")
            val productType = bundle.getInt("product_type", 0)
            val productPrice = bundle.getDouble("product_price", 1.0)
            val productRating = bundle.getFloat("product_rating", 3.0f)

            myViewModel.updateProduct(productId, productName, productDescription, productType, productPrice, productRating)

        }

        binding.addProductActionButtonDB.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("fragment_mode", 0) // 0 for add, 1 for edit
            findNavController().navigate(R.id.action_to_nav_product_add_d_b, bundle)
        }
        /*val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewDB)
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
        })*/
    }

    inner class MyDB2Adapter(var data: MutableList<DBProduct>) : RecyclerView.Adapter<MyDB2Adapter.ViewHolder>() {
        private val MAX_TEXT_LENGTH = 20
        //private var onClickListener: OnClickListener? = null
        private var selectedProductIndx: Int = RecyclerView.NO_POSITION

        override fun getItemCount(): Int {
            return data.size
        }

        inner class ViewHolder(viewHolder: CardViewDesignBinding) :
            RecyclerView.ViewHolder(viewHolder.root) {
            val imageView = viewHolder.imageview
            val textView = viewHolder.textView
            val textView2 = viewHolder.textView2
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.card_view_design, parent, false)
            val view = CardViewDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val product = data[position]
            //val ItemsViewModel = getProduct(holder.getAdapterPosition())
            //val ItemsViewModel = DBAdapter.getProduct(position)

            when (product.productType) {
                0 -> {
                    holder.imageView.setImageResource(R.drawable.food_icon)
                }
                1 -> {
                    holder.imageView.setImageResource(R.drawable.water_icon)
                }
                2 -> {
                    holder.imageView.setImageResource(R.drawable.cleaning_icon)
                }
            }

            //display short description
            holder.textView.text = product.name
            if (product.description!!.length < MAX_TEXT_LENGTH)
                holder.textView2.text = product.description
            else
                holder.textView2.text = product.description!!.removeRange(MAX_TEXT_LENGTH, product.description!!.length) + "..."


            //display item details
            holder.itemView.setOnClickListener {
                /*if(onClickListener != null){
                    onClickListener!!.onClick(position, ItemsViewModel)
                }*/
                parentFragmentManager.setFragmentResult("msgtochild", bundleOf(
                    "name" to product.name,
                    "description" to product.description,
                    "productType" to product.productType,
                    "price" to product.price,
                    "rating" to product.rating,
                    "id" to product.id
                ))
                findNavController().navigate(R.id.action_to_nav_product_details2)
            }

            holder.itemView.setOnLongClickListener {
                myViewModel.deleteProduct(product)
                true
            }
            /*holder.itemView.setOnLongClickListener { parentFragmentManager.setFragmentResult("msgtochild"), bundleOf(
                "name" to ItemsViewModel.name,
                "description" to ItemsViewModel.description,
                "productType" to ItemsViewModel.productType,
                "id" to ItemsViewModel.id
            ) }*/
        }

        /*interface OnClickListener {
            fun onClick(position: Int, product: DBProduct)
        }

        fun setOnClickListener(onClickListener: OnClickListener) {
            this.onClickListener = onClickListener
        }*/

        /*object DiffCallback : DiffUtil.ItemCallback<DBProduct>() {
            override fun areItemsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem == newItem
            }
        }*/
    }

    /*companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DBProduct>() {
            override fun areItemsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DBProduct, newItem: DBProduct): Boolean {
                return oldItem == newItem
            }
        }
    }*/
}