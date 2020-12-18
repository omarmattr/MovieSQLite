package com.example.project_final.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_final.Main2Activity

import com.example.project_final.R
import kotlinx.android.synthetic.main.fragment_wellcome.view.*

/**
 * A simple [Fragment] subclass.
 */
class Wellcome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var root=inflater.inflate(R.layout.fragment_wellcome, container, false)
        root.w_sign_in.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,Sign_in()).commit()
        }
        root.w_sign_up.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,Sign_up()).commit()
        }
        return root

    }


}
