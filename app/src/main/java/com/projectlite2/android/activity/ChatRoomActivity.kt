package com.projectlite2.android.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.adapter.MsgChatRoomAdapter
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.app.MyApplication.showToast
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity(), View.OnClickListener {

    private var toolBar: Toolbar? = null
    private var txtTitle: TextView? = null
    private var drawerLayout: DrawerLayout? = null

    private val msgList = ArrayList<Msg>()

    private var adapter: MsgChatRoomAdapter? = null
    lateinit var inputTxtView: EditText
    lateinit var btnSend: Button
    lateinit var btnPluse: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_chat_room)

        toolBar = this.findViewById(R.id.toolBar)
        txtTitle = this.findViewById(R.id.txtPageTitle)
        drawerLayout = this.findViewById(R.id.chatRoomDraw)

        txtTitle?.setText("四枚野指针")
        toolBar?.inflateMenu(R.menu.menu_chatroom)

        //图片显示
        val navigationView = findViewById<View>(R.id.navView) as NavigationView
        navigationView.itemIconTintList = null

        val headview = navigationView.inflateHeaderView(R.layout.nav_header)

        //工作空间界面跳转
        val work_place: ConstraintLayout = headview.findViewById<View>(R.id.workPlaceChatRoom) as ConstraintLayout
        work_place.setOnClickListener(View.OnClickListener {
//            showToast("work place")
            val workPlaceActivity = Intent(MyApplication.getContext(), WorkPlaceActivity::class.java)
            startActivity(workPlaceActivity)
            drawerLayout?.closeDrawers()
        })
        //群聊设置界面跳转
        val setting: ConstraintLayout = headview.findViewById<View>(R.id.settingChatRoom) as ConstraintLayout
        setting.setOnClickListener(View.OnClickListener {
//            showToast("setting")
            val chatRoomSettingActivity = Intent(MyApplication.getContext(), ChatRoomSettingActivity::class.java)
            startActivity(chatRoomSettingActivity)
            drawerLayout?.closeDrawers()
        })

        //消息卡片进入
        val extraData = intent.getStringExtra("Member")
        if (extraData == "Single"){
            txtTitle?.setText("大雄")
            msgList.clear()
            msgList.add(Msg("云端数据能够实现吗？",Msg.TYPE_SENT))
            msgList.add(Msg("正在努力！加油！打工人！",Msg.TYPE_RECEIVED))
        }

        //菜单目录点击
        navView.setCheckedItem(R.id.navGroup)
        navView.setNavigationItemSelectedListener {

            Log.d("KKang", it.toString())

            when(it.toString()){
                "群聊" ->{
                    txtTitle?.setText("四枚野指针")
                    msgList.clear()
                    initMsg()
                }
                "大雄" ->{
                    txtTitle?.setText("大雄")
                    msgList.clear()
                    msgList.add(Msg("云端数据能够实现吗？",Msg.TYPE_SENT))
                    msgList.add(Msg("正在努力！加油！打工人！",Msg.TYPE_RECEIVED))
                }
                "胖虎" ->{
                    txtTitle?.setText("胖虎")
                    msgList.clear()
                    msgList.add(Msg("原型快点做！",Msg.TYPE_RECEIVED))
                    msgList.add(Msg("做不出来",Msg.TYPE_RECEIVED))
                    msgList.add(Msg("把你按在地上摩擦（bushi）",Msg.TYPE_RECEIVED))
                }
                "静香" ->{
                    txtTitle?.setText("静香")
                    msgList.clear()
                    msgList.add(Msg("你先做设计规范吧",Msg.TYPE_RECEIVED))
                    msgList.add(Msg("OKk，莫问题",Msg.TYPE_SENT))
                    msgList.add(Msg("我原型做好了马上发你",Msg.TYPE_RECEIVED))
                }
            }

            drawerLayout?.closeDrawers()
            true
        }

        //  标题栏菜单点击
        toolBar?.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                //进入菜单
                R.id.btnMenu -> {
                    drawerLayout?.openDrawer(GravityCompat.END)
                }
                //进入项目树
                R.id.btnTree ->{

                }
            }
            true
        })

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