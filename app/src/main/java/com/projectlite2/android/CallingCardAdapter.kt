package com.projectlite2.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CallingCardAdapter(private val cards: List<CallingCard>) :
        RecyclerView.Adapter<CallingCardAdapter.ViewHolder>() {

    inner class ViewHolder(cardview: View) :RecyclerView.ViewHolder(cardview){
        val cdCardBg: CardView = cardview.findViewById(R.id.myCardBackground)
        val cdPic: ImageView = cardview.findViewById(R.id.cardPic)
        val cdPicStatus: ImageView = cardview.findViewById(R.id.cardStatus)
        val cdName: TextView = cardview.findViewById(R.id.cardName)
        val cdMajor: TextView = cardview.findViewById(R.id.cardMajor)
        val cdGrade: TextView = cardview.findViewById(R.id.cardGrade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallingCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_eachone, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CallingCardAdapter.ViewHolder, position: Int) {
        val card = cards[position]
        holder.cdName.text = card.cardName
        holder.cdMajor.text = card.major
        holder.cdGrade.text = card.grade
    }

    override fun getItemCount() = cards.size
}