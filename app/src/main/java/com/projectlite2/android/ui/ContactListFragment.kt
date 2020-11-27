package com.projectlite2.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.R
import com.projectlite2.android.adapter.ContactCardAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.ContactCard
import com.projectlite2.android.utils.IKotlinItemClickListener
import java.util.*


class ContactListFragment(private val style_param:Int) : Fragment() {

    companion object {

        private const val STYLE_PARAM_MY_CONTACTS=0
        private const val STYLE_PARAM_NEW_FRIENDS=1

        @JvmStatic
        fun setStyleMyContacts():Int{
            return STYLE_PARAM_MY_CONTACTS;
        }
        @JvmStatic
        fun setStyleNewFriends():Int{
            return STYLE_PARAM_NEW_FRIENDS;
        }
    }



    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: ContactCardAdapter


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
        mAdapter = ContactCardAdapter(mContactList,style_param)
        mRecyclerview.adapter = mAdapter

        //Log.d("MyTEST", "style_param: $style_param")

        mAdapter.setOnKotlinItemClickListener(object : IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
//                MyApplication.showToast(mContactList[position].name)

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
}