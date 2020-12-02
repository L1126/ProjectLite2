package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.MessageCard
import com.projectlite2.android.utils.IKotlinItemClickListener
import com.projectlite2.android.utils.ItemTouchHelperAdapter
import java.util.*

class MessageCardAdapter(private var mData: MutableList<MessageCard>) :
        RecyclerView.Adapter<MessageCardAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var mShowAction: Animation
    private lateinit var mHiddenAction: Animation

    private var itemClickListener: IKotlinItemClickListener? = null
    private var isFolded: Boolean = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cdCardBg: CardView = itemView.findViewById(R.id.messageCardBackground)
        val cdConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        val cdConsMessageNum: ConstraintLayout = itemView.findViewById(R.id.messageNum)
        val cdConsMessageReply: ConstraintLayout = itemView.findViewById(R.id.messageReply)
        val cdAvatar: ImageView = itemView.findViewById(R.id.picAvatarMessage)
        val cdMessageAll: RecyclerView = itemView.findViewById(R.id.messageBox)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
        val cdGroup: TextView = itemView.findViewById(R.id.txtProject)
        val cdDate: TextView = itemView.findViewById(R.id.txtDate)
        val cdMessage: TextView = itemView.findViewById(R.id.txtMessage)
        val cdTime: TextView = itemView.findViewById(R.id.txtMessageTime)
        val cdToggleBar: ImageView = itemView.findViewById(R.id.imgToggleBar)
        val cdChatImage: ImageView = itemView.findViewById(R.id.messageChat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = mData[position]

        holder.cdName.text = card.name
        holder.cdGroup.text = card.group
        holder.cdTime.text = card.time
        holder.cdMessage.text = card.message

        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
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
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //设置卡片展开与折叠
    private fun setCardFold(holder: ViewHolder, viewOption: Int, viewOption2: Int) {
        holder.cdMessageAll.visibility = viewOption
        holder.cdConsMessageReply.visibility = viewOption
        holder.cdToggleBar.visibility = viewOption
        holder.cdChatImage.visibility = viewOption

        holder.cdDate.visibility = viewOption2
        holder.cdTime.visibility = viewOption2
        holder.cdMessage.visibility = viewOption2
        holder.cdConsMessageNum.visibility = viewOption2

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