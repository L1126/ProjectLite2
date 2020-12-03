package com.projectlite2.android.ui


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
import java.util.*


class ContactListFragment(private val style_param: Int) : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)



        mRecyclerview = mView.findViewById(R.id.recyclerViewCard2)
        mRecyclerview.layoutManager = layoutManager
        mAdapter = ContactCardAdapter(mContactList, style_param)
        mRecyclerview.adapter = mAdapter

        mRecyclerview.itemAnimator = DefaultItemAnimator()
        mRecyclerview.itemAnimator!!.changeDuration = 300

        //先实例化Callback
        mCallBack = SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)


        Log.d("MyTEST", "style_param: $style_param")

        //  点击事件
        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {
                when (which) {
                    R.id.btnMenu -> item?.let { showCardMenu(it, arrayOf("邀请", "删除")) }
                    R.id.btnAgree -> MyApplication.ToastyInfo("click agree")
                    R.id.btnCancel -> MyApplication.ToastyInfo("click cancel")
                }

            }
        })
    }

    private fun addNewCards() {
        mContactList.add(ContactCard("小张", "工业设计", "2018级"))
        mContactList.add(ContactCard("院长", "工业设计", "2018级"))
        mContactList.add(ContactCard("小张", "工业设计", "2018级"))
        mContactList.add(ContactCard("院长", "工业设计", "2018级"))
        mContactList.add(ContactCard("小张", "工业设计", "2018级"))
        mContactList.add(ContactCard("院长", "工业设计", "2018级"))
        mContactList.add(ContactCard("小张", "工业设计", "2018级"))
        mContactList.add(ContactCard("院长", "工业设计", "2018级"))
    }

    private fun showCardMenu(v: View, arrMenuTitle: Array<String>) {
        XPopup.Builder(context)
                .atView(v) // 依附于所点击的View，内部会自动判断在上方或者下方显示
                .asAttachList(
                        arrMenuTitle,
                        intArrayOf()
                )
                { position, text ->
                    when (text) {
                        arrMenuTitle[0]->{
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