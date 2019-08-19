package com.example.goingshoppinganimation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.goingshoppinganimation.R
import com.example.goingshoppinganimation.constants.ShoppingList
import kotlinx.android.synthetic.main.shopping_list_item.view.*


class ShoppingListAdapter(val data: MutableList<ShoppingList>):
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.textView2
        var image = view.image_view
        val listParent = view.shopping_list
        //TODO 10 add another constant to get the position
        var lastPostion = -1



        // TODO shortcut to onbind view holder (binding views to the XMLs)
        fun bindModel(list: ShoppingList){
            image.setImageResource(list.image)
            name.text = list.name
            // TODO instead of having a switch, implement boolean fun here
            if(list.isChecked)
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            else
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))







        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // instead of declaring the value like in the comment bellow
        /*   val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)

        return ViewHolder(viewGroup)*/
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_item, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingItems = data[position]
        holder.bindModel(shoppingItems)

        //TODO srt on Click listener here in order to get the position to set the favorites Boolean fun
        holder.listParent.setOnClickListener{
            if(shoppingItems.isChecked){
                shoppingItems.isChecked = false
                notifyItemChanged(position)
            }else{
                shoppingItems.isChecked = true
                notifyItemChanged(position)
            }
        }


    }

    override fun getItemCount(): Int {
        return data.size

    }

}