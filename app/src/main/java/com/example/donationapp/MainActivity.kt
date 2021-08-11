package com.example.donationapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.donationapp.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {

    val TAG:String = MainActivity::class.toString()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        Checkout.preload(applicationContext)

        //opening checkout at the click of button
        binding.donateButton.setOnClickListener {
            makeDonation()
        }

        //opens web payment page
        binding.donateMail.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://rzp.io/l/aJZrSyWI")))
        }
    }

    private fun makeDonation() {

        val activity: Activity = this
        val co = Checkout()                 //instance of Checkout

        //passing the payment details and options as a JSONObject
        try {
            val options = JSONObject()
            options.put("name","Bandhan Corp.")
            options.put("description","Donate money")
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#4CAF50")
            options.put("currency","INR")
            options.put("amount","30000")
            options.put("send_sms_hash",true)

            val prefill = JSONObject()
            prefill.put("email","parbhatbandhan@gmail.com")
            prefill.put("contact","6766246956")

            options.put("prefill",prefill)
            co.open(activity,options)                      //launches the Checkout form where the customer completes the payment

        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        try{
            Toast.makeText(this,"Payment failed $errorCode \n $response",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        try{
            Toast.makeText(this,"Payment Successful $razorpayPaymentId",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

}
