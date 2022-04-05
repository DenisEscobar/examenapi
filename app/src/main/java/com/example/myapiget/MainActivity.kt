package com.example.myapiget

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.retrofitkotlinexample.APIService
import com.cursokotlin.retrofitkotlinexample.DogsAdapter
import com.cursokotlin.retrofitkotlinexample.DogsResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    lateinit var imagesPuppies: List<String>
    lateinit var dogsAdapter: DogsAdapter

    val serviceGenerator = ServiceBuilder.buildService(APIService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val text = findViewById<EditText>(R.id.editTextTextPersonName)
        val buscar = findViewById<Button>(R.id.buttonbuscarall)
        val afegir = findViewById<Button>(R.id.buttonafegir)
        val filtrar = findViewById<Button>(R.id.buttonfiltrar)
        val borrar = findViewById<Button>(R.id.buttonborrar)

        afegir.setOnClickListener { addDummyUser(text.text.toString()) }

        buscar.setOnClickListener { getMethod() }

        filtrar.setOnClickListener { getMethod(text.text.toString()) }

        borrar.setOnClickListener { deleteMethod(text.text.toString()) }
    }

    fun deleteMethod(text: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .build()
        val service = retrofit.create(APIService::class.java)
        val call = serviceGenerator.delete(text)
        call.clone().enqueue(object : retrofit2.Callback<MutableList<DogsResponse>> {
            override fun onResponse(call: retrofit2.Call<MutableList<DogsResponse>>, response: Response<MutableList<DogsResponse>>) {
                if (response.isSuccessful) {
                    Log.d("Pretty Printed JSON :", "borrado")
                } else {
                    Log.e("RETROFIT_ERROR", "test")
                }
            }override fun onFailure(call: Call<MutableList<DogsResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun addDummyUser(text: String) {
        val apiService = RestApiService()
        val userInfo = DogsResponse(null, text)

        apiService.addUser(userInfo) {
            if (it?.id != null) {
                Toast.makeText(this, "registering new user", Toast.LENGTH_LONG).show()
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else {
                Toast.makeText(this, "Error registering new user", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun getMethod() {
        val call = serviceGenerator.find()
        val recyclerview = findViewById<RecyclerView>(R.id.rvDogs)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081")
            .build()
        val service = retrofit.create(APIService::class.java)
        call.clone().enqueue(object : retrofit2.Callback<MutableList<DogsResponse>> {
            override fun onResponse(call: retrofit2.Call<MutableList<DogsResponse>>, response: Response<MutableList<DogsResponse>>){
                if (response.isSuccessful){
                    recyclerview.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = DogsAdapter(response.body()!!)
                        Toast.makeText(applicationContext,"GET correcte!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<MutableList<DogsResponse>>, t: Throwable){
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }

    private fun getMethod(text: String) {
        //val call = serviceGenerator.findnom()
        val recyclerview = findViewById<RecyclerView>(R.id.rvDogs)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081")
            .build()
        val service = retrofit.create(APIService::class.java)
        val call = serviceGenerator.findnom(text)
        call.clone().enqueue(object : retrofit2.Callback<MutableList<DogsResponse>> {
            override fun onResponse(call: retrofit2.Call<MutableList<DogsResponse>>, response: Response<MutableList<DogsResponse>>){
                if (response.isSuccessful){
                    recyclerview.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = DogsAdapter(response.body()!!)
                        Toast.makeText(applicationContext,"GET correcte!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<MutableList<DogsResponse>>, t: Throwable){
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }


/*
CoroutineScope(Dispatchers.IO).launch {
    val response = service.getapi()
    withContext(Dispatchers.Main) {
        if (response.isSuccessful) {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = DogsAdapter(response.body()!!)
                Toast.makeText(applicationContext,"GET correcte!",Toast.LENGTH_SHORT).show()
            }

            val gson = GsonBuilder().setPrettyPrinting().create()
          val prettyJson = gson.toJson(
                JsonParser.parseString(
                    response.body()
                        ?.string()
                )
            )
            Log.d("Pretty Printed JSON :", prettyJson)
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("json_results", prettyJson)
            this@MainActivity.startActivity(intent)
        } else {
            Log.e("RETROFIT_ERROR", response.code().toString())
        }
    }
}
}*/
}