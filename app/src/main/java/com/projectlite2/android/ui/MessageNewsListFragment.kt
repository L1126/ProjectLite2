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
import com.projectlite2.android.adapter.MessageCardAdapter
import com.projectlite2.android.adapter.MsgChatAdapter
import com.projectlite2.android.model.MessageCard
import com.projectlite2.android.utils.OnItemClickListenerPlus
import com.projectlite2.android.utils.SimpleItemTouchHelperCallback
import kotlinx.android.synthetic.main.message_card_item.*
import java.util.ArrayList

class MessageNewsListFragment(): Fragment() {

    lateinit var mView: View
    lateinit var mRecyclerview: RecyclerView
    lateinit var mAdapter: MessageCardAdapter
    lateinit var mCallBack: ItemTouchHelper.Callback
    private val mMessageList = ArrayList<MessageCard>()

    lateinit var cRecyclerView: MaxRecyclerView
    lateinit var cAdapter: MsgChatAdapter
    private val msgList = ArrayList<Msg>()
    private var CardClick = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_message_list, container, false)
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
        val mlayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerview = mView.findViewById(R.id.recyclerViewCardMessage)
        mRecyclerview.layoutManager = mlayoutManager
        mAdapter = MessageCardAdapter(mMessageList)
        mRecyclerview.adapter = mAdapter

        mRecyclerview.itemAnimator = DefaultItemAnimator()
        mRecyclerview.itemAnimator!!.changeDuration = 300

        //先实例化Callback
        mCallBack= SimpleItemTouchHelperCallback(mAdapter)
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        ItemTouchHelper(mCallBack).attachToRecyclerView(mRecyclerview)

        mAdapter.setOnKotlinItemClickListener(object : OnItemClickListenerPlus {
            override fun onClick(item: View?, position: Int, which: Int) {

                when(which){
                    R.id.messageCardBackground ->{

                        if (CardClick){
                            cRecyclerView = mView.findViewById(R.id.messageBox)
                            cRecyclerView.layoutManager = layoutManager
                            cAdapter = MsgChatAdapter(msgList)
                            cRecyclerView.adapter = cAdapter
                            val msg0 = Msg("报告这周六交", Msg.TYPE_RECEIVED)
                            msgList.add(msg0)

                            CardClick = false
                        }else{
                            cRecyclerView.scrollToPosition(msgList.size - 1)
                        }

                    }
                    R.id.btnReply ->{
                        cRecyclerView = mView.findViewById(R.id.messageBox)
                        cRecyclerView.layoutManager = layoutManager
                        cAdapter = MsgChatAdapter(msgList)
                        cRecyclerView.adapter = cAdapter

                        val content = textReply.text.toString()

                        if (content.isNotEmpty()){
                            val msg = Msg(content,Msg.TYPE_SENT)
                            msgList.add(msg)
                            cAdapter?.notifyItemInserted(msgList.size - 1)
                            cRecyclerView.scrollToPosition(msgList.size - 1)
                            textReply.setText("")
                        }

                    }
                }
            }
        })
    }

    private fun addNewCards(){
        mMessageList.add(MessageCard("小军","SRP","10:12","报告这周六交"))
        mMessageList.add(MessageCard("胖虎","互联网+","15:12","木棉开会"))
        mMessageList.add(MessageCard("静香","SRP","9:12","原型已经发给你了"))
    }
}