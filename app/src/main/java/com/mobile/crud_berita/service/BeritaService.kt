package com.mobile.crud_berita.service

import com.mobile.crud_berita.model.ResponseBerita
import retrofit2.Call
import retrofit2.http.GET

interface BeritaService {
    @GET("getBerita.php")
    fun getAllBerita(): Call<ResponseBerita>
}