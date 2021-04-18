package com.safall.adoptdont_shop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.safall.adoptdont_shop.R
import com.safall.adoptdont_shop.entity.Favorite
import com.safall.adoptdont_shop.repository.FavRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavAdapter (
    var listFav: MutableList<Favorite>,
    val context: Context

): RecyclerView.Adapter<FavAdapter.FavViewHolder>(){
    class FavViewHolder(view: View): RecyclerView.ViewHolder(view){
        val petImg: ImageView
        val petName: TextView
        val delete: ImageButton

        init {
            petImg=view.findViewById(R.id.imgPet)
            petName=view.findViewById(R.id.tvShopName)
            delete = view.findViewById(R.id.ibDelete)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_fav_layout,parent,false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder,  position: Int) {
        val fav = listFav[position]
        val imguri = "http://10.0.2.2:90/public" + fav.pet_id!!.pet_img!!
        holder.petName.text = fav.pet_id.pet_name

        Glide.with(context).load(imguri).into(holder.petImg)

        holder.delete.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
//                try {
                    val repository = FavRepository()
                    val token = getSharedPref()
                    Log.d("idtesting1", "onBindViewHolder: " + fav._id)
                    val response = repository.deleteFav(fav._id!!,"Bearer "+ token)
                    Log.d("idtesting", "onBindViewHolder: " + response)
                    listFav=response.data!!
                    Log.d("deleting", response.success.toString())
                    notifyDataSetChanged()

                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Pet removed from favorites", Toast.LENGTH_SHORT).show()
                            notifyDataSetChanged()
                        }
                    }

                }
//                catch (ex: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//            }
        }}




    override fun getItemCount(): Int {
        return listFav.size
    }

    private fun getSharedPref():String {
        val sharedPref = context?.getSharedPreferences("LoginPref", AppCompatActivity.MODE_PRIVATE)
        return sharedPref?.getString("token", "")!!
    }
    companion object {
        private const val TAG = "FavAdapter"
    }
}