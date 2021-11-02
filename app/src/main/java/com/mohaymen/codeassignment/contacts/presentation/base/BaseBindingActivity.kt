package com.mohaymen.codeassignment.contacts.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.mohaymen.codeassignment.contacts.databinding.ActivityBaseBinding

/**
 *  A parent for every activity that wanna implement viewBinding.
 *
 */
abstract class
BaseBindingActivity<VB : ViewBinding> : AppCompatActivity() {


    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    private lateinit var baseViewBinding: ActivityBaseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewBinding = ActivityBaseBinding.inflate(layoutInflater)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)

    }

    /**
     * ContainerBase will hold view of subclasses
     */

    override fun setContentView(layoutResID: Int) {
        super.setContentView(baseViewBinding.root)
        baseViewBinding.containerBase.removeAllViews()
        baseViewBinding.containerBase.addView(layoutInflater.inflate(layoutResID, null))
        initView()
    }


    override fun setContentView(view: View?) {
        super.setContentView(baseViewBinding.root)
        baseViewBinding.containerBase.removeAllViews()
        baseViewBinding.containerBase.addView(view)
        initView()
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(baseViewBinding.root, params)
        baseViewBinding.containerBase.removeAllViews()
        baseViewBinding.containerBase.addView(view)
        initView()
    }

    abstract fun initView()


    fun displayProgress(isDisplayed: Boolean) {
        baseViewBinding.progressBarBase.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun displayMessage(message: String) {
        Snackbar.make(baseViewBinding.root, message, Snackbar.LENGTH_LONG).show()
    }


}