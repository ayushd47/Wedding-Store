package com.safall.adoptdont_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safall.adoptdont_shop.adapter.SinglePetAdapter
import com.safall.adoptdont_shop.repository.SinglePetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewSinglePetActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_view_single_shop)

        recyclerView = findViewById(R.id.singlepetRecycler)
        loadSinglePet()


    }

    private fun loadSinglePet() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                var id =intent.getStringExtra("pet")
//                Log.d("IDchaiyeko", "onBindViewHolder: " + id)
                val repository = SinglePetRepository()
//                Log.d("repotest", "onBindViewHolder: " + repository)
                val response = repository.getSinglePet(id!!)
                Log.d("Datataneko1", "onBindViewHolder: " + repository.getSinglePet(id!!))
                if(response.success == true){
                    val lstPet = response.data
//                    Log.d("Datataneko", "onBindViewHolder: " + response.data)
                    withContext(Dispatchers.Main){
                        recyclerView.adapter = SinglePetAdapter(
                                lstPet!!,
                                this@ViewSinglePetActivity
                        )
                        recyclerView.layoutManager= LinearLayoutManager(
                                this@ViewSinglePetActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                        )
                    }
                }
            }
            catch(ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@ViewSinglePetActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
//                    Log.d("DataAAyena", "Pet ko aayena")
                }
            }
        }}
}