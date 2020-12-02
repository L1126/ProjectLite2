package com.projectlite2.android.utils

import android.view.View

interface OnItemClickListenerPlus {
    /**
     * 点击item条目中某个控件回调的方法
     * @param item ListView中item布局的View对象
     * @param position item在ListView中所处的位置
     * @param which item中要点击的控件的id
     */
    fun onClick(item: View?, position: Int, which: Int)
}