package com.projectlite2.android.ui


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lxj.xpopup.XPopup
import com.projectlite2.android.R
import com.projectlite2.android.adapter.ContactCardAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import java.util.*


class ContactListFragment(var ac: Activity, private val style_param: Int) : Fragment() {

    companion object {

        private const val STYLE_PARAM_MY_CONTACTS = 0
        private const val STYLE_PARAM_NEW_FRIENDS = 1

        @JvmStatic
        fun setStyleMyContacts(): Int {
            return STYLE_PARAM_MY_CONTACTS;
        }

        @JvmStatic
        fun setStyleNewFriends(): Int {
            return STYLE_PARAM_NEW_FRIENDS;
        }
    }


    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: ContactCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback
    lateinit var mRefresh: RefreshLayout


    private val mContactList = ArrayList<ContactCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_contact_list, container, false)
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

        mRecyclerview = mView.findViewById(R.id.recyclerViewCard2)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = ContactCardAdapter(ac, mContactList, style_param)
        mRecyclerview.adapter = mAdapter

        mRecyclerview.itemAnimator = DefaultItemAnimator()
        mRecyclerview.itemAnimator!!.changeDuration = 300

        //先实例化Callback
        mCallBack = SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        Log.d("MyTEST", "style_param: $style_param")

        mRefresh = mView.findViewById<SmartRefreshLayout>(R.id.smartRefresh)
        val myHeader = BezierRadarHeader(MyApplication.getContext())
        myHeader.setAccentColorId(R.color.white)
        myHeader.setPrimaryColorId(R.color.font_blue)
        mRefresh.setRefreshHeader(myHeader)
        mRefresh.setOnRefreshListener { refreshlayout ->
            refreshlayout.finishRefresh(800 /*,false*/) //传入false表示刷新失败
//            refreshData()
        }


        //  点击事件
        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {
                when (which) {
                    R.id.btnMenu -> item?.let { showCardMenu(it, arrayOf("邀请到项目", "删除名片")) }
                    R.id.btnAgree -> MyApplication.ToastyInfo("click agree")
                    R.id.btnCancel -> MyApplication.ToastyInfo("click cancel")
                }

            }
        })


    }

    /**
     * 向卡片List添加卡片数据
     */
    private fun addNewCards() {

        if(style_param==STYLE_PARAM_MY_CONTACTS){
            mContactList.add(ContactCard("小林", "交互设计", "2018级","Alias建模、Android开发、关系数据库设计",style_param))
            mContactList.add(ContactCard("小陈", "工业设计", "2020级","犀牛建模、C4D动画、产品策划、用研",style_param))
            mContactList.add(ContactCard("康康", "交互设计", "2018级","Alias建模、3DMax建模、Unity开发",style_param))
            mContactList.add(ContactCard("学委", "交互设计", "2018级","产品手绘、UI设计、策划、产品设计",style_param))
        }else{
            mContactList.add(ContactCard("小王", "机械工程", "2019级","向你投递了名片",style_param))
        }

    }


    /**
     * 点击更多按钮监听中，调用本方法，显示更多菜单的popup window，传入的数组为菜单名称
     */
    private fun showCardMenu(v: View, arrMenuTitle: Array<String>) {
        XPopup.Builder(context)
                .atView(v) // 依附于所点击的View，内部会自动判断在上方或者下方显示
                .asAttachList(
                        arrMenuTitle,
                        intArrayOf()
                )
                { position, text ->
                    when (text) {
                        arrMenuTitle[0] -> {
                            MyApplication.ToastyInfo("还没实现呢")
                        }

                        arrMenuTitle[1] -> {
                            XPopup.Builder(context).asConfirm("提示", "确定要删除该名片吗？"
                            ) { MyApplication.ToastyInfo("点击了删除") }
                                    .show()
                        }
                    }
                }
                .show()
    }
}