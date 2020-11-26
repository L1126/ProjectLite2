package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.model.NewFriendCard

class NewFriendCardAdapter (private val cards: List<NewFriendCard>) :
        RecyclerView.Adapter<NewFriendCardAdapter.ViewHolder>() {

    private var cardNewClickListener: IKotlinItemClickListener? = null

    inner class ViewHolder(cardcaseview: View) :RecyclerView.ViewHolder(cardcaseview){
        val cdNewCardBg: CardView = cardcaseview.findViewById(R.id.myCardNewBackground)
        val cdNewPic: ImageView = cardcaseview.findViewById(R.id.picAvatar)
        val cdNewName: TextView = cardcaseview.findViewById(R.id.txtName)
        val cdNewMajor: TextView = cardcaseview.findViewById(R.id.txtGrade)
        val cdNewGrade: TextView = cardcaseview.findViewById(R.id.txtMajor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_eachone_new, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.cdNewName.text = card.name
        holder.cdNewMajor.text = card.major
        holder.cdNewGrade.text = card.grade

        // 点击事件
        holder.itemView.setOnClickListener {
            cardNewClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount() = cards.size

    // 提供set方法
    fun setOnKotlinItemClickListener(cardNewClickListener: IKotlinItemClickListener) {
        this.cardNewClickListener = cardNewClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }
}