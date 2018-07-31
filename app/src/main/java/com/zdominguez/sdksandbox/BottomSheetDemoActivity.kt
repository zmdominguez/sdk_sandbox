package com.zdominguez.sdksandbox

import android.animation.ObjectAnimator
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.zdominguez.sdksandbox.databinding.ActivityBottomSheetBinding

class BottomSheetDemoActivity : AppCompatActivity() {
    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    var showPlaceholder = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBottomSheetBinding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_sheet)
        binding.handlers = this
        binding.setLifecycleOwner(this)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // We want the button to animate when the user is dragging too
                // this means the ObjectAnimator stuff must move here
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Skip
            }

        })
    }

    fun onTotalsClicked(totalsButton: View) {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            ObjectAnimator.ofFloat(totalsButton, View.ROTATION, 0f, 180f).start()
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            ObjectAnimator.ofFloat(totalsButton, View.ROTATION, 180f, 360f).start()
        }
        showPlaceholder.value = bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED ||
            bottomSheetBehavior.state != BottomSheetBehavior.STATE_SETTLING
    }
}

@BindingAdapter("visible")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}