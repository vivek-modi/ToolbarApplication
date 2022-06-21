package com.example.toolbarapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toolbarapplication.databinding.ActivityMainBinding

class MainActivity : CartActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextActivity.setOnClickListener {
            Intent(this, ToolbarActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}