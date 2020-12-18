package com.example.project_final.fragment


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project_final.Model.Film1

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_movie_page.view.*
import kotlinx.android.synthetic.main.fragment_pay_page.*

/**
 * A simple [Fragment] subclass.
 */
class Movie_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity!!.toolbar.visibility=View.GONE
        activity!!.fab.visibility=View.GONE

        var root = inflater.inflate(R.layout.fragment_movie_page, container, false)
       // var bun=Bundle()


        val b = arguments
        var db = DatabaseHelper(activity)

        if (b != null) {
            var p=b.getInt("movie")
            val data = db.getAllFilm()

        //  var i=0
          //  while (i<data.size){
           for (i in data){
              if (i.id == p){
                  root.mp_txt.setText(i.name)
                  root.time_mo.text=i.time
                  root.imgMo.setImageURI(Uri.parse(i.img))

                  root.btn_book_ticet.setOnClickListener {
                      var bo=Book_ticket()
                      var bun=Bundle()
                      bun.putParcelable("id",i)
                      bo.arguments=bun
                      activity!!.supportFragmentManager.beginTransaction()
                          .replace(R.id.mainContainer2,bo ).addToBackStack(null).commit()
                  }
                //  d.putString("namemo",data[i].name)

                  Toast.makeText(activity,"${i.id}", Toast.LENGTH_LONG).show()
                  break
              }//else{
                 // i++
              //}

              }

           }
           // root.imgMo.setImageResource(send.img)



        return root


    }
}
