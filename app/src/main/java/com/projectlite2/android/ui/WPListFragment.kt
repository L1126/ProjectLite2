package com.projectlite2.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.R
import com.projectlite2.android.adapter.ContactCardAdapter
import com.projectlite2.android.adapter.WPCardAdapter
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.model.WorkPlaceCard
import com.projectlite2.android.utils.OnItemClickListener
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import java.util.ArrayList

class WPListFragment(private val style_param: Int) : Fragment() {

    companion object {

        private const val STYLE_PARAM_STUDY = 1
        private const val STYLE_PARAM_MEETING = 2

        @JvmStatic
        fun workPlaceStudy(): Int {
            return STYLE_PARAM_STUDY;
        }

        @JvmStatic
        fun workPlaceMeeting(): Int {
            return STYLE_PARAM_MEETING;
        }
    }

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback

    private val mWPtList = ArrayList<WorkPlaceCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.workplace_list_frag, container, false)
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
        mRecyclerview = mView.findViewById(R.id.recyclerViewList)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = WPCardAdapter(mWPtList, style_param)
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
        mWPtList.add(WorkPlaceCard("A文件", "无介绍", "2020/7/6","6：13"))
        mWPtList.add(WorkPlaceCard("B文件", "简略介绍", "2020/10/6","10：35"))
        mWPtList.add(WorkPlaceCard("C文件", "无介绍", "2020/11/5","15：27"))
        mWPtList.add(WorkPlaceCard("D文件", "无介绍", "2020/12/1","22：17"))
    }

}