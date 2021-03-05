package com.effective.android.panel.interfaces.listener

import android.view.View

/**
 * listen to  [android.widget.EditText] focus change
 * Created by XXF on 18-7-07
 *
 * blog: XXF.com
 * update 2020/05/08 支持 dsl
 */
interface OnEditFocusChangeListener {
    fun onFocusChange(view: View?, hasFocus: Boolean)
}
private typealias OnFocusChange = (view: View?, hasFocus: Boolean) -> Unit

class OnEditFocusChangeListenerBuilder : OnEditFocusChangeListener {

    private var onFocusChange: OnFocusChange? = null

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        onFocusChange?.invoke(view, hasFocus)
    }

    fun onFocusChange(onFocusChange: OnFocusChange) {
        this.onFocusChange = onFocusChange
    }
}
