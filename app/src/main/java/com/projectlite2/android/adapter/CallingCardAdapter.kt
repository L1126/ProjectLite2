package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.model.CallingCard

class CallingCardAdapter(private val cards: List<CallingCard>) :
        RecyclerView.Adapter<CallingCardAdapter.ViewHolder>() {

    private var cardClickListener: IKotlinItemClickListener? = null

    inner class ViewHolder(cardcaseview: View) :RecyclerView.ViewHolder(cardcaseview){
        val cdCardBg: CardView = cardcaseview.findViewById(R.id.myCardBackground)
        val cdPic: ImageView = cardcaseview.findViewById(R.id.cardPic)
        val cdPicStatus: ImageView = cardcaseview.findViewById(R.id.cardStatus)
        val cdName: TextView = cardcaseview.findViewById(R.id.cardName)
        val cdMajor: TextView = cardcaseview.findViewById(R.id.cardMajor)
        val cdGrade: TextView = cardcaseview.findViewById(R.id.cardGrade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_eachone, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.cdName.text = card.name
        holder.cdMajor.text = card.major
        holder.cdGrade.text = card.grade

        // 点击事件
        holder.itemView.setOnClickListener {
            cardClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount() = cards.size

    // 提供set方法
    fun setOnKotlinItemClickListener(cardClickListener: IKotlinItemClickListener) {
        this.cardClickListener = cardClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }
}