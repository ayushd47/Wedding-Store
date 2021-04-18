package com.safall.adoptdont_shop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.safall.adoptdont_shop.R
import com.safall.adoptdont_shop.entity.Pet
import com.safall.adoptdont_shop.entity.Product
import com.safall.adoptdont_shop.repository.CartRepository
import com.safall.adoptdont_shop.repository.FavRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SinglePetAdapter (
        private val lstPets: List<Pet>,
        private val context: Context,
): RecyclerView.Adapter<SinglePetAdapter.SinglePetViewHolder>() {
    class SinglePetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPetName: TextView = view.findViewById(R.id.tvPetName)
        val tvPetSpecies: TextView = view.findViewById(R.id.tvPetSpecies)
        val tvPetGender: TextView = view.findViewById(R.id.tvPetGender)
        val tvPetBd: TextView = view.findViewById(R.id.tvPetBd)
        val tvPetFee: TextView = view.findViewById(R.id.tvPetFee)
        val tvPetLocation: TextView = view.findViewById(R.id.tvPetLocation)
        val tvPetContactno: TextView = view.findViewById(R.id.tvPetContactno)
        val tvPetInfo: TextView = view.findViewById(R.id.tvPetInfo)
        val btnAddToFav: Button = view.findViewById(R.id.btnAddToFav)


        val imgPet: ImageView

        init {
            imgPet = view.findViewById(R.id.imgPet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePetViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_single_pet_layout, parent, false)
        return SinglePetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinglePetViewHolder, position: Int) {
        val pet = lstPets[position]
        holder.tvPetName.text = pet.pet_name
        val imguri = "http://10.0.2.2:90/public/" + pet.pet_img!!
//        Log.d("IMGURI", "onBindViewHolder: " + imguri)
//        Log.d("TAG", "onBindViewHolder: " + pet.pet_img)
        Glide.with(context).load(imguri).into(holder.imgPet)
        holder.tvPetSpecies.text = "Species: " + pet.pet_species
        holder.tvPetGender.text = "Gender: " + pet.pet_gender
        holder.tvPetBd.text = "Birthday: " +  pet.pet_bd
        holder.tvPetLocation.text = "Location: " + pet.pet_location
        holder.tvPetFee.text = "Fee: " + pet.pet_fee
        holder.tvPetContactno.text = "Contact No.: " + pet.pet_contactno
        holder.tvPetInfo.text = "Info: " + pet.pet_info

        holder.btnAddToFav.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
//                    val cart = Cart(productData.productImage, productData.productName, productData.productPrice, productData.quantity)

//                    CartDB.getInstance(context).getCartDAO().insertCart(cart)
                    val repository = FavRepository()
                    val token = getSharedPref()
                    val response = repository.addTofav(pet._id!!,"Bearer "+ token!!)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            //addtocartNotification()
                            Toast.makeText(context, "Pet Added To Favorites !", Toast.LENGTH_SHORT).show()
                        }
                    }

                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }



    }

    override fun getItemCount(): Int {
        return lstPets.size
    }

    private fun getSharedPref():String {
        val sharedPref = context?.getSharedPreferences("LoginPref", AppCompatActivity.MODE_PRIVATE)
        return sharedPref?.getString("token", "")!!
    }
}