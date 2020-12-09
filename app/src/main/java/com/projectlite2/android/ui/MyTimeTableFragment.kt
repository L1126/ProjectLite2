package com.projectlite2.android.ui

import android.content.Intent
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.MaxRecyclerView
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.activity.ChatRoomActivity
import com.projectlite2.android.adapter.MessageCardAdapter
import com.projectlite2.android.adapter.MsgChatAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.MessageCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import java.util.*

class MyTimeTableFragment() : Fragment() {

    lateinit var mView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_time_table, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }


}