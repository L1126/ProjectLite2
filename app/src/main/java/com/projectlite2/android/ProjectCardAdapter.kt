package com.projectlite2.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectCardAdapter(private val projects: List<ProjectCard>) :
        RecyclerView.Adapter<ProjectCardAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pjName: TextView = itemView.findViewById(R.id.txtSettingItemName)
        val pjProgress:ProgressBar=itemView.findViewById(R.id.progressProject)
        val isLeader:TextView= itemView.findViewById(R.id.txtLeader)
        val dotMsgCount: ImageView = itemView.findViewById(R.id.dotMsgCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_card_item, parent, false)


        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val project = projects[position]
            MyApplication.showToast("${project.name} Clicked!")
        }


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectCardAdapter.ViewHolder, position: Int) {
        val project = projects[position]
        holder.pjName.text = project.name
        holder.pjProgress.progress = project.progress;
        if (project.isLeader){
            holder.isLeader.visibility = View.VISIBLE
        }else{
            holder.isLeader.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = projects.size


}