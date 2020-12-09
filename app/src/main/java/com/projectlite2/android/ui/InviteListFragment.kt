package com.projectlite2.android.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.R
import com.projectlite2.android.adapter.InviteCardAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.InviteCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout

class InviteListFragment(): Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: InviteCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback
    lateinit var mRefresh: RefreshLayout
    private val mInviteList = ArrayList<InviteCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_invite_list, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewCardInvite)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = InviteCardAdapter(mInviteList)
        mRecyclerview.adapter = mAdapter

        mRecyclerview.itemAnimator = DefaultItemAnimator()
        mRecyclerview.itemAnimator!!.changeDuration = 300

        //先实例化Callback
        mCallBack= SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {

            }
        })


        mRefresh = mView.findViewById<SmartRefreshLayout>(R.id.smartRefresh)
        val myHeader = BezierRadarHeader(MyApplication.getContext())
        myHeader.setAccentColorId(R.color.white)
        myHeader.setPrimaryColorId(R.color.font_blue)
        mRefresh.setRefreshHeader(myHeader)
        mRefresh.setOnRefreshListener { refreshlayout ->
            refreshlayout.finishRefresh(800 /*,false*/) //传入false表示刷新失败
//            refreshData()
        }

    }

    private fun addNewCards(){
        mInviteList.add(InviteCard("小亮","设计心理学"))
        mInviteList.add(InviteCard("小熊","全景AR"))
        mInviteList.add(InviteCard("小吴","UX"))
        mInviteList.add(InviteCard("小熊","产品创新设计专题"))

    }
}