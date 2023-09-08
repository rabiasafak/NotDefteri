package com.example.deneme101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class NoteGirisActivity : AppCompatActivity() {
    private lateinit var vt:VeriTabaniH
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_giris)
        vt= VeriTabaniH(this)




        var btn_back: ImageButton =findViewById(R.id.img_back)
        btn_back.setOnClickListener(){
            startActivity(Intent(this@NoteGirisActivity,MainActivity::class.java))
            finish()

        }


        var btn_save:ImageButton=findViewById(R.id.img_add)
        btn_save.setOnClickListener(){
            val intent2 = Intent(this, MainActivity::class.java)
            val title:EditText=findViewById(R.id.editTextTitle)
            val desc:EditText=findViewById(R.id.editTextDesc)
            val baslik=title.text.toString().trim()
            val not_icerik= desc.text.toString().trim()
            if(TextUtils.isEmpty(baslik)){
                Toast.makeText(getApplicationContext(),"Alan Boş",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not_icerik)){
                Toast.makeText(getApplicationContext(),"Alan Boş",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            Notlardao().notEkle(vt,baslik,not_icerik)

            startActivity(intent2)
            finish()
        }



    }
}