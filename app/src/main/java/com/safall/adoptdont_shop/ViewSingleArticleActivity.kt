package com.safall.adoptdont_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safall.adoptdont_shop.adapter.SingleArticleAdapter
import com.safall.adoptdont_shop.adapter.SingleProductAdapter
import com.safall.adoptdont_shop.repository.SingleArticleRepository
import com.safall.adoptdont_shop.repository.SingleProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewSingleArticleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_view_single_article)

        recyclerView = findViewById(R.id.singlearticleRecycler)
        loadSingleArticle()


    }

    private fun loadSingleArticle() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                var id =intent.getStringExtra("article")
//                Log.d("IDchaiyeko", "onBindViewHolder: " + id)
                val repository = SingleArticleRepository()
//                Log.d("repotest", "onBindViewHolder: " + repository)
                val response = repository.getSingleArticle(id!!)
//                Log.d("Datataneko1", "onBindViewHolder: " + repository.getSingleArticle(id!!))
                if(response.success == true){
                    val lstArticle = response.data
//                    Log.d("Datataneko", "onBindViewHolder: " + lstArticle)
                    withContext(Dispatchers.Main){
                        recyclerView.adapter = SingleArticleAdapter(
                            lstArticle!!,
                            this@ViewSingleArticleActivity
                        )
                        recyclerView.layoutManager= LinearLayoutManager(
                            this@ViewSingleArticleActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
                }
            }
            catch(ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ViewSingleArticleActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
//                    Log.d("DataAAyena", "jgjhvjh")
                }
            }
        }}
}