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
import com.projectlite2.android.MaxRecyclerView
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.adapter.DeleteFileAdapter
import com.projectlite2.android.adapter.MessageCardAdapter
import com.projectlite2.android.adapter.MsgChatAdapter
import com.projectlite2.android.adapter.WPRecycleFileAdapter
import com.projectlite2.android.model.DeleteFileCard
import com.projectlite2.android.model.MessageCard
import com.projectlite2.android.model.RecyclebinCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import java.util.ArrayList

class WPRecycbinFragment : Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPRecycleFileAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback
    private val mDeleteBarList = ArrayList<RecyclebinCard>()

    lateinit var dfRecyclerView: RecyclerView
    lateinit var dfAdapter: DeleteFileAdapter
    private val dfList = ArrayList<DeleteFileCard>()

    private var CardClick = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.workplace_recyclebin_frag, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()

        //消息卡片
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        val dflayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewBin)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = WPRecycleFileAdapter(mDeleteBarList)
        mRecyclerview.adapter = mAdapter

        //先实例化Callback
        mCallBack= SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        //点击事件
        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {

                when(which){
                    R.id.WPDeleteCardBackground -> {
                        if (CardClick){
                            dfRecyclerView = mView.findViewById(R.id.deleteFileBar)
                            dfRecyclerView.layoutManager = dflayoutManager
                            dfAdapter = DeleteFileAdapter(dfList)
                            dfRecyclerView.adapter = dfAdapter

                            dfList.add(DeleteFileCard("XXX 文件"))
                            dfList.add(DeleteFileCard("XX 文件"))

                            CardClick = false
                        }
                    }
                }
            }
        })
    }

    private fun addNewCards(){
        mDeleteBarList.add(RecyclebinCard("APP调研小组"))
        mDeleteBarList.add(RecyclebinCard("原型开发小组"))
        mDeleteBarList.add(RecyclebinCard("视觉设计小组"))
    }
}