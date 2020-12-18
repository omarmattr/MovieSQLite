package com.example.project_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_final.Abapter.Adapter
import com.example.project_final.fragment.Wellcome
import kotlinx.android.synthetic.main.header_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("isLogin",false)){
            var i= Intent(this, Main2Activity::class.java)
            startActivity(i)
            finish()
        }
         setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, Wellcome()).commit()
    }

}
