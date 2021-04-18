package com.safall.adoptdont_shop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.safall.adoptdont_shop.R
import com.safall.adoptdont_shop.ViewSinglePetActivity
import com.safall.adoptdont_shop.entity.Pet


class PetAdapter (

        val context: Context,

        ): RecyclerView.Adapter<PetAdapter.PetViewHolder>(){
        var lstPets= emptyList<Pet>()

        fun setList(list: List<Pet>){
                lstPets = list
                notifyDataSetChanged()
        }
        class PetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val tvPetName: TextView = view.findViewById(R.id.tvShopName)

                val imgPet: ImageView
                init{
                        imgPet = view.findViewById(R.id.imgPet)
                }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.custom_shop_layout, parent, false)
                return PetViewHolder(view)
        }

        override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
                val pet = lstPets[position]
//                Log.d("TAG", "onBindViewHolder: " + pet.pet_gender)
                holder.tvPetName.text = pet.pet_name
                holder.tvPetName.setOnClickListener{
                        val id = pet._id
                        val intent = Intent (context, ViewSinglePetActivity::class.java)
                        context.startActivity(intent.putExtra("pet",id))
                }
//                Log.d("TAG", "onBindViewHolder: " + pet.pet_img)
                val imguri = "http://10.0.2.2:90/public/" + pet.pet_img
//                Log.d("IMGURI", "onBindViewHolder: " + imguri)
                Glide.with(context).load(imguri).into(holder.imgPet)
                holder.imgPet.setOnClickListener{
                        val id = pet._id
                        val intent = Intent (context, ViewSinglePetActivity::class.java)
                        context.startActivity(intent.putExtra("pet",id))
                }

        }
        override fun getItemCount(): Int {
                return lstPets.size
        }
}