package com.a706012110039.peternakan

import `interface`.cardlistener
import adapter.rvadapter
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import ayam
import com.a706012110039.peternakan.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import database.globalvar
import kambing
import model.biji
import model.rumput
import sapi

class MainActivity : AppCompatActivity(),cardlistener {
    private lateinit var viewbind: ActivityMainBinding
    private val adapter = rvadapter(globalvar.listDatahewan, this)
    private val adapter2 = rvadapter(globalvar.tempDatahewan, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        viewbind = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewbind.root)
        listener()
        setuprv()
    }

    override fun onResume() {
        super.onResume()
        if(globalvar.session == "ayam"){
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is ayam){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
                if(globalvar.tempDatahewan.size>0){
                    viewbind.textView2.alpha = 0f
                }else{
                    viewbind.textView2.alpha = 1f
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }else if(globalvar.session == "sapi"){
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is sapi){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
                if(globalvar.tempDatahewan.size>0){
                    viewbind.textView2.alpha = 0f
                }else{
                    viewbind.textView2.alpha = 1f
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }else if(globalvar.session == "kambing"){
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is kambing){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
                if(globalvar.tempDatahewan.size>0){
                    viewbind.textView2.alpha = 0f
                }else{
                    viewbind.textView2.alpha = 1f
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }else{
            adapter.notifyDataSetChanged()
        }
        if(globalvar.listDatahewan.size>0){
            viewbind.textView2.alpha = 0f
        }else{
            viewbind.textView2.alpha = 1f
        }
    }

    fun listener(){
        viewbind.addbutton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            startActivity(intent)
        }

        viewbind.buttonayam.setOnClickListener {
            globalvar.session = "ayam"
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is ayam){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        viewbind.buttonkambing.setOnClickListener {
            globalvar.session = "kambing"
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is kambing){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        viewbind.buttonsapi.setOnClickListener {
            globalvar.session = "sapi"
            globalvar.tempDatahewan.clear()
            for(i in 0..globalvar.listDatahewan.size-1){
                if(globalvar.listDatahewan[i] is sapi){
                    globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                }
            }
            viewbind.listdata.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        viewbind.buttonall.setOnClickListener {
            globalvar.session = "asdfas"
            viewbind.listdata.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewbind.buttonclear.setOnClickListener {
            globalvar.session = "asdfas"
            viewbind.listdata.adapter = adapter
            globalvar.listDatahewan.clear()
            viewbind.textView2.alpha = 1f
            adapter.notifyDataSetChanged()
        }
    }

    fun setuprv(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewbind.listdata.layoutManager = layoutManager
        viewbind.listdata.adapter = adapter
    }

    override fun onCardClick(tombol: String, position: Int) {
        if(tombol == "delete"){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete animal")
            builder.setMessage("Are you sure you want to delete this animal?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbar = Snackbar.make(viewbind.listdata, "Animal Deleted", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Dismiss") { snackbar.dismiss() }
                snackbar.setActionTextColor(Color.WHITE)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()

                //remove
                globalvar.listDatahewan.removeAt(position)
                if(globalvar.session == "ayam"){
                    globalvar.tempDatahewan.clear()
                    for(i in 0..globalvar.listDatahewan.size-1){
                        if(globalvar.listDatahewan[i] is ayam){
                            globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                        }
                        if(globalvar.tempDatahewan.size>0){
                            viewbind.textView2.alpha = 0f
                        }else{
                            viewbind.textView2.alpha = 1f
                        }
                    }
                    viewbind.listdata.adapter = adapter2
                    adapter.notifyDataSetChanged()
                }else if(globalvar.session == "sapi"){
                    globalvar.tempDatahewan.clear()
                    for(i in 0..globalvar.listDatahewan.size-1){
                        if(globalvar.listDatahewan[i] is sapi){
                            globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                        }
                        if(globalvar.tempDatahewan.size>0){
                            viewbind.textView2.alpha = 0f
                        }else{
                            viewbind.textView2.alpha = 1f
                        }
                    }
                    viewbind.listdata.adapter = adapter2
                    adapter.notifyDataSetChanged()
                }else if(globalvar.session == "kambing"){
                    globalvar.tempDatahewan.clear()
                    for(i in 0..globalvar.listDatahewan.size-1){
                        if(globalvar.listDatahewan[i] is kambing){
                            globalvar.tempDatahewan.add(globalvar.listDatahewan[i])
                        }
                        if(globalvar.tempDatahewan.size>0){
                            viewbind.textView2.alpha = 0f
                        }else{
                            viewbind.textView2.alpha = 1f
                        }
                    }
                    viewbind.listdata.adapter = adapter2
                    adapter.notifyDataSetChanged()
                }else{
                    adapter.notifyDataSetChanged()
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }else if(tombol == "edit"){
            val intent = Intent(this, AddActivity::class.java).putExtra("position",position)
            startActivity(intent)
        }else if(tombol == "feed"){
            if(globalvar.listDatahewan[position] is ayam){
                Toast.makeText(this, globalvar.listDatahewan[position].makanan(biji()), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, globalvar.listDatahewan[position].makanan<Int>(rumput()), Toast.LENGTH_SHORT).show()
            }
        }else{
            //interact
            Toast.makeText(this,  globalvar.listDatahewan[position].interaksi(), Toast.LENGTH_SHORT).show()
        }

    }
}