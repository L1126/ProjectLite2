package com.projectlite2.android.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lxj.xpopup.XPopup
import com.projectlite2.android.R
import com.projectlite2.android.adapter.DeleteFileAdapter
import com.projectlite2.android.adapter.WPRecycleFileAdapter
import com.projectlite2.android.dialog.WPdeleteDialog
import com.projectlite2.android.dialog.WPrecoverDialog
import com.projectlite2.android.model.DeleteFileCard
import com.projectlite2.android.model.RecyclebinCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import java.util.*

class WPRecycbinFragment : Fragment() ,View.OnClickListener {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: WPRecycleFileAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback
    lateinit var wplayoutManager: RecyclerView.LayoutManager

    private val mDeleteBarList = ArrayList<RecyclebinCard>()
    private val mEveryCardDeleteList = ArrayList<ArrayList<DeleteFileCard>>()
    private val mEveryCardDeleteAdapterList = ArrayList<DeleteFileAdapter>()

    private var CardClick0 = true
    private var CardClick1 = true
    private var CardClick2 = true

    lateinit var mBtnDelete: CardView
    lateinit var mBtnRecover: CardView

    lateinit var mContext: Context

//    lateinit var cView: View
//    lateinit var cBtnCheck : CheckBox

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onClick(v: View?) {
        //删除
        if (v?.id == R.id.cardDelete){
            XPopup.Builder(mContext)
                    .asCustom(WPdeleteDialog(mContext)
                    .show())
        }
        //复原
        else if (v?.id == R.id.cardRecover){
            XPopup.Builder(mContext)
                    .asCustom(WPrecoverDialog(mContext)
                            .show())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.workplace_recyclebin_list_frag, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//    override fun onResume() {
//        super.onResume()
//        cView = wplayoutManager.findViewByPosition(0)!!
//
//        cBtnCheck = cView.findViewById<CheckBox>(R.id.checkButtonFat)
//
//        Log.d("KKang",cBtnCheck.toString())
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBtnDelete = mView.findViewById(R.id.cardDelete)
        mBtnDelete.setOnClickListener(this)

        mBtnRecover = mView.findViewById(R.id.cardRecover)
        mBtnRecover.setOnClickListener(this)

        addNewCards()

        //消息卡片
        wplayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewBin)
        mRecyclerview.layoutManager = wplayoutManager
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

                        val thisCardView = mRecyclerview.getChildAt(position)
                        val thisRV = thisCardView.findViewById<RecyclerView>(R.id.deleteFileBar)

                        thisRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                        thisRV.adapter = mEveryCardDeleteAdapterList[position]

                        when(position) {
                            0 -> {
                                if (CardClick0){
                                    mEveryCardDeleteList[0].add(DeleteFileCard("用户调研"))
                                    mEveryCardDeleteList[0].add(DeleteFileCard("用户访谈"))
                                    CardClick0 = false
                                }
                            }
                            1 -> {
                                if (CardClick1){
                                    mEveryCardDeleteList[1].add(DeleteFileCard("XXX 文件"))
                                    CardClick1 = false
                                }
                            }
                            2 -> {
                                if (CardClick2){
                                    mEveryCardDeleteList[2].add(DeleteFileCard("插图文件"))
                                    mEveryCardDeleteList[2].add(DeleteFileCard("按钮文件"))
                                    CardClick2 = false
                                }
                            }
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
        mEveryCardDeleteList.add(ArrayList<DeleteFileCard>())
        mEveryCardDeleteList.add(ArrayList<DeleteFileCard>())
        mEveryCardDeleteList.add(ArrayList<DeleteFileCard>())
        mEveryCardDeleteAdapterList.add(DeleteFileAdapter(mEveryCardDeleteList[0]))
        mEveryCardDeleteAdapterList.add(DeleteFileAdapter(mEveryCardDeleteList[1]))
        mEveryCardDeleteAdapterList.add(DeleteFileAdapter(mEveryCardDeleteList[2]))
    }
}