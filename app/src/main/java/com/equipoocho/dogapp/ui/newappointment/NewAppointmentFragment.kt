package com.equipoocho.dogapp.ui.newappointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.equipoocho.dogapp.R
import com.equipoocho.dogapp.databinding.FragmentNewAppointmentBinding
import com.equipoocho.dogapp.ui.common.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NewAppointmentFragment : Fragment() {

    private var _binding: FragmentNewAppointmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewAppointmentViewModel by viewModels {
        ViewModelFactory { NewAppointmentViewModel() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAppointmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSaveAppointment.setOnClickListener {
            viewModel.onSaveClicked()
        }

        viewModel.selectSymptomEvent.observe(viewLifecycleOwner) { show ->
            if (show == true) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.select_symptom),
                    Snackbar.LENGTH_SHORT
                )
                    .setAnchorView(binding.btnSaveAppointment)
                    .show()
                viewModel.consumeSelectSymptomEvent()
            }
        }
        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                breeds
            )
            binding.actvBreed.setAdapter(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
