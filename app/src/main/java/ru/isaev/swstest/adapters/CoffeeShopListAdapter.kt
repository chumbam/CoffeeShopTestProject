package ru.isaev.swstest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.isaev.swstest.R
import ru.isaev.swstest.models.CoffeeShopModel
import java.util.*

interface NavigateToMenuDelegate{

    fun navigateToMenuFragment(id: String)
}

class CoffeeShopListAdapter : RecyclerView.Adapter<CoffeeShopListAdapter.ViewHolder>() {
    private val coffeeShopList: MutableList<CoffeeShopModel?> = LinkedList()
    private var delegate: NavigateToMenuDelegate? = null

    fun attachDelegate(delegate: NavigateToMenuDelegate){
        this.delegate = delegate
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItem: List<CoffeeShopModel?>) {
        coffeeShopList.clear()
        coffeeShopList.addAll(newItem)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.coffeeshop_recycleview_item, parent, false), delegate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = coffeeShopList[position])
    }

    override fun getItemCount(): Int {
        return coffeeShopList.count()
    }

    class ViewHolder(itemView: View, private val delegate: NavigateToMenuDelegate?) : RecyclerView.ViewHolder(itemView) {
        val coffeeShopName = itemView.findViewById<TextView>(R.id.coffeeShopNameTextView)
        val layoutItem = itemView.findViewById<ConstraintLayout>(R.id.layout_item)


        fun bind(model: CoffeeShopModel?) {
            coffeeShopName.text = model?.name
            layoutItem.setOnClickListener {
                if (model != null) {
                    delegate?.navigateToMenuFragment(id = model.id.toString())
                }
            }
        }

    }

}