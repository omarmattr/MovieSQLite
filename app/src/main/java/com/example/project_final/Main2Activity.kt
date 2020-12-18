package com.example.project_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.project_final.db.DatabaseHelper
import com.example.project_final.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar.*

import kotlinx.android.synthetic.main.header_toolbar.*
import kotlinx.android.synthetic.main.header_toolbar.view.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //img_pro.setImageResource(R.drawable.sigin_in)
        toolbar.title="Home"

        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        nav_view.getHeaderView(0).name_he.text= sharedPref.getString("name","no name")
        nav_view.getHeaderView(0).email_he.text= sharedPref.getString("email","no name")

        //name_he.text=name

       //Toast.makeText(this,name,Toast.LENGTH_LONG).show()
        fab.setOnClickListener {

supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,addfilm()).addToBackStack(null).commit()
        }

        supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,Home()).commit()
        setSupportActionBar(toolbar)
        title = null

        nav_view.itemIconTintList=null

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //For Default Selected Item at Navigation Drawer
        nav_view.menu.getItem(0).isChecked = true
        onNavigationItemSelected(nav_view.menu.getItem(0))

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()


        when (item.itemId) {
            R.id.m_home -> {
                replaceFragment(Home())
            }
            R.id.m_Profile -> {
                replaceFragment(profile())
            }
            R.id.m_search -> {
                replaceFragment(Search())
            }
            R.id.Action -> {
                editor.putString("category","Action")
                editor.apply()
                replaceFragment(viewAll())
            }
            R.id.romantic -> {
                editor.putString("category","romantic")
                editor.apply()
                replaceFragment(viewAll())
            }
            R.id.horror -> {
                editor.putString("category","horror")
                editor.apply()
                replaceFragment(viewAll())
            }
            R.id.drama -> {
                editor.putString("category","drama")
                editor.apply()
                replaceFragment(viewAll())
            }
            R.id.m_Log_out -> {

                editor.clear().apply()
                var i= Intent(this,MainActivity::class.java)
                startActivity(i)
            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer2, fragment).addToBackStack(null).commit()
    }
   /*
    }*/
}
