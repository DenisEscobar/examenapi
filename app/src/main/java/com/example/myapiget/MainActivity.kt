package com.example.myapiget

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
//import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.retrofitkotlinexample.APIService
import com.cursokotlin.retrofitkotlinexample.DogsAdapter
import com.cursokotlin.retrofitkotlinexample.DogsResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()/*, androidx.appcompat.widget.SearchView.OnQueryTextListener */ {

    lateinit var imagesPuppies: List<String>
    lateinit var dogsAdapter: DogsAdapter

    lateinit var searchBreed: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //addDummyUser()
        getMethod()
    }

    fun addDummyUser() {
        val apiService = RestApiService()
        val userInfo = DogsResponse(3, "Alex")

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
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .build()
        val service = retrofit.create(APIService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getapi()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
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
    }
/*
        searchBreed = findViewById(R.id.searchBreed)
        searchBreed.setOnQueryTextListener(this)

        searchByName("Akita")
    }

    private fun initCharacter(puppies: DogsResponse) {
        if(puppies.status == "success"){
            imagesPuppies = puppies.images
            alert(""+imagesPuppies)
            val rvDog = findViewById<ImageView>(R.id.imageView)
            Picasso.get().load(""+imagesPuppies).into(rvDog)
        }

        //Picasso.with(this).load(imagesPuppies).into(rvDogs)

//        val rvDogs = findViewById<RecyclerView>(R.id.rvDogs)
//        dogsAdapter = DogsAdapter(imagesPuppies)
//        rvDogs.setHasFixedSize(true)
//        rvDogs.layoutManager = LinearLayoutManager(this)
//        rvDogs.adapter = dogsAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        val api = getRetrofit().create(APIService::class.java)
        api.getCharacterByName("$query/images").enqueue(object : Callback<DogsResponse> {
            override fun onResponse(call: Call<DogsResponse>, response: Response<DogsResponse>) {
                val heroList: List<DogsResponse> = response.body() as List<DogsResponse>
                val heroes = arrayOfNulls<String>(heroList.size)
                for (i in heroList.indices) {
                    heroes[i] = heroList[i].images.toString()
                }
                val rvDog = findViewById<ImageView>(R.id.imageView)
                Picasso.get().load(heroes[0]).into(rvDog)

            }

            override fun onFailure(call: Call<DogsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
//        doAsync {
//            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
//            val puppies = call.body() as DogsResponse
//            uiThread {
//                if(puppies.status == "success") {
//                    initCharacter(puppies)
//                }else{
//                    showErrorDialog()
//                }
//                hideKeyboard()
//            }
//        }
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query.toLowerCase())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val viewRoot = findViewById<ConstraintLayout>(R.id.viewRoot)
        imm.hideSoftInputFromWindow(viewRoot.windowToken, 0)
    }*/
}