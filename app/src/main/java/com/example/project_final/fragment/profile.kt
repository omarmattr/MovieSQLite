package com.example.project_final.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project_final.MainActivity
import com.example.project_final.R
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_sigin_up.view.*


/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity!!.toolbar.visibility=View.VISIBLE
        activity!!.toolbar.title="Profile"
        activity!!.fab.visibility=View.GONE

        var root=inflater.inflate(R.layout.fragment_profile, container, false)
        val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        var name=""

           name= sharedPref.getString("name","no name").toString()
        //Toast.makeText(activity,name,Toast.LENGTH_LONG).show()
        root.name_pr.setText("$name")
        root.txtp.setText("$name")
        root.txtp2.setText(sharedPref.getString("email","no email").toString())
        root.txtp6.setText(sharedPref.getString("city","no city").toString())
        root.txtp7.setText(sharedPref.getString("country","no country").toString())
        root.city_pr.setText(sharedPref.getString("city","no city").toString())
        root.country_pr.setText(sharedPref.getString("country","no country").toString())
  root.Edit_profile.setOnClickListener {


    txtp.setEnabled(true)
      txtp2.setEnabled(true)
      txtp3.setEnabled(true)
      txtp4.setEnabled(true)
      txtp5.setEnabled(true)
      txtp6.setEnabled(true)
      txtp7.setEnabled(true)
}
root!!.btnSavep.visibility=View.GONE
var Ename=""
        var Eemail=""
        var Ecity=""
        var Ecountry=""

        root.txtp.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp.text.toString().trim()

                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
                Eemail=sharedPref.getString("email","no email").toString()

            }
            override fun afterTextChanged(s: Editable) {
                Ename=root.txtp.text.toString()
                //Toast.makeText(activity,aa,Toast.LENGTH_LONG).show()

            }
        })

        root.txtp2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp2.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
                Eemail=root.txtp2.text.toString()
            }
        })
        root.txtp3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp3.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        root.txtp4.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp4.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        root.txtp5.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp5.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        root.txtp6.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp6.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
                Ecity=root.txtp6.text.toString()
            }
        })
        root.txtp7.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                txtp7.text.toString().trim()
                root!!.btnSavep.visibility=View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
            }
            override fun afterTextChanged(s: Editable) {
                Ecountry=root.txtp7.text.toString()
            }
        })

        root.btnSavep.setOnClickListener {
            val editor = sharedPref.edit()

            editor.putString("name", Ename)
            editor.putString("email", Eemail)
            editor.putString("country", Ecountry)
            editor.putString("city", Ecity)
            editor.apply()
            root.txtp.setHint("$Ename")
            root.txtp2.setHint("$Eemail")
            root.txtp6.setHint("$Ecity")
            root.txtp7.setHint("$Ecountry")
            txtp.setEnabled(false)
            txtp2.setEnabled(false)
            txtp3.setEnabled(false)
            txtp4.setEnabled(false)
            txtp5.setEnabled(false)
            txtp6.setEnabled(false)
            txtp7.setEnabled(false)
            root!!.btnSavep.visibility=View.GONE
           // name= sharedPref.getString("name","no name").toString()
            //Toast.makeText(activity,name,Toast.LENGTH_LONG).show()
            root.name_pr.setText("$Ename")
            root.city_pr.setText("$Ecity")
            root.country_pr.setText("$Ecountry")

        }



        return root
    }


}
