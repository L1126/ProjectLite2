package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.utils.IKotlinItemClickListener

class ContactCardAdapter(private val cards: List<ContactCard>, private val style: Int) :
        RecyclerView.Adapter<ContactCardAdapter.ViewHolder>() {

    companion object {
        const val STYLE_PARAM_MY_CONTACTS = 0
        const val STYLE_PARAM_NEW_FRIENDS = 1
    }

    private var itemClickListener: IKotlinItemClickListener? = null
    private var isFolded: Boolean = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cdCardBg: CardView = itemView.findViewById(R.id.contactCardBackground)
        val cdAvatar: ImageView = itemView.findViewById(R.id.picAvatar)
        val cdBadgeStatus: ImageView = itemView.findViewById(R.id.badgeStatus)
        val cdTagsTitle: RecyclerView = itemView.findViewById(R.id.rvTagsTitle)
        val cdStatus: TextView = itemView.findViewById(R.id.txtStatus)
        val cdMoreInformation: TextView = itemView.findViewById(R.id.txtMoreMessage)
        val cdButtonAgree: Button = itemView.findViewById(R.id.btnAgree)
        val cdButtonCancel: Button = itemView.findViewById(R.id.btnCancel)
        val cdTagsAll: RecyclerView = itemView.findViewById(R.id.rvTagsAll)
        val cdInfoType: TextView = itemView.findViewById(R.id.txtInfoType)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
        val cdMajor: TextView = itemView.findViewById(R.id.txtGrade)
        val cdGrade: TextView = itemView.findViewById(R.id.txtMajor)
        val cdToggle: ImageView = itemView.findViewById(R.id.imgToggleBar)
        val cdBtnMenu:ImageView =itemView.findViewById(R.id.btnMenu)
        val cdBtnMore:ImageView =itemView.findViewById(R.id.btnMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]



        holder.cdName.text = card.name
        holder.cdMajor.text = card.major
        holder.cdGrade.text = card.grade



        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)

            when (isFolded) {
                true -> {
                    setCardFold(holder, View.VISIBLE)

                }
                false -> {
                    setCardFold(holder, View.GONE)

                }
            }
            isFolded=!isFolded
            MyApplication.showToast(isFolded.toString())
        }

    }

    override fun getItemCount() = cards.size

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    private fun setCardFold(holder: ViewHolder, viewOption: Int) {
        holder.cdInfoType.visibility = viewOption
        holder.cdMoreInformation.visibility = viewOption
        holder.cdTagsAll.visibility = viewOption
        holder.cdToggle.visibility = viewOption
        holder.cdButtonAgree.visibility = viewOption
        holder.cdButtonCancel.visibility = viewOption

        //  修改holder.cdBtnMore.的图片资源
    }


}