package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.projectlite2.android.R
import com.projectlite2.android.model.OrderStatus
import com.projectlite2.android.model.TimeLineModel
import com.projectlite2.android.utils.VectorDrawableUtils
import com.projectlite2.android.utils.formatDateTime

class TimeLineAdapter(private val mFeedList: List<TimeLineModel>) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    private lateinit var mLayoutInflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {

        if(!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }

        return TimeLineViewHolder(mLayoutInflater.inflate(R.layout.item_timeline, parent, false), viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {

        val timeLineModel = mFeedList[position]

        when {
            timeLineModel.status == OrderStatus.INACTIVE -> {
                setMarker(holder, R.drawable.ic_marker_inactive, R.color.colorGrey500)
            }
            timeLineModel.status == OrderStatus.ACTIVE -> {
                setMarker(holder, R.drawable.ic_marker_active, R.color.colorGrey500)
            }
            else -> {
                setMarker(holder, R.drawable.ic_marker, R.color.colorGrey500)
            }
        }

        if (timeLineModel.date.isNotEmpty()) {
            holder.date.visibility = View.VISIBLE
            holder.date.text = timeLineModel.date.formatDateTime("yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
        } else
            holder.date.visibility = View.GONE

        holder.message.text = timeLineModel.message
    }

    private fun setMarker(holder: TimeLineViewHolder, drawableResId: Int, colorFilter: Int) {
        holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, drawableResId, ContextCompat.getColor(holder.itemView.context, colorFilter))
    }

    override fun getItemCount() = mFeedList.size

    inner class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        val date :TextView= itemView.findViewById(R.id.text_timeline_date)
        val message :TextView= itemView.findViewById(R.id.text_timeline_title)
        val timeline :TimelineView= itemView.findViewById(R.id.timeline)

        init {
            timeline.initLine(viewType)
        }
    }

}