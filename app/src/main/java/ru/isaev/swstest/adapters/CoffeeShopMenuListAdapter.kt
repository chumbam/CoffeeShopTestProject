package ru.isaev.swstest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.isaev.swstest.R
import ru.isaev.swstest.models.CoffeeShopMenu
import java.util.*

interface QuantityCountDelegate {
    fun quantityPlus(name: String, count: Int)
    fun quantityMinus(name: String, count: Int)
}

class CoffeeShopMenuListAdapter : RecyclerView.Adapter<CoffeeShopMenuListAdapter.ViewHolder>() {
    private val menuList: MutableList<CoffeeShopMenu> = LinkedList()
    private var delegate: QuantityCountDelegate? = null

    fun attachDelegate(delegate: QuantityCountDelegate) {
        this.delegate = delegate
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_recycleview_item, parent, false), delegate
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

    class ViewHolder(itemView: View, private val delegate: QuantityCountDelegate?) :
        RecyclerView.ViewHolder(itemView) {

        val menuItemImage = itemView.findViewById<ImageView>(R.id.cardCoffeeImageView)
        val menuItemName = itemView.findViewById<TextView>(R.id.cardCoffeeNameTextView)
        val menuItemCost = itemView.findViewById<TextView>(R.id.cardCoffeeCoastTextView)
        val quantityMinusBtn = itemView.findViewById<ImageButton>(R.id.quantityMinusButton)
        val quantityPlusBtn = itemView.findViewById<ImageButton>(R.id.quantityPlusButton)
        val coffeeCount = itemView.findViewById<TextView>(R.id.coffeeCountTextView)

        fun bind(model: CoffeeShopMenu) {
            menuItemName.text = model.name
            menuItemCost.text = model.price.toString()
            coffeeCount.text = model.count.toString()
            println(model.imageURL)
            Picasso.get().load(model.imageURL).into(menuItemImage)
            quantityMinusBtn.setOnClickListener {
                if (coffeeCount.text.toString().toInt() >= 0) {
                    model.count -= 1
                    coffeeCount.text = model.count.toString()
                }
                delegate?.quantityMinus(model.name, model.count)
            }
            quantityPlusBtn.setOnClickListener {
                    model.count += 1
                    coffeeCount.text = model.count.toString()
                delegate?.quantityPlus(model.name, model.count)
            }

        }

    }
}