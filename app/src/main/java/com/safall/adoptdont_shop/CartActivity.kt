package com.safall.adoptdont_shop

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safall.adoptdont_shop.adapter.CartAdapter
import com.safall.adoptdont_shop.api.ServiceBuilder.token
import com.safall.adoptdont_shop.entity.Cart
import com.safall.adoptdont_shop.entity.Product
import com.safall.adoptdont_shop.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartActivity : AppCompatActivity() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartView: RecyclerView
    private lateinit var btnCheckout: Button
    private lateinit var tvTotalPrice: TextView
    val productList=ArrayList<Product>()
    var cartList= mutableListOf<Cart>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_cart)

        cartView = findViewById(R.id.cartRecycler)
        btnCheckout = findViewById(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            startActivity(Intent(this@CartActivity, CheckoutActivity::class.java))
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = CartRepository()
//                Log.d("repotest", "onBindViewHolder: " + repository)
                val token = getSharedPref()
//                Log.d("tokentest", "onBindViewHolder: " + token)
                val response = repository.getCart("Bearer "+token!!)
                Log.d("resptest", "onBindViewHolder: " + response)
                cartList=response.data!!
                Log.d("CARTLIST", response.success.toString())
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        var adapter = CartAdapter(cartList, this@CartActivity)

                        cartView.layoutManager= LinearLayoutManager(this@CartActivity)
                        cartView.adapter=adapter
                    }
                    }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CartActivity, ex.toString(), Toast.LENGTH_SHORT).show()

                }
            }

    }

}
    private fun getSharedPref():String {
        val sharedPref = getSharedPreferences("LoginPref", AppCompatActivity.MODE_PRIVATE)
        return sharedPref?.getString("token", "")!!
    }

}
