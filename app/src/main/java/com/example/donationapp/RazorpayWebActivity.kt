package com.example.donationapp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.donationapp.databinding.ActivityRazorpayWebBinding

class RazorpayWebActivity : AppCompatActivity() {

    lateinit var binding: ActivityRazorpayWebBinding
    var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_razorpay_web)

        mWebView = binding.webView
        mWebView!!.loadUrl("https://rzp.io/l/aJZrSyWI")


    }

}