package com.example.mylitedislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //var like:Int=0
    var dislike: Int=0
    lateinit var counterViewModel:CounterViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Main Activity","onCreate")

            //init view model
        counterViewModel= ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //init shared pref
        sharedPreferences=getPreferences(Context.MODE_PRIVATE)  //only 1 activity


        imageViewLike.setOnClickListener{
            counterViewModel.incrementLike()
            textViewLike.text=counterViewModel.likeCount.toString()
        }

        imageViewDislike.setOnClickListener{
            dislike++
            textViewDislike.text=dislike.toString()
        }
    }

    override fun onStart(){
        Log.d("Main Activity","onStart")
        super.onStart()
    }
    override fun onResume(){
        Log.d("Main Activity","onResume")
        val like=sharedPreferences.getInt(getString(R.string.like),0)
        counterViewModel.likeCount=like

        textViewLike.text=counterViewModel.likeCount.toString()
        textViewDislike.text=dislike.toString()
        super.onResume()
    }
    override fun onPause(){
        Log.d("Main Activity","onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like),counterViewModel.likeCount)
            apply() //commit()

        }
        super.onPause()
    }
    override fun onStop(){
        Log.d("Main Activity","onStop")
        super.onStop()
    }
    override fun onDestroy(){
        Log.d("Main Activity","onDestroy")
        super.onDestroy()
    }
}
