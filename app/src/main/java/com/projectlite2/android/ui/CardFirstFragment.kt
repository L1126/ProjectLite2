package com.projectlite2.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.projectlite2.android.R
import com.projectlite2.android.adapter.CallingCardAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.CallingCard
import java.util.*

class CardFirstFragment : Fragment() {

    lateinit var cView: View
    lateinit var cRecyclerView: RecyclerView
    lateinit var cAdapter: CallingCardAdapter

    private val cardList = ArrayList<CallingCard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        cView = inflater.inflate(R.layout.card_calling_fragment, container, false)
        return cView;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addCards()

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        cRecyclerView = cView.findViewById(R.id.recyclerViewCard1)
        cRecyclerView.layoutManager = layoutManager
        cAdapter = CallingCardAdapter(cardList)
        cRecyclerView.adapter = cAdapter
        //初始化列表数据
        cAdapter.setOnKotlinItemClickListener(object : CallingCardAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                MyApplication.showToast(cardList[position].name)
            }
        })
    }

    private fun addCards() {
        cardList.add(CallingCard("小红", "交互设计", "2018级"))
        cardList.add(CallingCard("小白", "计算机工程", "2017级"))
        cardList.add(CallingCard("小林", "软件工程", "2019级"))
        cardList.add(CallingCard("小吴", "工业设计", "2020级"))
    }

}