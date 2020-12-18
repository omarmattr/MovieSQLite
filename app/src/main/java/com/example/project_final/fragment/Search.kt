package com.example.project_final.fragment


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_final.Abapter.Adapter
import com.example.project_final.Model.Film1

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * A simple [Fragment] subclass.
 */
class Search : Fragment(),Adapter.onClick {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity!!.toolbar.visibility=View.VISIBLE
        activity!!.toolbar.title="Search"
        activity!!.fab.visibility=View.GONE

        var root=inflater.inflate(R.layout.fragment_search, container, false)

        var db = DatabaseHelper(activity)

 //       var data=""
   //     root.txt_input_search.addTextChangedListener(object : TextWatcher {
     //       override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
  //              data=root.txt_input_search.text.toString()
    //            // Toast.makeText(activity,data, Toast.LENGTH_LONG).show()
//
//
  //          }
    //        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){
//
//
  //          }
    //        override fun afterTextChanged(s: Editable) {
      //          data=root.txt_input_search.text.toString()
//
  //          }
    //    })
      //  val Arraying =db.getsearchfilm(data)
        //root.recy4.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)
        //val myAdapter = Adapter(activity!! , Arraying,R.layout.recyclehome,this)
        //root.recy4.adapter =myAdapter

root.LinearSe.visibility=View.GONE

    root.btn_search.setOnClickListener {
        var Arraying:ArrayList<Film1>
    if (root.txt_input_search.text.isNotEmpty()){
    var data=root.txt_input_search.text.toString()
   // Toast.makeText(activity,data, Toast.LENGTH_LONG).show()
        if (db.getsearchfilm(data).size!=0){
            root.recy4.visibility=View.VISIBLE
            root.LinearSe.visibility=View.VISIBLE
            Arraying =db.getsearchfilm(data)
    root.recy4.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)
    val myAdapter = Adapter(activity!! , Arraying,R.layout.recyclehome,this)
    root.recy4.adapter =myAdapter
            root.view_all_search.setOnClickListener {
               // var b=Bundle()
                val f=viewAll()
                val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("category","search")
                editor.putString("ca",data)
                editor.apply()
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,f).addToBackStack(null).commit()
            }
        }else{
            root.recy4.visibility=View.GONE
            root.LinearSe.visibility=View.GONE

            Toast.makeText(activity,"No Results", Toast.LENGTH_SHORT).show()}
    }else{
        root.recy4.visibility=View.GONE
        root.LinearSe.visibility=View.GONE
        Toast.makeText(activity,"The Fild Is Empty", Toast.LENGTH_SHORT).show()
    }

}
        root.recy5.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL , false)
      var  Arraying=db.getdramafilm()
       val myAdapter2 = Adapter(activity!! , Arraying,R.layout.recyclesearch,this)
       root.recy5.adapter =myAdapter2

        return root
    }
    override fun onClickItem(position: Int) {

        var d=Bundle()
        var fragment =   Movie_page()

        d.putInt("movie",position)
        //d.putParcelable("movie3",arrayList3!![position])
        fragment.arguments=d
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,fragment).addToBackStack(null).commit()


    }

    override fun onLongClickItem(view: View,position: Int) {
        var db = DatabaseHelper(activity)

        val popup = PopupMenu(activity, view)
        popup.menuInflater.inflate(R.menu.popupmenu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete ->{
                    val alertDialog = AlertDialog.Builder(activity!!)
                    alertDialog.setTitle("Delete Fime")
                    alertDialog.setMessage("Are you sure?")
                    alertDialog.setCancelable(false)
                    alertDialog.setIcon(R.drawable.ic_delete)
                    alertDialog.setPositiveButton("Yes") { _, _ ->
                        if(db.deleteFilm(position)){
                            Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
                            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,Home()).commit()
                        }else{
                            Toast.makeText(activity, "$position", Toast.LENGTH_SHORT).show()
                        }
                    }

                    alertDialog.setNegativeButton("No") { dialogInterface, i ->
                        Toast.makeText(activity, "No", Toast.LENGTH_SHORT).show()
                    }



                    alertDialog.create().show()
                }
                R.id.editFilm -> {

                    var d=Bundle()

                    var fragment =   UpdateFilm()

                    d.putInt("update",position)
                    fragment.arguments=d
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer2, fragment).addToBackStack(null).commit()
                }
            }
            true
        }
        popup.show()




    }

}
