package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.topmenu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import customfonts.MyRobotoTextView
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import org.jetbrains.anko.find

class AdapterTopMainMenu(var dataItems: ArrayList<DataModel?>?, val context: Context?, private val listener: (DataModel) -> Unit):
    RecyclerView.Adapter<AdapterTopMainMenu.itemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): itemHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_top_main_menu,parent,false)
        return itemHolder(view)
    }

    override fun getItemCount(): Int = dataItems!!.size

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        holder.bindItems(dataItems?.get(position)!!,listener,context)
    }

    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.find(R.id.item_img)
        var itemText: MyRobotoTextView = itemView.find(R.id.item_text)

        fun bindItems(model: DataModel, listener: (DataModel) -> Unit, context: Context?){
            itemImage.setImageResource(model.itemImg)
            itemText.text = model.itemText
            itemView.setOnClickListener { listener(model) }
        }
    }
}