package com.example.project_final.fragment


import android.content.Context
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment(),Adapter.onClick{

    //var data:ArrayList<Film1>?=null
   // var aerialist:ArrayList<Film1>?=null
   // var arrayList3:ArrayList<Film1>?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity!!.toolbar.visibility=View.VISIBLE
        activity!!.toolbar.title="Home"
        activity!!.fab.visibility=View.VISIBLE

        val root= inflater.inflate(R.layout.fragment_home, container, false)


        val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
            root.view_all_home.setOnClickListener {
                editor.putString("category","all")
                editor.apply()
                  activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,viewAll()).addToBackStack(null).commit()
     }
        root.view_all_home2.setOnClickListener {
            editor.putString("category","Action")
            editor.apply()
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,viewAll()).addToBackStack(null).commit()
        }

       val db = DatabaseHelper(activity)
       val data = db.getAllFilm()
        root.rcMov.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)

        val myAdapter = Adapter(activity!! , data,R.layout.recyclehome,this)
        root.rcMov.adapter =myAdapter

       val aerialist =db.getActionfilm()


        root.rcMov2.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)
        root.rcMov2.setHasFixedSize(true)

        val  dapter = Adapter(activity!! , aerialist, R.layout.recycle2,this)
        root.rcMov2.adapter =dapter



        val arrayList3 = db.getromanticfilm()


        root.rcMov3.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)


        val  dapter3 = Adapter(activity!! , arrayList3,R.layout.recycle2,this)
        root.rcMov3.adapter =dapter3



        return root
    }

    override fun onClickItem(position: Int) {


        val d=Bundle()
        val fragment =   Movie_page()
        fragment.arguments=d

//        Toast.makeText(activity, "sss", Toast.LENGTH_SHORT).show();

        activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,fragment).addToBackStack(null).commit()

    }
    override fun onLongClickItem(view: View,position: Int) {

            val db = DatabaseHelper(activity)

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
                     //   Toast.makeText(activity, "No", Toast.LENGTH_SHORT).show()
                    }



                    alertDialog.create().show()
                }
                R.id.editFilm -> {

                    val d=Bundle()
                    val fragment =   UpdateFilm()

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
