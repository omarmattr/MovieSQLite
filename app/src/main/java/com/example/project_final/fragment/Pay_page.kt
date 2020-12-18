package com.example.project_final.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.project_final.R
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_pay_page.view.*

/**
 * A simple [Fragment] subclass.
 */
class Pay_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_pay_page, container, false)
        root.mm.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,Home()).commit()
            Toast.makeText(activity,"Successfully",Toast.LENGTH_SHORT).show()
        }
        return root
    }


}
