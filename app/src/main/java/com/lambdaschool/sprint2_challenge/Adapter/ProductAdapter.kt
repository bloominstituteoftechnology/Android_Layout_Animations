package com.lambdaschool.sprint2_challenge.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.Shopping_items.itemDetail

import kotlinx.android.synthetic.main.product_item.view.*



class ProductAdapter( val products: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>(){



    private var context: Context? = null
    private var lastPosition = -1


    override fun getItemCount(): Int {
        return products.size
    }

    /*override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ProductHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.product_item, p0, false)
        return ProductHolder(view)
    } */


    fun setEnterAnimation(viewToAnimate: View, position:Int) {
        val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
        viewToAnimate.startAnimation(animation)

        lastPosition = position


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent,false) as View)
    }
/*
    override fun onBindViewHolder(holder: ProductHolder?, position: Int) {
        holder?.bindProduct(products[position], context)
    } */
override fun onBindViewHolder(holder: ProductHolder, position: Int) {
   val grocery = products[position]
    holder.bindModel(grocery)

    holder.listParent.setOnClickListener{
        grocery.purchased = !grocery.purchased
        notifyItemChanged(position)

        holder.productImage.setOnClickListener{ view ->
            val intent = Intent(view.context, itemDetail::class.java)
            intent.putExtra(itemDetail.ITEM_KEY,grocery)
            view.context.startActivity(intent)
        }




    }


    setEnterAnimation(holder.productImage,position)
}


    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage: ImageView = itemView.productimage
        val productName: TextView = itemView.productName
        val listParent: LinearLayout = itemView.list_of_items

        fun bindModel(product: Product) {
            productImage.setImageResource(product.imageId)
            productName.text = product.title
            if (product.purchased)
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorAccent))
            else
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorPrimary))


        }
    }
}







