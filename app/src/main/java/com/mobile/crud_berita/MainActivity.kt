package com.mobile.crud_berita

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mobile.crud_berita.adapter.BeritaAdapter
import com.mobile.crud_berita.model.ModelBerita
import com.mobile.crud_berita.model.ResponseBerita
import com.mobile.crud_berita.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ResponseBerita>
    private lateinit var beritaAdapter: BeritaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerView  = findViewById(R.id.rv_berita)

        beritaAdapter = BeritaAdapter { modelBerita: ModelBerita -> beritaOnClick(modelBerita) }

        recyclerView.adapter = beritaAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager.VERTICAL,
            false
        )
        //swipe refresh
        swipeRefreshLayout.setOnClickListener{
            getData()
        }
        getData()
    }

    private fun beritaOnClick(modelProduk: ModelBerita) {
        val intent = Intent(this, DetailPage::class.java)
        intent.putExtra("gambar_berita",modelProduk.gambar_berita)
        intent.putExtra("judul",modelProduk.judul)
        intent.putExtra("isi_berita",modelProduk.isi_berita)
        intent.putExtra("tgl_berita",modelProduk.tgl_berita)



        startActivity(intent)
    }

    private fun getData() {
        //proses untuk dapatkan respons
        swipeRefreshLayout.isRefreshing = true
        call = ApiClient.beritaService.getAllBerita()
        call.enqueue(object : Callback<ResponseBerita> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponseBerita>,
                response: Response<ResponseBerita>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful){
                    beritaAdapter.submitList(response.body()?.data)
                    beritaAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext,
                    t.localizedMessage, Toast.LENGTH_SHORT).show()

            }

        })
    }

}