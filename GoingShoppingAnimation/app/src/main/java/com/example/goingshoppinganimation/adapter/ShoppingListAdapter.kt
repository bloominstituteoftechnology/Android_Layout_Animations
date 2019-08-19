package com.example.goingshoppinganimation.adapter


import android.app.Activity
import android.app.ActivityOptions
import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.goingshoppinganimation.R
import com.example.goingshoppinganimation.activity.DetailActivity
import com.example.goingshoppinganimation.constants.ShoppingList
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.shopping_list_item.view.*


class ShoppingListAdapter(val data: MutableList<ShoppingList>):
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.textView2
        var image = view.image_view
        val listParent = view.shopping_list
        //TODO 10 add another constant to get the position
        var lastPosition = -1

        fun setEnterAnimation(viewToAnimate: View, position: Int) {

            if (position > lastPosition) {

                val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
                viewToAnimate.startAnimation(animation)
            }

        }






        // TODO shortcut to onbind view holder (binding views to the XMLs)
        fun bindModel(list: ShoppingList){
            image.setImageResource(list.image)
            name.text = list.name
            // TODO instead of having a switch, implement boolean fun here
            if(list.isChecked)
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            else
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))

                setEnterAnimation(viewToAnimate = itemView, position = 1)
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
        holder.listParent.setOnClickListener{ context ->
            if(shoppingItems.isChecked){
                shoppingItems.isChecked = false
                notifyItemChanged(position)
            }else{
                shoppingItems.isChecked = true
                notifyItemChanged(position)
            }
            val intent = Intent(context.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ITEM_KEY, data[position])
            val optionsBundle = ActivityOptions.makeSceneTransitionAnimation(context.context as Activity,
                holder.image, "shared_image" ).toBundle()
            context.context.startActivity(intent, optionsBundle)




        }



    }


    override fun getItemCount(): Int {
        return data.size

    }


}