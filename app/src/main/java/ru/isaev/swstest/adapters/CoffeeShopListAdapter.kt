package ru.isaev.swstest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.isaev.swstest.R
import ru.isaev.swstest.models.CoffeeShopModel
import java.util.*

class CoffeeShopListAdapter: RecyclerView.Adapter<CoffeeShopListAdapter.ViewHolder>() {
    private val coffeeShopList: MutableList<CoffeeShopModel> = LinkedList()

    fun setData (newItem: List<CoffeeShopModel>){
        coffeeShopList.clear()
        coffeeShopList.addAll(newItem)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.coffeeshop_recycleview_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = coffeeShopList[position])
    }

    override fun getItemCount(): Int {
        return coffeeShopList.count()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val coffeeShopName = itemView.findViewById<TextView>(R.id.coffeeShopNameTextView)


        @SuppressLint("SetTextI18n")
        fun bind(model: CoffeeShopModel){
            coffeeShopName.text = model.name
        }

    }

}