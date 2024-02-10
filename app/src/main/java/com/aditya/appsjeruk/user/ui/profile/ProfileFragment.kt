package com.aditya.appsjeruk.user.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.FragmentProfileBinding
import com.aditya.appsjeruk.user.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonBackClicked()
        getDataUser()

        if (activity != null) {
//            checkUserLogin()
            binding?.apply {
                btnLogout.setOnClickListener {
                    showAlertLogout()
                }
            }
        }
    }

    private fun getDataUser() {
        viewModel.getUser().observe(viewLifecycleOwner) { data ->
            binding?.tvNameProfil?.text = data.name
            binding?.tvJabatanProfil?.text = data.roles
//            binding?.etEmail?.setText(data.email)
//            binding?.etNotlp?.setText(data.no_telp)
//            binding?.etAlamatProfil?.setText(data.alamat)

        }
    }

    private fun showAlertLogout() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.logoutMessage))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                viewModel.deleteUser()
                Intent(requireContext(), LoginActivity::class.java).also { i ->
                    i.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }

            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }


    private fun setupButtonBackClicked() {

        binding?.imgBackProfil?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_home)
        }
    }


}