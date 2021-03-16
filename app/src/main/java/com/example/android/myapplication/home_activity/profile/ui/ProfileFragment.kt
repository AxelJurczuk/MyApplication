package com.example.android.myapplication.home_activity.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentProfileBinding
import com.example.android.myapplication.home_activity.profile.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    private val presenter: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveName.setOnClickListener {
            presenter.saveName(binding.etName.toString())
        }

        presenter.readName()

        binding.btnReadName.setOnClickListener {
            presenter.savedName.observe(viewLifecycleOwner,{
                 it.let{
                     binding.tvSavedName.text = it
                 }
            })
        }
    }

}