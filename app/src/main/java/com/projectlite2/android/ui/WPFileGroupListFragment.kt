package com.projectlite2.android.ui

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
import com.projectlite2.android.adapter.WPCardAdapter
import com.projectlite2.android.model.WorkPlaceCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback

class WPFileGroupListFragment(private val style_group: Int) : Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback

    private val mFlieList = ArrayList<WorkPlaceCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.workplace_grouplist_frag, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards(style_group)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewGruop)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = WPCardAdapter(mFlieList,3)
        mRecyclerview.adapter = mAdapter

        //先实例化Callback
        mCallBack= SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus{
            override fun onClick(item: View?, position: Int, which: Int) {

            }
        })
    }

    private fun addNewCards( _style: Int){

        when(_style){

            0 -> {
                mFlieList.add(WorkPlaceCard("背景调研","","2020/9/15","21：03"))
                mFlieList.add(WorkPlaceCard("焦点小组","","2020/9/20","22：25"))
                mFlieList.add(WorkPlaceCard("问卷","","2020/9/21","23：24"))
                mFlieList.add(WorkPlaceCard("竞品分析","","2020/10/5","19：35"))
            }

            1 -> {
                mFlieList.add(WorkPlaceCard("低保真","","2020/10/15","22: 36"))
                mFlieList.add(WorkPlaceCard("迭代","","2020/10/26","22：17"))
            }

            2 -> {
                mFlieList.add(WorkPlaceCard("登录注册","","2020/11/12","00：18"))
                mFlieList.add(WorkPlaceCard("布局","","2020/11/18","23：38"))
                mFlieList.add(WorkPlaceCard("数据库","","2020/12/1","01：15"))
            }

            3 -> {
                mFlieList.add(WorkPlaceCard("迷你APP","","2020/12/3","15：45"))
                mFlieList.add(WorkPlaceCard("文档","","2020/12/5","19：33"))
            }

            4 -> {
                mFlieList.add(WorkPlaceCard("文档素材","","2020/11/5","19：58"))
                mFlieList.add(WorkPlaceCard("其他","","2020/11/9","14：22"))
            }
        }
    }

}