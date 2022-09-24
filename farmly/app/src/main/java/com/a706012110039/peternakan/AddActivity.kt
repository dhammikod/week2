package com.a706012110039.peternakan

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import ayam
import com.a706012110039.peternakan.databinding.ActivityAddBinding
import database.globalvar
import kambing
import model.hewan
import sapi

class AddActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivityAddBinding
    private lateinit var hewan: hewan
    var position = -1
    var image: String = ""
    var jenis: String = "no"
    var id: Int = 0

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLEY
            viewbind.imageView2.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        getintent()
        listener()

    }
    //tes update

    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            var hewan = globalvar.listDatahewan[0]
            for(i in 0..globalvar.listDatahewan.size-1){
                if(position == globalvar.listDatahewan[i].id){
                    hewan = globalvar.listDatahewan[i]
                }
            }
            viewbind.toolbar2.title = "Edit animal"
            viewbind.Addanimal.text = "Edit Animal"
            viewbind.imageView2.setImageURI(Uri.parse(hewan.imageuri))
            viewbind.usiahewan.editText?.setText(hewan.usiahewan.toString())
            viewbind.namahewan.editText?.setText(hewan.namahewan)
            image = hewan.imageuri
            if(hewan is kambing){
                viewbind.Kambing.isChecked = true
            }else if(hewan is ayam){
                viewbind.ayam.isChecked = true
            }else{
                viewbind.sapi.isChecked = true
            }
            jenis = hewan.jenishewan
        }

    }

    fun listener(){
        viewbind.imageView2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewbind.sapi.setOnClickListener {
            jenis = "sapi"
        }

        viewbind.ayam.setOnClickListener {
            jenis = "ayam"
        }

        viewbind.Kambing.setOnClickListener {
            jenis = "kambing"
        }

        viewbind.toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }

        viewbind.Addanimal.setOnClickListener {
            var nama = viewbind.namahewan.editText?.text.toString().trim()
            var usia = 0



            hewan = hewan(nama,jenis,usia,image, id)
            hewan.jenishewan = jenis

            checker()
        }
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(hewan.namahewan!!.isEmpty()){
            viewbind.namahewan.error = "nama hewan cannot be empty"
            isCompleted = false
        }else{
            viewbind.namahewan.error = ""
        }

        if(hewan.jenishewan == "no"){
            Toast.makeText(this, "Tipe hewan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            isCompleted = false
        }

        hewan.imageuri = image.toUri().toString()
        if(hewan.imageuri!!.isEmpty()){
            isCompleted = false
            Toast.makeText(this, "Image cannot be empty", Toast.LENGTH_SHORT).show()
        }

        if(viewbind.usiahewan.editText?.text.toString().isEmpty() || viewbind.usiahewan.editText?.text.toString().toInt() < 0)
        {
            viewbind.usiahewan.error = "usia cannot be empty or 0"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            var speshewan: hewan
            if(position == -1)
            {
                if(globalvar.listDatahewan.size > 0){
                    id = globalvar.listDatahewan[globalvar.listDatahewan.size-1].id + 1
                }
                hewan.usiahewan = viewbind.usiahewan.editText?.text.toString().toInt()
                if(jenis == "sapi"){
                    speshewan = sapi(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri, id)
                    speshewan.jenishewan = "sapi"
                }else if(jenis == "ayam")
                {
                    speshewan = ayam(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri, id)
                    speshewan.jenishewan = "ayam"
                }else{
                    speshewan = kambing(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri, id)
                    speshewan.jenishewan = "kambing"
                }
                globalvar.listDatahewan.add(speshewan)

            }
            else
            {
                id = position
                hewan.usiahewan = viewbind.usiahewan.editText?.text.toString().toInt()
                if(jenis == "sapi"){
                    speshewan = sapi(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri, id)
                    speshewan.jenishewan = "sapi"
                }else if(jenis == "ayam")
                {
                    speshewan = ayam(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri,id)
                    speshewan.jenishewan = "ayam"
                }else{
                    speshewan = kambing(hewan.namahewan,hewan.jenishewan,hewan.usiahewan,hewan.imageuri,id)
                    speshewan.jenishewan = "kambing"
                }

                for(i in 0..globalvar.listDatahewan.size-1){
                    if(globalvar.listDatahewan[i].id == position){
                        globalvar.listDatahewan[i] = speshewan
                        break
                    }
                }

            }
            finish()
        }
    }
}