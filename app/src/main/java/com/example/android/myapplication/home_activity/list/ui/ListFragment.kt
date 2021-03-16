package com.example.android.myapplication.home_activity.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.data.models.TransactionDTO
import com.example.android.myapplication.R
import com.example.android.myapplication.commons.BaseFragment
import com.example.android.myapplication.commons.uicomponents.ErrorDialog
import com.example.android.myapplication.databinding.ListFragmentBinding
import com.example.android.myapplication.home_activity.list.vm.ListViewModel
import com.example.android.myapplication.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment: BaseFragment(), CellClickListener {

    private val presenter: ListViewModel by sharedViewModel()
    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()


    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TransactionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun loadObservers() {
        presenter.transactionsList.observe(viewLifecycleOwner, {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = TransactionAdapter(it, this)
            binding.recyclerView.adapter = adapter
        })

        presenter.showMessage.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        presenter.showError.observe(viewLifecycleOwner, {
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                        activity,
                        getString(R.string.alert),
                        it,
                        getString(R.string.close)
                ) {
                    errorDialog?.dismiss()
                }
            }
            errorDialog!!.setCancelable(false)
            errorDialog!!.show()
        })
        presenter.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onCellClickListener(transaction: TransactionDTO) {
        Toast.makeText(activity, "Cell: ${transaction.description}", Toast.LENGTH_SHORT).show()
        sharedTransactionVM.setTransaction(transaction)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

