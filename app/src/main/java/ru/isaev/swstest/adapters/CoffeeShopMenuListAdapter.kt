package ru.isaev.swstest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.isaev.swstest.R
import ru.isaev.swstest.models.CoffeeShopMenu
import java.util.*

class CoffeeShopMenuListAdapter : RecyclerView.Adapter<CoffeeShopMenuListAdapter.ViewHolder>() {
    private val menuList: MutableList<CoffeeShopMenu> = LinkedList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_recycleview_item, parent, false)
        )
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItem: List<CoffeeShopMenu>) {
        menuList.clear()
        menuList.addAll(newItem)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return menuList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = menuList[position])
    }


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(model = menuList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var menuItemImage = itemView.findViewById<ImageView>(R.id.cardCoffeeImageView)
        var menuItemName = itemView.findViewById<TextView>(R.id.cardCoffeeNameTextView)
        var menuItemCost = itemView.findViewById<TextView>(R.id.cardCoffeeCoastTextView)
        fun bind(model: CoffeeShopMenu) {
            menuItemName.text = model.name
            menuItemCost.text = model.price.toString()

            Picasso.get().load(model.imageUrl).into(menuItemImage)
        }

    }
}