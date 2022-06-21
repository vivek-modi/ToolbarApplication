package com.example.toolbarapplication

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class CartActivity : AppCompatActivity() {

    internal var menuView: View? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)
        val menuItem = menu.findItem(R.id.menu_item_cart)
        menuView = menuItem?.actionView
        menuView?.setOnClickListener {
            menuItem?.let {
                onOptionsItemSelected(menuItem)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_item_cart) {
            Log.e("Title", "You clicked on item")
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}