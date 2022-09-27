package com.example.mywords

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ChargeLevel : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
      Toast.makeText(p0, "You're about to run out of charge.",Toast.LENGTH_SHORT).show()
    }
}