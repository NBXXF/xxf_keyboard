package com.effective.android.panel.interfaces.listener

import android.view.View

/**
 * preventing listeners that [com.effective.android.panel.PanelSwitchHelper] set these to view from being overwritten
 * Created by XXF on 18-7-07
 *
 * blog: XXF.com
 * update 2020/05/08 支持 dsl
 */
interface OnViewClickListener {
    fun onClickBefore(view: View?)
}

private typealias OnClickBefore = (view: View?) -> Unit

class OnViewClickListenerBuilder : OnViewClickListener {

    private var onClickBefore: OnClickBefore? = null

    override fun onClickBefore(view: View?) {
        onClickBefore?.invoke(view)
    }

    fun onClickBefore(onClickBefore: OnClickBefore) {
        this.onClickBefore = onClickBefore
    }
}