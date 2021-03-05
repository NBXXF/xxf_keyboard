package com.example.demo.scene.api

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.effective.R
import com.effective.android.panel.interfaces.ContentScrollMeasurer
import com.effective.android.panel.view.content.ContentContainerImpl
import com.effective.android.panel.view.content.IContentContainer
import com.effective.android.panel.view.content.IInputAction
import com.effective.android.panel.view.content.IResetAction

/**
 * 实现IContentContainer接口，可参考
 * [com.effective.android.panel.view.content.FrameContentContainer] 等库提供的模版实现基础的container容器
 * demo已约束布局为例子，使用者按需扩展就可以了
 * Created by XXF on 2020/05/07
 *
 * blog: XXF.com
 */
class CusContentContainer @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr), IContentContainer {
    @IdRes
    private var editTextId = 0

    @IdRes
    private var resetViewId = 0
    private var autoResetByOnTouch: Boolean = true
    private lateinit var contentContainer: ContentContainerImpl
    private fun initView(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CusContentContainer, defStyleAttr, 0)
        editTextId = typedArray.getResourceId(R.styleable.CusContentContainer_edit_view, -1)
        resetViewId = typedArray.getResourceId(R.styleable.CusContentContainer_auto_reset_area, -1)
        autoResetByOnTouch = typedArray.getBoolean(R.styleable.CusContentContainer_auto_reset_enable, autoResetByOnTouch)
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        contentContainer = ContentContainerImpl(this, autoResetByOnTouch, editTextId, resetViewId)
        val editText = getInputActionImpl().getFullScreenPixelInputView()
        addView(editText, 0, LayoutParams(1, 1))
    }

    override fun layoutContainer(l: Int, t: Int, r: Int, b: Int,
                                 contentScrollMeasurers: MutableList<ContentScrollMeasurer>, defaultScrollHeight: Int, canScrollOutsize: Boolean,
                                 reset: Boolean) {
        contentContainer.layoutContainer(l, t, r, b, contentScrollMeasurers, defaultScrollHeight, canScrollOutsize, reset)
    }

    override fun findTriggerView(id: Int): View? {
        return contentContainer.findTriggerView(id)
    }

    override fun changeContainerHeight(targetHeight: Int) {
        contentContainer.changeContainerHeight(targetHeight)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val onTouchTrue = super.dispatchTouchEvent(ev)
        val hookResult = getResetActionImpl().hookDispatchTouchEvent(ev, onTouchTrue)
        return hookResult or onTouchTrue
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val onTouchBySelf = super.onTouchEvent(event)
        val hookResult = getResetActionImpl().hookOnTouchEvent(event)
        return onTouchBySelf or hookResult
    }

    override fun getInputActionImpl(): IInputAction = contentContainer.getInputActionImpl()

    override fun getResetActionImpl(): IResetAction = contentContainer.getResetActionImpl()

    init {
        initView(attrs, defStyleAttr, 0)
    }
}