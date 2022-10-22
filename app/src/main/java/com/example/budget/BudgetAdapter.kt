package com.example.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.budget.database.BudgetItem

class BudgetAdapter(private val itemList: List<BudgetItem>,
                   val clickInterface: ClickInterface
                    )
    : RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BudgetAdapter.BudgetViewHolder{
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent , false )
            return BudgetViewHolder(itemView)
        }
        override fun onBindViewHolder(holder:BudgetViewHolder, position: Int) {

            holder.itemName.text = itemList[position].itemName

            holder.image.setImageResource(itemList[position].image)

            holder.price.text = itemList[position].price.toString()

            holder.cardView.setOnClickListener {
                clickInterface.onItemClick(itemList[position])
            }
            holder.image.setOnClickListener {
                clickInterface.onItemClick(itemList[position])
            }
        }
        override fun getItemCount(): Int {
        return itemList.size
    }
        class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var itemName : TextView = itemView.findViewById(R.id.item_name)
        var price : TextView = itemView.findViewById(R.id.item_price)
        var image : ImageView = itemView.findViewById(R.id.imageView)
       var cardView :CardView = itemView.findViewById(R.id.card_item)

    }

    interface ClickInterface {

        fun onItemClick (budgetItem: BudgetItem)
    }

}



