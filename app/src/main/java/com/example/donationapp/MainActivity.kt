package com.example.donationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.donationapp.databinding.ActivityMainBinding
import com.razorpay.Checkout

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        Checkout.preload(getApplicationContext());

        binding.donateButton.setOnClickListener {
            makeDonation()
        }
    }

    private fun makeDonation(){

    }

}