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
import java.util.*

class WPStudyListFragment : Fragment() {

    lateinit var linearLayout: LinearLayout
    lateinit var lview: View

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback

    private val mWPtList = ArrayList<WorkPlaceCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.workplace_study_list_frag, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayout = requireActivity().findViewById(R.id.toolBtnBar)
        lview = requireActivity().findViewById(R.id.view5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewStudy)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = WPCardAdapter(mWPtList, 1)
        mRecyclerview.adapter = mAdapter

        linearLayout.visibility = View.VISIBLE
        lview.visibility = View.VISIBLE

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
        mWPtList.add(WorkPlaceCard("A文件", "无介绍", "2020/7/6","6：13"))
        mWPtList.add(WorkPlaceCard("B文件", "简略介绍", "2020/10/6","10：35"))
        mWPtList.add(WorkPlaceCard("C文件", "无介绍", "2020/11/5","15：27"))
        mWPtList.add(WorkPlaceCard("D文件", "无介绍", "2020/12/1","22：17"))
    }

}