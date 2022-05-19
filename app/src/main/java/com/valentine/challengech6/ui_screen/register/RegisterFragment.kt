package com.valentine.challengech6.ui_screen.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.valentine.challengech6.R
import com.valentine.challengech6.data.local.user.User
import com.valentine.challengech6.data.local.user.UserDatabase
import com.valentine.challengech6.databinding.FragmentRegisterBinding
import com.valentine.challengech6.mvvm.ViewModelFactory


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var DbUser: UserDatabase? = null
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory(view.context)
        registerViewModel =
            ViewModelProvider(requireActivity(), factory)[RegisterViewModel::class.java]

        DbUser = UserDatabase.getInstance(requireContext())
        binding.txtLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

            binding.icBack.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_splashscreenFragment)
            }
            binding.btnRegister.setOnClickListener {

                val username = binding.edtUsername.text.toString()
                val fullname = binding.edtFullName.text.toString()
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
                val objectUser = User(
                    username = username,
                    fullname = fullname,
                    email = email,
                    password = password
                )
                registerViewModel.userRegister(objectUser, binding.edtPassword.toString())
                Log.d("test", "test register")
            }

            registerViewModel.getRegister().observe(viewLifecycleOwner) {
                if (it == true) {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    registerViewModel.reset()
                }
            }
            if (binding.edtFullName.text.isNullOrEmpty() || binding.edtUsername.text.isNullOrBlank() || binding.edtEmail.text.isNullOrBlank() || binding.edtPassword.text.isNullOrBlank()) {
                Toast.makeText(
                    activity, "Please Check the field and type again", Toast.LENGTH_LONG
                ).show()
            } else {
                (binding.edtFullName.text!!.isNotEmpty() && binding.edtUsername.text!!.isNotEmpty() && binding.edtEmail.text!!.isNotEmpty() && binding.edtPassword.text!!.isNotEmpty())

                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }


}
