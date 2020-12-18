package com.example.project_final.fragment


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project_final.Abapter.Adapter2
import com.example.project_final.Model.Film1
import com.example.project_final.Model.model2

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_book_ticket.view.*

/**
 * A simple [Fragment] subclass.
 */
class Book_ticket : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.fab.visibility=View.GONE

        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_book_ticket, container, false)

        root.btn_pay_page.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,Pay_page()).addToBackStack(null).commit()
        }
        val b = arguments
       // var db = DatabaseHelper(activity)

        if (b != null) {
            var p=b.getParcelable<Film1>("id")
            root.txtbt.setText(p!!.name)
                    root.imgBo.setImageURI(Uri.parse(p.img))
            }
//            val data = db.getAllFilm()

//            var i=0
//            while (i<data.size){
//                Toast.makeText(activity,"${data[i].id}", Toast.LENGTH_LONG).show()
//                if (data[i].id == p){
//                    root.txtbt.setText(data[i].name)
//                    root.imgBo.setImageURI(Uri.parse(data[i].img))
//
//
//                    //  d.putString("namemo",data[i].name)
//
//                    Toast.makeText(activity,"${data[i].id}", Toast.LENGTH_LONG).show()
//                    break
//                }else{
//                    i++
//                }
//
//            }

        //}


        val arrayList = ArrayList<model2>()
        arrayList.add(model2("6:15 PM" ,"Audi: #09", "Filling Fast"))
        arrayList.add(model2("6:55 PM","Audi: #02","Filling Fast"))
        arrayList.add(model2("7:15 PM","Audi: #05","Filling Fast"))
        arrayList.add(model2("10:20 PM" ,"Audi: #06" , "Available"))
        arrayList.add(model2("11:25 PM","Audi: #09","Available"))
        root.recy1.layoutManager = GridLayoutManager(activity  , 3)
        val adapter = Adapter2(activity!! , arrayList)
        root.recy1.adapter=adapter



        val arrayList2 = ArrayList<model2>()
        arrayList2.add(model2("6:25 PM" ,"Audi: #02", "Filling Fast"))
        arrayList2.add(model2("6:35 PM","Audi: #01","Filling Fast"))
        arrayList2.add(model2("9:15 PM","Audi: #03","All Most Full"))
        arrayList2.add(model2("10:20 PM" ,"Audi: #04" , "Available"))
        arrayList2.add(model2("11:15 PM","Audi: #01","Available"))
        arrayList2.add(model2("11:45 PM","Audi: #02","Available"))
        root.recy2.layoutManager = GridLayoutManager(activity  , 3)
        val adapter2 =Adapter2(activity!! , arrayList2)
        root.recy2.adapter=adapter2

        val arrayList3 = ArrayList<model2>()
        arrayList3.add(model2("6:30 PM" ,"Audi: #03", "Filling Fast"))
        arrayList3.add(model2("7:45 PM","Audi: #05","Filling Fast"))
        arrayList3.add(model2("8:15 PM","Audi: #02","All Most Full"))
        arrayList3.add(model2("9:20 PM" ,"Audi: #01" , "Available"))
        root.recy3.layoutManager = GridLayoutManager(activity  , 3)
        val adapter3 =Adapter2(activity!! , arrayList3)
        root.recy3.adapter=adapter3

        return root
    }


}
