package com.example.userinformation.electronics.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentAddUserBinding
import com.example.userinformation.databinding.FragmentListOfUserkBinding
import com.example.userinformation.electronics.database.AppDatabase
import com.example.userinformation.electronics.model.User
import com.example.userinformation.electronics.repository.UserRepository
import kotlinx.coroutines.launch

//import com.example.userinformation.electronics.viewmodel.UserViewModel

class AddUserFragment : Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var userRepository: UserRepository

    //    private lateinit var modelView :UserViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

//        modelView = ViewModelProvider(this)[UserViewModel::class.java]

//        val userDao = AppDatabase.getInstance(requireContext()).userDao()
//        userRepository = UserRepository(userDao)

        binding.btnAdd.setOnClickListener {
//
            viewLifecycleOwner.lifecycleScope.launch {
                insertIntoDatabase()
            }
        }
        return view
    }

    private suspend fun insertIntoDatabase() {

        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()

        if (checkInput(firstName, lastName)) {
            val user = User(0, firstName, lastName)
            addUserToDatabase(user)
            Log.d(requireContext().toString(), "add user")
            Toast.makeText(requireContext(), "Successfully Add", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Unsuccessfully", Toast.LENGTH_SHORT).show()
            Log.d(requireContext().toString(), "user not added")


        }
    }

    private suspend fun addUserToDatabase(user: User) {

            userRepository.addUser(user)

    }


    private fun checkInput(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }
}