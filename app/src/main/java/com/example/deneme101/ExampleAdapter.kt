package com.example.deneme101

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


//Adapter  verileri RecyclerView'e bağlar.verileri VieqHolder içinde görüntülwbilecek şekilde uyarlar
//RecyclerView verilerin ekranda nasıl görüntüleceğinin anlamak için adapter kullanır
class ExampleAdapter(val context: Context, var itemList: ArrayList<ExampleItem>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    private var selected = -1

    fun ExampleAdapter(itemList: ArrayList<ExampleItem>) {
        this.itemList = itemList
    }

    var onClick: OnItemClickListener? = null

    fun setOnItemClickLitener(mOnItemClickListener: OnItemClickListener) {
        this.onClick = mOnItemClickListener
    }

    fun setSelection(position: Int) {
        selected = position
        notifyDataSetChanged()
    }
    fun getSelection(position: Int): ExampleItem{
        return itemList[position]
    }

    fun setFilteredList(itemList: ArrayList<ExampleItem>){
        this.itemList=itemList
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_single_item, parent, false)
        return ExampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val item = itemList[position]
        holder.title.text = item.text1
        holder.desc.text = item.text2
        holder.not_card.setOnClickListener{
      val intent= Intent(context,DetayActivity::class.java)
            intent.putExtra("nesne",item)
            context.startActivity(intent)

        }


        if (onClick != null) {
            holder.itemView.setOnClickListener {
                onClick!!.onItemClick(
                    holder.title,
                    holder.adapterPosition
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById(R.id.text_view_1) as TextView
        var desc = itemView.findViewById(R.id.text_view_2) as TextView
        var not_card= itemView.findViewById(R.id.not_card) as CardView
    }
}


