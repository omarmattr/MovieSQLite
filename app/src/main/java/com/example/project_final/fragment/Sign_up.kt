package com.example.project_final.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project_final.Main2Activity

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.fragment__sign_in.view.*
import kotlinx.android.synthetic.main.fragment_sigin_up.view.*

/**
 * A simple [Fragment] subclass.
 */
class Sign_up : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var root=inflater.inflate(R.layout.fragment_sigin_up, container, false)

        val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)

        var db = DatabaseHelper(activity)

        root.btn_sigin_up.setOnClickListener {

            if(root.txtname_up.text!!.isEmpty()){
                root.name_up.setError("This field is empty")
            }else if (root.txtemail_up.text!!.isEmpty()){
                root.name_up.setError(null)
                root.email_up.setError("This field is empty")
            }else if (root.txtpass_up.text!!.isEmpty()){
                root.email_up.setError(null)
                root.pass_up.setError("This field is empty")
            }else if (root.txtpassre_up.text!!.isEmpty()){
                root.pass_up.setError(null)
                root.passre_up.setError("This field is empty")
            }else if (root.txtpassre_up.text.toString()!=root.txtpass_up.text.toString()){
                root.passre_up.setError("Password does not match")
            }else{
                val editor = sharedPref.edit()
                if (db.insertUser("${root.txtname_up.text.toString()}", "${root.txtpass_up.text.toString()}","${root.txtemail_up.text.toString()}")) {
                    //Toast.makeText(activity, "hahahaha", Toast.LENGTH_LONG).show()
                }
                editor.putString("name", root.txtname_up.text.toString())
                editor.putString("email", root.txtemail_up.text.toString())
               // editor.putBoolean("isLogin", true)
                editor.apply()

                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,Sign_in()).addToBackStack(null).commit()

           // var i= Intent(context, Main2Activity::class.java)
          //  startActivity(i)
              //  activity!!.finish()
            }
        }
        root.txt_sigin_in.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,Sign_in()).addToBackStack(null).commit()
        }
        return root
    }


}
/*
package com.application.storedata

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: UsersDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        tv_name.text = sharedPreferences.getString("name", "EMPTY")

        dbHelper = UsersDBHelper(this)

        if (dbHelper.getUserCount() == 1) {
            var cursor = dbHelper.getUserData(1)
            cursor.moveToFirst()
            ed_name.setText(cursor.getString(cursor.getColumnIndex("name")))
            ed_mobile.setText(cursor.getString(cursor.getColumnIndex("mobile")))
            ed_age.setText(cursor.getString(cursor.getColumnIndex("age")))
            ed_address.setText(cursor.getString(cursor.getColumnIndex("address")))
        }

        btn_save.setOnClickListener {

            var user = User(
                ed_name.text.toString(),
                ed_mobile.text.toString(),
                ed_age.text.toString(),
                ed_address.text.toString()
            )

            dbHelper.insertUser(user)

        }
    }
}


package com.application.storedata

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLogin",false)){
            startActivity(Intent(this,MainActivity::class.java))
        }

        btn_login.setOnClickListener {

            if (ed_username.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(this, "املاأ الفارغ", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString("name", ed_username.text.toString())
                editor.putBoolean("isLogin", true)
                editor.apply()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}


 */
