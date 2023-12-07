import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise04.ProductModel
import com.example.exercise04.ProdType
import com.example.exercise04.R


class CustomAdapter(private val mList: MutableList<ProductModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val MAX_TEXT_LENGTH = 20
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        when (ItemsViewModel.prodType) {
            ProdType.FOOD -> {
                holder.imageView.setImageResource(R.drawable.food_icon)
            }
            ProdType.DRINK -> {
                holder.imageView.setImageResource(R.drawable.water_icon)
            }
            ProdType.CLEANING -> {
                holder.imageView.setImageResource(R.drawable.cleaning_icon)
            }
        }

        holder.textView.text = ItemsViewModel.name
        if (ItemsViewModel.description.length < MAX_TEXT_LENGTH)
            holder.textView2.text = ItemsViewModel.description
        else
            holder.textView2.text = ItemsViewModel.description.removeRange(MAX_TEXT_LENGTH, ItemsViewModel.description.length) + "..."
        holder.itemView.setOnClickListener {
            if(onClickListener != null){
                onClickListener!!.onClick(position, ItemsViewModel)
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun addProduct(product: ProductModel){
        mList.add(product)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun deleteProduct(product: ProductModel) {
        mList.remove(product)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(position: Int, product: ProductModel)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
    }
}