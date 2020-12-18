package com.example.project_final.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.project_final.Main2Activity

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.fragment__sign_in.*
import kotlinx.android.synthetic.main.fragment__sign_in.view.*

/**
 * A simple [Fragment] subclass.
 */
class Sign_in : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment__sign_in, container, false)
        var db = DatabaseHelper(activity)
        val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        var data=db.getAllUser()
        root.btn_sigin_in.setOnClickListener {
        for (i in data){
            if(i.name==root.txtName_i.text.toString()&&i.password==root.txtPass_i.text.toString()){
                Toast.makeText(activity,"wellcome",Toast.LENGTH_LONG).show()
                val editor = sharedPref.edit()
                editor.putString("name", root.txtName_i.text.toString())
                editor.putBoolean("isLogin", true)
                editor.apply()
                var i=Intent(context,Main2Activity::class.java)
                startActivity(i)
                activity!!.finish()
                break
            }else if(root.txtName_i.text!!.isEmpty()){
                root.txtName_in.setError("This field is empty")
            }else if(root.txtPass_i.text!!.isEmpty()){
                root.txtName_in.setError(null)
                root.txtPass_i.setError("This field is empty")
            }else{
                Toast.makeText(activity,"An error in the username or password",Toast.LENGTH_LONG).show()

            }


        }}
/*
        root.btn_sigin_in.setOnClickListener {
            if(root.txtName_i.text!!.isEmpty()){
                root.txtName_in.setError("This field is empty")
            }else if (root.txtPass_i.text!!.isEmpty()){
                root.txtName_in.setError(null)
                root.txtPass_i.setError("This field is empty")
            }else{
                val editor = sharedPref.edit()

                editor.putBoolean("isLogin", true)
                editor.apply()

            var i=Intent(context,Main2Activity::class.java)
            startActivity(i)
                activity!!.finish()

        }}*/
        root.txt_sigin_up.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,Sign_up()).addToBackStack(null).commit()
        }
        return root

    }


}
