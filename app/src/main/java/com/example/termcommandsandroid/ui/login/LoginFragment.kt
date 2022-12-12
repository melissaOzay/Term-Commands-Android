package com.example.termcommandsandroid.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.termcommandsandroid.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var btnLogin: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        btnLogin = view.findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment2_to_homeFragment)
        }


        return view
    }


}