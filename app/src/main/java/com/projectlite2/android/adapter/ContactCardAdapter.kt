package com.projectlite2.android.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.XPopup
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.utils.ItemTouchHelperAdapter
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.tencent.liteav.beauty.b.v
import java.util.*


class ContactCardAdapter(var ac: Activity, private var mData: MutableList<ContactCard>, val style: Int) :
        RecyclerView.Adapter<ContactCardAdapter.ViewHolder>()
        , ItemTouchHelperAdapter {

    companion object {
        const val STYLE_PARAM_MY_CONTACTS = 0
        const val STYLE_PARAM_NEW_FRIENDS = 1
    }

    private lateinit var mShowAction: Animation
    private lateinit var mHiddenAction: Animation

    private var itemClickListener: OnItemClickListenerPlus? = null
    private var isFolded: Boolean = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cdCardBg: CardView = itemView.findViewById(R.id.contactCardBackground)
        val cdConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
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
        val cdBtnMenu: ImageView = itemView.findViewById(R.id.btnMenu)
        val cdBtnMore: ImageView = itemView.findViewById(R.id.btnMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_card_item, parent, false)
        val viewHolder = ViewHolder(view)
        // 必须在事件发生前，调用这个方法来监视View的触摸
        val builder = XPopup.Builder(ac).watchView(view)


        //  整个卡片的点击
        viewHolder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it, viewHolder.adapterPosition, viewHolder.itemView.id)
            initAnimations()
            when (isFolded) {
                true -> {
//                    setCardFold(holder, View.VISIBLE)
//                    setLayoutStyle(holder, style)
                    setCardFold(viewHolder, View.VISIBLE)
                    setLayoutStyle(viewHolder, style)

                }
                false -> {
                    setCardFold(viewHolder, View.GONE)
                }
            }
            //折叠状态取反
            isFolded = !isFolded
//            MyApplication.showToast(style.toString())
        }
        //  整个卡片的长按
        viewHolder.itemView.setOnLongClickListener {
            view.setOnLongClickListener {
                builder.asAttachList(arrayOf("邀请到项目", "删除名片"), null
                ) { position, text ->

            val parentPosition = viewHolder.adapterPosition

//                    MyApplication.ToastyInfo(parentPosition.toString()+" "+position.toString())

                }

                        .show()
                false
            }


            false
        }


        //  菜单按钮的点击
        viewHolder.cdBtnMenu.setOnClickListener {
            itemClickListener!!.onClick(it, viewHolder.adapterPosition, viewHolder.cdBtnMenu.id)

        }
        //  同意按钮的点击
        viewHolder.cdButtonAgree.setOnClickListener {
            itemClickListener!!.onClick(it, viewHolder.adapterPosition, viewHolder.cdButtonAgree.id)
        }
        //  拒绝按钮的点击
        viewHolder.cdButtonCancel.setOnClickListener {
            itemClickListener!!.onClick(it, viewHolder.adapterPosition, viewHolder.cdButtonCancel.id)
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = mData[position]

        holder.cdName.text = card.name
        holder.cdMajor.text = card.major
        holder.cdGrade.text = card.grade
        holder.cdMoreInformation.text=card.brief

        if(card.param==STYLE_PARAM_MY_CONTACTS){
            holder.cdInfoType.text="擅长领域："
        }else{
            holder.cdInfoType.text="投递信息："
        }


    }

    override fun getItemCount() = mData.size

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: OnItemClickListenerPlus) {
        this.itemClickListener = itemClickListener
    }

    /**
     * 设置卡片的布局方式，1.已添加的卡片 2.新朋友卡片
     */
    private fun setLayoutStyle(holder: ViewHolder, style: Int) {

        if (style == STYLE_PARAM_MY_CONTACTS) {

            holder.cdTagsAll.visibility = View.VISIBLE
            holder.cdTagsTitle.visibility = View.VISIBLE
            holder.cdButtonAgree.visibility = View.GONE
            holder.cdButtonCancel.visibility = View.GONE

            holder.cdButtonCancel.startAnimation(mHiddenAction)
            holder.cdButtonAgree.startAnimation(mHiddenAction)
            holder.cdTagsAll.startAnimation(mShowAction)
            holder.cdTagsTitle.startAnimation(mShowAction)

        } else if (style == STYLE_PARAM_NEW_FRIENDS) {

            holder.cdButtonAgree.visibility = View.VISIBLE
            holder.cdButtonCancel.visibility = View.VISIBLE
            holder.cdTagsAll.visibility = View.GONE
            holder.cdTagsTitle.visibility = View.GONE

            holder.cdButtonCancel.startAnimation(mShowAction)
            holder.cdButtonAgree.startAnimation(mShowAction)
            holder.cdTagsAll.startAnimation(mHiddenAction)
            holder.cdTagsTitle.startAnimation(mHiddenAction)

        }
    }

    /**
     * 设置卡片的展开和折叠
     */
    private fun setCardFold(holder: ViewHolder, viewOption: Int) {
        holder.cdInfoType.visibility = viewOption
        holder.cdMoreInformation.visibility = viewOption
        holder.cdTagsAll.visibility = viewOption
        holder.cdToggle.visibility = viewOption
        holder.cdButtonAgree.visibility = viewOption
        holder.cdButtonCancel.visibility = viewOption
        if (viewOption == View.VISIBLE) {
            holder.cdBtnMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        } else {
            holder.cdBtnMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }
        //  修改holder.cdBtnMore.的图片资源
    }

    /**
     * 初始化动画成员
     */
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
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }


}