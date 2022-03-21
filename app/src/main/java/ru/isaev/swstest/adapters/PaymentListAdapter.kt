package ru.isaev.swstest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.isaev.swstest.R
import ru.isaev.swstest.helper.Item
import ru.isaev.swstest.models.PaymentDataModel
import java.util.*

class PaymentListAdapter : RecyclerView.Adapter<PaymentListAdapter.ViewHolder>() {
    private val paymentList: MutableList<PaymentDataModel> = LinkedList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.payment_recycleview_item, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItem: List<PaymentDataModel>) {
        paymentList.clear()
        paymentList.addAll(newItem)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return paymentList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = paymentList[position])
    }


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(model = paymentList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val coffeeName = itemView.findViewById<TextView>(R.id.paymentCoffeeNameTextView)
        val coffeeCount = itemView.findViewById<TextView>(R.id.paymentCoffeeCountTextView)
        val quantityMinusBtn = itemView.findViewById<ImageButton>(R.id.paymentQuantityMinusButton)
        val quantityPlusBtn = itemView.findViewById<ImageButton>(R.id.paymentQuantityPlusButton)

        fun bind(model: PaymentDataModel) {
            coffeeName.text = model.name
            coffeeCount.text = model.count.toString()
            quantityMinusBtn.setOnClickListener {
                Item.menuPositionCount[model.name] = model.count
            }
            quantityPlusBtn.setOnClickListener {
                Item.menuPositionCount[model.name] = model.count
            }
        }

    }
}