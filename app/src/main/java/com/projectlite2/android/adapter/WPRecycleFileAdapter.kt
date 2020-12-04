package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.model.RecyclebinCard
import com.projectlite2.android.utils.ItemTouchHelperAdapter
import com.projectlite2.android.utils.OnItemClickListenerPlus
import org.w3c.dom.Text
import java.util.*

class WPRecycleFileAdapter(private var mData: MutableList<RecyclebinCard>) :
        RecyclerView.Adapter<WPRecycleFileAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var itemClickListener: OnItemClickListenerPlus? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cdCardBg: CardView = itemView.findViewById(R.id.WPDeleteCardBackground)
        val cdConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        val cdDeleteFileRecyc: RecyclerView = itemView.findViewById(R.id.deleteFileBar)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WPRecycleFileAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workplace_recyclebin_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WPRecycleFileAdapter.ViewHolder, position: Int) {
        val card = mData[position]

        holder.cdName.text = card.name

        // 整体点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it, holder.adapterPosition, holder.itemView.id)
        }
    }

    override fun getItemCount() = mData.size

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: OnItemClickListenerPlus) {
        this.itemClickListener = itemClickListener
    }

    override fun onItemDissmiss(position: Int) {
        //移除数据
//        mData.removeAt(position)
//        notifyItemRemoved(position);
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        //交换位置
        Collections.swap(mData,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }
}