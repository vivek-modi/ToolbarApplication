package com.example.toolbarapplication

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.toolbarapplication.databinding.ToolBarLayoutBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

class ToolbarActivity : CartActivity() {

    private lateinit var binding: ToolBarLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolBarLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar()
        setupSearchView()
    }

    private fun actionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupSearchView() {
        var originalMargin = 0
        binding.consultationSearchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                    }
                    return true
                }
            })
            val params =
                binding.consultationSearchView.layoutParams as CollapsingToolbarLayout.LayoutParams
            originalMargin = params.marginStart
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                binding.appBar.setExpanded(!hasFocus)
                isSelected = hasFocus
                if (hasFocus) {
                  binding.toolbarTitle.visibility = View.GONE
                    params.marginStart = originalMargin + 150 // arbitrary constant
                } else {
                    binding.toolbarTitle.visibility = View.VISIBLE
                    params.marginStart = originalMargin
                }
                view.layoutParams = params
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view is SearchView.SearchAutoComplete) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect);
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    view.clearFocus()
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}