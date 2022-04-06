package com.example.myapiget

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val serviceGenerator = ServiceBuilder.buildService(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val count = findViewById<TextView>(R.id.textViewcount)
        val text = findViewById<EditText>(R.id.editTextTextPersonName)
        val buscar = findViewById<Button>(R.id.buttonbuscarall)
        val afegir = findViewById<Button>(R.id.buttonafegir)
        val filtrar = findViewById<Button>(R.id.buttonfiltrar)
        val borrar = findViewById<Button>(R.id.buttonborrar)

        afegir.setOnClickListener {
            getMethod(0)
            count.text=""
            update(text.text.toString())
        }

        buscar.setOnClickListener {
            getMethod(0)
            getMethod()
        }

        filtrar.setOnClickListener {
            count.text=""
            getMethod(0)
            if (text.text.toString()!=""){
                try {
                    getMethod(text.text.toString().toInt())
                }catch (e : Exception){}
            }
        }

        borrar.setOnClickListener {
            getMethod(0)
            count.text=""
            try{
                deleteMethod(text.text.toString().toInt())
            }catch (e : Exception){}
        }
    }

    fun deleteMethod(text: Int) {
        val call = serviceGenerator.deleteid(text)
        call.clone().enqueue(object : retrofit2.Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    Log.d("Pretty Printed JSON :", "borrado")
                    Toast.makeText(applicationContext,"Borrado correcte!",Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("RETROFIT_ERROR", "test")
                }
            }override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("RETROFIT_ERROR", "test")
            }
        })
    }
    fun update(text: String) {
        val userInfo = ResponseData(null, text)
        val apiService = RestApiService()

        apiService.addUser(userInfo) {
            if (it?.id != null) {
                Toast.makeText(this, "registering new user", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error registering new user", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun getMethod() {
        val call = serviceGenerator.getapro()

        call.clone().enqueue(object : retrofit2.Callback<MutableList<ResponseData>> {
            override fun onResponse(call: Call<MutableList<ResponseData>>, response: Response<MutableList<ResponseData>>){
                if (response.isSuccessful){
                    val count = findViewById<TextView>(R.id.textViewcount)
                    count.text=response.body()?.size.toString()
                    count.textSize= 200F
                }
            }
            override fun onFailure(call: Call<MutableList<ResponseData>>, t: Throwable){
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }

    private fun getMethod(text: Int) {
        val recyclerview = findViewById<RecyclerView>(R.id.rvDogs)

        val call = serviceGenerator.findid(text)
        call.clone().enqueue(object : retrofit2.Callback<MutableList<ResponseData>> {
            override fun onResponse(call: Call<MutableList<ResponseData>>, response: Response<MutableList<ResponseData>>){
                if (response.isSuccessful){
                    recyclerview.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = Adapter(response.body()!!)
                        Toast.makeText(applicationContext,"GET correcte!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<MutableList<ResponseData>>, t: Throwable){
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }
}