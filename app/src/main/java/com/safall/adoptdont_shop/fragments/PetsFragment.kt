package com.safall.adoptdont_shop.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safall.adoptdont_shop.FavoriteActivity
import com.safall.adoptdont_shop.R
import com.safall.adoptdont_shop.adapter.PetAdapter
import com.safall.adoptdont_shop.db.PetDB
import com.safall.adoptdont_shop.repository.PetRepository


class PetsFragment : Fragment() {

    private lateinit var petViewModel: PetViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PetAdapter
    private lateinit var btnFav: Button
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        val petDao = PetDB.getInstance(requireContext()).getPetDAO()
        val repository = PetRepository(petDao)
        val viewModelFactory = PetViewModelFactory(repository)
        petViewModel = ViewModelProvider(this,viewModelFactory).get(PetViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shops, container, false)
        btnFav = view.findViewById(R.id.btnFav)
        recyclerView = view.findViewById(R.id.petRecycler)

        petViewModel.getAllPets()

        petViewModel.text.observe(viewLifecycleOwner, Observer { lstPets ->
            adapter.setList(lstPets)
        })

        adapter = PetAdapter(requireContext())
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter

        btnFav.setOnClickListener{
            startActivity(
                    Intent(
                            context,
                            FavoriteActivity::class.java
                    )
            )
        }


//        loadPets()
        return view



    }
//    private  val TAG = "PetsFragment"



//    private fun loadPets() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val petRepository = PetRepository()
//                val response = petRepository.getAllPets()
//                if (response.success == true) {
//                    // Put all the student details in lstStudents
//                    val lstPets = response.data
//                    withContext(Dispatchers.Main) {
//                        val adapter =
//                                PetAdapter(lstPets!!, requireContext())
//                        recyclerView.layoutManager = LinearLayoutManager(context)
//                        recyclerView.adapter = adapter
//                    }
//                }
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                            context,
//                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }


}