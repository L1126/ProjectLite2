package com.projectlite2.android.activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.adapter.MsgChatRoomAdapter
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()

    private var adapter: MsgChatRoomAdapter? = null
    lateinit var inputTxtView: EditText
    lateinit var btnSend: Button
    lateinit var btnPluse: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        initMsg()

        val layoutManager = LinearLayoutManager(this)
        recyclerViewRoom.layoutManager = layoutManager
        adapter = MsgChatRoomAdapter(msgList)
        recyclerViewRoom.adapter = adapter

        send.setOnClickListener(this)
        inputText.setOnClickListener(this)

        inputTxtView = this.findViewById<EditText>(R.id.inputText)
        btnSend = this.findViewById(R.id.send)
        btnPluse = this.findViewById(R.id.plus)

        //EditText内容监听
        inputTxtView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // 输入的内容变化的监听
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // 输入前的监听
            }
            override fun afterTextChanged(s: Editable) {
                // 输入后的监听
                val content = inputText.text.toString()
                if (content.isNotEmpty()){
                    btnSend.visibility = VISIBLE
                    btnPluse.visibility = GONE
                }else{
                    btnSend.visibility = GONE
                    btnPluse.visibility = VISIBLE
                }
            }
        })

//        inputTxtView.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
//            if (hasFocus) {
//                // 此处为得到焦点时的处理内容
//            } else {
//                // 此处为失去焦点时的处理内容
//            }
//        })
    }

    override fun onClick(v: View?) {
        when(v){
            send -> {
                val content = inputText.text.toString()
                if(content.isNotEmpty()){
                    msgList.add(Msg(content, Msg.TYPE_SENT))
                    adapter?.notifyItemInserted(msgList.size - 1)
                    recyclerViewRoom.scrollToPosition(msgList.size - 1)
                    inputText.setText("")
                }
            }
            inputText -> {
                recyclerViewRoom.scrollToPosition(msgList.size - 1)
            }
        }
    }

    private fun initMsg(){
        msgList.add(Msg("你的原型还要改一下",Msg.TYPE_RECEIVED))
        msgList.add(Msg("把交互操作梳理清楚",Msg.TYPE_RECEIVED))
        msgList.add(Msg("好哒！！！",Msg.TYPE_SENT))
        msgList.add(Msg("最晚明天下午发给我",Msg.TYPE_RECEIVED))

    }
}