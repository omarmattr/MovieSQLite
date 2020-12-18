package com.example.project_final.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_final.Abapter.Adapter
import com.example.project_final.Model.Film1

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_view_all.view.*

/**
 * A simple [Fragment] subclass.
 */
class viewAll : Fragment(), Adapter.onClick {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar.visibility=View.GONE
        activity!!.fab.visibility=View.GONE

        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_view_all, container, false)

        var db = DatabaseHelper(activity)
        val sharedPref = activity!!.getSharedPreferences("login", Context.MODE_PRIVATE)
       var category= sharedPref.getString("category","Error")
        var data=db.getAllFilm()
        root.view_all_name.text=category
        var b=arguments

        if (category=="Action"){
            data = db.getActionfilm()

        }else if(category=="romantic"){
            data = db.getromanticfilm()
        }else if (category=="horror"){
            data = db.gethorrorfilm()
        }else if (category=="all"){
            data = db.getAllFilm()
        }else if (category=="drama"){
            data = db.getdramafilm()
        }else if (category=="search"){
            data = db.getsearchfilm(sharedPref.getString("ca","Error").toString())
        }else{
            Toast.makeText(activity,"$category",Toast.LENGTH_LONG).show()
            //Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
        }
       //root.view_re.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL , false)
        root.view_re.layoutManager  = GridLayoutManager(activity,2)
        val myAdapter = Adapter(activity!! , data,R.layout.recycle2,this)
        root.view_re.adapter =myAdapter
        return root
    }

    override fun onClickItem(position: Int) {


        var d=Bundle()
        var fragment =   Movie_page()

        d.putInt("movie",position)
        //d.putParcelable("movie3",arrayList3!![position])
        fragment.arguments=d

//        Toast.makeText(activity, "sss", Toast.LENGTH_SHORT).show();

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
