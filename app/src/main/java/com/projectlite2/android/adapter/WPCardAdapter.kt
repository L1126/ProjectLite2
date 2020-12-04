package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.model.WorkPlaceCard
import com.projectlite2.android.utils.ItemTouchHelperAdapter
import com.projectlite2.android.utils.OnItemClickListener
import com.projectlite2.android.utils.OnItemClickListenerPlus
import java.util.*

class WPCardAdapter(private var mData: MutableList<WorkPlaceCard>, val style: Int) :
        RecyclerView.Adapter<WPCardAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    companion object{
        const val STYLE_PARAM_FILE = 0
        const val STYLE_PARAM_STUDY = 1
        const val STYLE_PARAM_MEETING = 2
    }

    private var itemClickListener: OnItemClickListenerPlus? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cdCardBg: CardView = itemView.findViewById(R.id.WPCardBackground)
        val cdConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        val cdAvatar: ImageView = itemView.findViewById(R.id.picFile)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
        val cdIntro: TextView = itemView.findViewById(R.id.txtIntro)
        val cdDate: TextView = itemView.findViewById(R.id.txtDate)
        val cdTime: TextView = itemView.findViewById(R.id.txtTime)
        val cdMessageBar: LinearLayout = itemView.findViewById(R.id.messageBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WPCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workplace_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = mData[position]

        holder.cdName.text = card.name
        holder.cdIntro.text = card.intro
        holder.cdDate.text = card.date
        holder.cdTime.text = card.time

        setLayoutStyle(holder, style)

        //点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it,holder.adapterPosition,holder.itemView.id)
        }
    }

    private fun setLayoutStyle(holder: WPCardAdapter.ViewHolder, style: Int) {

        if (style == STYLE_PARAM_FILE) {

            holder.cdIntro.visibility = View.GONE
            holder.cdTime.visibility = View.GONE

        } else if (style == STYLE_PARAM_STUDY) {

            holder.cdDate.visibility = View.GONE
            holder.cdTime.visibility = View.GONE

        } else if (style == STYLE_PARAM_MEETING){

            holder.cdMessageBar.visibility = View.GONE

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