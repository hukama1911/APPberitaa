package com.mobile.crud_berita

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.mobile.crud_berita.model.ModelBerita

class DetailPage : AppCompatActivity() {
    private lateinit var txtJudul : TextView
    private lateinit var txtIsiBerita : TextView
    private lateinit var txtTanggalBerita : TextView
    private lateinit var imgBerita : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page)

        txtJudul=findViewById(R.id.txtJudulDetail)
        txtIsiBerita=findViewById(R.id.txtIsi)
        txtTanggalBerita=findViewById(R.id.txtTgl)

        imgBerita=findViewById(R.id.imgDetail)

        //getData
        val gambar = intent.getStringExtra("gambar_berita")
        val judul = intent.getStringExtra("judul")
        val isi = intent.getStringExtra("isi_berita")
        val tanggal = intent.getStringExtra("tgl_berita")


        Glide.with(this).load("http://192.168.243.75/beritaDb/gambar_berita/"
                + gambar).centerCrop()
            .into(imgBerita)
        txtJudul.text=judul
        txtTanggalBerita.text=tanggal
        txtIsiBerita.text=isi


    }
}