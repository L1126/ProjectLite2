package com.projectlite2.android.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.projectlite2.android.R

class ChatRoomSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatroom_setting)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //给左上角添加返回箭头
        supportActionBar!!.title = "群聊设置" //设置Title文字

    }

    //返回按钮功能
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> finish()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}