package com.safall.adoptdont_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safall.adoptdont_shop.adapter.CartAdapter
import com.safall.adoptdont_shop.adapter.FavAdapter
import com.safall.adoptdont_shop.entity.Cart
import com.safall.adoptdont_shop.entity.Favorite
import com.safall.adoptdont_shop.repository.CartRepository
import com.safall.adoptdont_shop.repository.FavRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favView: RecyclerView
    var favList= mutableListOf<Favorite>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_favorite)

        favView = findViewById(R.id.favRecycler)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = FavRepository()
//                Log.d("repotest", "onBindViewHolder: " + repository)
                val token = getSharedPref()
//                Log.d("tokentest", "onBindViewHolder: " + token)
                val response = repository.getFav("Bearer "+token!!)
//                Log.d("resptest", "onBindViewHolder: " + response)
                favList=response.data!!
//                Log.d("CARTLIST", response.success.toString())
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        var adapter = FavAdapter(favList, this@FavoriteActivity)

                        favView.layoutManager= LinearLayoutManager(this@FavoriteActivity)
                        favView.adapter=adapter
                    }
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FavoriteActivity, ex.toString(), Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
    private fun getSharedPref():String {
        val sharedPref = getSharedPreferences("LoginPref", AppCompatActivity.MODE_PRIVATE)
        return sharedPref?.getString("token", "")!!
    }
}