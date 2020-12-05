package com.projectlite2.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.R
import com.projectlite2.android.adapter.WPCardAdapter
import com.projectlite2.android.model.WorkPlaceCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import java.util.ArrayList

class WPMeetingListFragment : Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback

    private val mWPtList = ArrayList<WorkPlaceCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.workplace_meeting_list_frag, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewMeeting)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = WPCardAdapter(mWPtList, 2)
        mRecyclerview.adapter = mAdapter

        //先实例化Callback
        mCallBack= SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {
            }
        })
    }

    private fun addNewCards(){
        mWPtList.add(WorkPlaceCard("焦点小组汇报", "无介绍", "2020/8/26","6：24"))
        mWPtList.add(WorkPlaceCard("焦点小组汇报", "简略介绍", "2020/9/6","10：45"))
        mWPtList.add(WorkPlaceCard("时间安排", "A负责 XXX", "2020/9/8","15：57"))
        mWPtList.add(WorkPlaceCard("原型讨论", "确定低保真原型", "2020/9/16","12：17"))
    }
}