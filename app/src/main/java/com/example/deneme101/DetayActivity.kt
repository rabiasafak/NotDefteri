package com.example.deneme101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class DetayActivity : AppCompatActivity() {
    private lateinit var item: ExampleItem
    private lateinit var vt:VeriTabaniH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)
        vt=VeriTabaniH(this)

        item=intent.getSerializableExtra("nesne") as ExampleItem
        // her tıklandıgında nesne keyiyle bilgileri alıp bıraua tasırız
        var editTextNoteTitle: EditText =findViewById(R.id.editTextNoteTitle)
        var editTextNoteContent: EditText =findViewById(R.id.editTextNoteContent)
        editTextNoteTitle.setText(item.text1)
        editTextNoteContent.setText(item.text2)


        var btn_kaydet: Button =findViewById(R.id.saveButton)
        btn_kaydet.setOnClickListener(){
            val intent2 = Intent(this, MainActivity::class.java)
            val title:EditText=findViewById(R.id.editTextNoteTitle)
            val desc:EditText=findViewById(R.id.editTextNoteTitle)
            val baslik=title.text.toString().trim()
            val not_icerik= desc.text.toString().trim()
            if(TextUtils.isEmpty(baslik)){
                Toast.makeText(getApplicationContext(),"Alan Boş", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not_icerik)){
                Toast.makeText(getApplicationContext(),"Alan Boş", Toast.LENGTH_LONG).show();
                return@setOnClickListener}
            Notlardao().notguncelle(vt,item.not_id,baslik,not_icerik)
            startActivity(intent2)
            finish()
        }

        var btn_sil: Button =findViewById(R.id.deleteButton)
        btn_sil.setOnClickListener(){
            Notlardao().notSil(vt,item.not_id)
            startActivity(Intent(this@DetayActivity,MainActivity::class.java))
            finish()

        }
    }
}