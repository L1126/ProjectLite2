package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.InviteCard
import com.projectlite2.android.utils.ItemTouchHelperAdapter
import com.projectlite2.android.utils.OnItemClickListenerPlus
import java.util.*

class InviteCardAdapter(private var mData: MutableList<InviteCard>) :
        RecyclerView.Adapter<InviteCardAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var mShowAction: Animation
    private lateinit var mHiddenAction: Animation

    private var itemClickListener: OnItemClickListenerPlus? = null
    private var isFolded: Boolean = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cdCardBg: CardView = itemView.findViewById(R.id.inviteCardBackground)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
        val cdProName: TextView = itemView.findViewById(R.id.txtProName)
        val cdProNameBar: ConstraintLayout = itemView.findViewById(R.id.projectNameBar)
        val cdProTimeBar: ConstraintLayout = itemView.findViewById(R.id.projectTimeBar)
        val cdProHeadmanBar: ConstraintLayout = itemView.findViewById(R.id.projectHeadmanBar)
        val cdProIntroBar: ConstraintLayout = itemView.findViewById(R.id.projectIntro)
        val cdMessageNumBar: ConstraintLayout = itemView.findViewById(R.id.messageNum)
        val cdMessageReplyBar: ConstraintLayout = itemView.findViewById(R.id.messageReply)
        val cdToggleBar: ImageView = itemView.findViewById(R.id.imgToggleBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_invite_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: InviteCardAdapter.ViewHolder, position: Int) {
        val card = mData[position]

        holder.cdName.text = card.name
        holder.cdProName.text = card.proName

        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it,holder.adapterPosition,holder.itemView.id)
            initAnimations()
            when (isFolded) {
                true -> {
                    setCardFold(holder, View.VISIBLE, View.GONE)
                }
                false -> {
                    setCardFold(holder, View.GONE, View.VISIBLE)
                }
            }
            //折叠状态取反
            isFolded = !isFolded
//            MyApplication.showToast(style.toString())
        }

    }

    override fun getItemCount() = mData.size

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: OnItemClickListenerPlus) {
        this.itemClickListener = itemClickListener
    }

    //设置卡片展开与折叠
    private fun setCardFold(holder: InviteCardAdapter.ViewHolder, viewOption: Int, viewOption2: Int) {
        holder.cdProNameBar.visibility = viewOption
        holder.cdProTimeBar.visibility = viewOption
        holder.cdProHeadmanBar.visibility = viewOption
        holder.cdProIntroBar.visibility = viewOption
        holder.cdToggleBar.visibility = viewOption
        holder.cdMessageReplyBar.visibility = viewOption

        holder.cdMessageNumBar.visibility = viewOption2

    }

    //初始化成员动画
    private fun initAnimations() {
        mShowAction = AnimationUtils.loadAnimation(MyApplication.getContext(), R.anim.push_up_in)
        mHiddenAction = AnimationUtils.loadAnimation(MyApplication.getContext(), R.anim.push_up_out)
    }

    override fun onItemDissmiss(position: Int) {
        //移除数据
        mData.removeAt(position)
        notifyItemRemoved(position);
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        //交换位置
        Collections.swap(mData,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

}