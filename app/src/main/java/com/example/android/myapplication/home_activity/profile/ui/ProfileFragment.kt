package com.example.android.myapplication.home_activity.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentProfileBinding
import com.example.android.myapplication.home_activity.profile.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.profile.observe(viewLifecycleOwner){profileData->
            binding.etName.setText(profileData.name)
            binding.etLastName.setText(profileData.lastName)
        }
        binding.etName.addTextChangedListener { name->
            profileViewModel.saveName(name.toString())
        }
        binding.etLastName.addTextChangedListener { lastName->
            profileViewModel.saveLastName(lastName.toString())
        }
    }


}