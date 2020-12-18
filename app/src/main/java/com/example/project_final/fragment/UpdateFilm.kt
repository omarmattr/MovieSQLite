package com.example.project_final.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_addfilm.*
import kotlinx.android.synthetic.main.fragment_addfilm.view.*
import kotlinx.android.synthetic.main.fragment_update_film.*
import kotlinx.android.synthetic.main.fragment_update_film.view.*
import kotlinx.android.synthetic.main.fragment_update_film.view.btn_add_film

/**
 * A simple [Fragment] subclass.
 */
class UpdateFilm : Fragment(), IPickResult {
    var imageURI:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar.title = "Edit Film"
        activity!!.fab.visibility=View.GONE
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_update_film, container, false)

        val db = DatabaseHelper(activity)

        root.up_img.setOnClickListener {
            //val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // startActivityForResult(i, 101)
            PickImageDialog.build(PickSetup()).setOnPickResult {
                onPickResult(it)

        }.setOnPickCancel {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer2, Home()).commit()
            }.show(activity)
        }

        var a = arguments





            root.btn_add_film.setOnClickListener {
                if (root.up_name.text.isNotEmpty() && root.up_time.text.isNotEmpty() && root.up_price.text.isNotEmpty()) {
            if (db.updataFilm(
                    a!!.getInt("update"),
                    imageURI,
                    root.up_name.text.toString(),
                    root.up_time.text.toString(),
                    root.up_price.text.toString(),
                    root.up_category.selectedItem.toString()
                )
            ) {

                Toast.makeText(activity, "Edited Successfully", Toast.LENGTH_SHORT)
                    .show()
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer2, Home()).commit()

            } else {
                Toast.makeText(activity, "Add Failed", Toast.LENGTH_SHORT).show()

            }
                 }else{
            Toast.makeText(activity, "Fill Fields", Toast.LENGTH_SHORT).show()
        }
            }

        return root
    }

      //  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           // super.onActivityResult(requestCode, resultCode, data)
           // if(resultCode == Activity.RESULT_OK && requestCode==101){
           //     imageURI = data!!.data.toString()
           //     Log.e("hzm",imageURI)
            //    up_img.setImageURI(data!!.data)
          //  }
       // }

    override fun onPickResult(r: PickResult?) {
        Log.e("hhhh","hhh" )
        if (r!!.error == null) {
            Log.e("hhhh","hhh2" )
            imageURI=r.getUri().toString()
            up_img.setImageURI(r.getUri())

        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(activity, r!!.error.message, Toast.LENGTH_LONG).show()
        }
    }

}
