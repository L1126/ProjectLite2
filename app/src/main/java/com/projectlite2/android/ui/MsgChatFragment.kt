package com.projectlite2.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.adapter.MsgChatAdapter
import com.projectlite2.android.adapter.NewFriendCardAdapter
import kotlinx.android.synthetic.main.message_card_item.*
import okhttp3.internal.addHeaderLenient

class MsgChatFragment: Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: MsgChatAdapter

    private val msgList = ArrayList<Msg>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.message_box_fragment, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initMsg()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView = mView.findViewById(R.id.messageBox)
        mRecyclerView.layoutManager = layoutManager
        mAdapter = MsgChatAdapter(msgList)
        mRecyclerView.adapter = mAdapter
        
        //点击事件

    }

    private fun initMsg(){
        val msg1 = Msg("你好", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("你也好", Msg.TYPE_SENT)
        msgList.add(msg2)
    }
}