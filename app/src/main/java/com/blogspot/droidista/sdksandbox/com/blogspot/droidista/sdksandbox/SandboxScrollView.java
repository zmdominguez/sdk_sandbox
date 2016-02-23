package com.blogspot.droidista.sdksandbox.com.blogspot.droidista.sdksandbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by zarah.dominguez on 23/02/2016.
 */
public class SandboxScrollView extends ScrollView {
    private OnScrollViewListener mOnScrollViewListener;

    public SandboxScrollView(Context context) {
        super(context);
    }

    public SandboxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SandboxScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SandboxScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mOnScrollViewListener != null) {
            mOnScrollViewListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScrollListener(OnScrollViewListener l) {
        this.mOnScrollViewListener = l;
    }

    public interface OnScrollViewListener {
        void onScrollChanged(int l, int t, int oldl, int oldt );
    }
}
