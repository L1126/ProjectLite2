package com.projectlite2.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.model.NewFriendCard
import com.projectlite2.android.adapter.NewFriendCardAdapter
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import java.util.ArrayList

class CardSecondFragment : Fragment() {

    lateinit var cSecView: View
    lateinit var cSecRecyclerView: RecyclerView
    lateinit var cSecAdapter: NewFriendCardAdapter

    private val cardSecList = ArrayList<NewFriendCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        cSecView = inflater.inflate(R.layout.card_new_fragment, container, false)
        return cSecView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNewCards()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        cSecRecyclerView = cSecView.findViewById(R.id.recyclerViewCard2)
        cSecRecyclerView.layoutManager = layoutManager
        cSecAdapter = NewFriendCardAdapter(cardSecList)
        cSecRecyclerView.adapter = cSecAdapter
        //初始化列表数据
        cSecAdapter.setOnKotlinItemClickListener(object : NewFriendCardAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                MyApplication.showToast(cardSecList.get(position).name)
            }
        })
    }

    private fun addNewCards(){
        cardSecList.add(NewFriendCard("小张", "工业设计", "2018级"))
        cardSecList.add(NewFriendCard("院长", "工业设计", "2018级"))
    }
}