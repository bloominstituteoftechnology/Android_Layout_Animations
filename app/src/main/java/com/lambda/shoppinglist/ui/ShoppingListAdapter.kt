package com.lambda.shoppinglist.ui


import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lambda.shoppinglist.R
import com.lambda.shoppinglist.model.ShoppingItem
import kotlinx.android.synthetic.main.shopping_item_layout.view.*
import java.util.ArrayList

class ShoppingListAdapter (val data: ArrayList<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    private var lastPosition = -1
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.item_image
        val itemName: TextView = view.item_name
        val card = view.card_view as CardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_layout, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemImage.setImageDrawable(getDrawable(holder.itemImage.context, data[position].itemImageId))
        holder.itemName.text = data[position].itemName

        holder.itemView.setOnClickListener {

            if (it.tag != "on") {

                it.tag = "on"
                holder.itemView.setBackgroundColor(ContextCompat.getColor(it.context,
                    R.color.colorPrimaryLight
                ))
                val intent = Intent(it.context, DetailsActivity::class.java)
                intent.putExtra("ITEM_KEY", data[position])

                val optionsBundle: Bundle =
                    ActivityOptions.makeSceneTransitionAnimation(it.context as Activity, holder.itemImage, "selected_image")
                        .toBundle()

                it.context.startActivity(intent,optionsBundle)

            } else if (it.tag != "off") {
                it.tag = "off"
                holder.itemView.setBackgroundColor(ContextCompat.getColor(it.context,
                    R.color.colorWhite
                ))

            }

        }
        setEnterAnimation(holder.card,position)
        }
    private fun setEnterAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.animation)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
    }


