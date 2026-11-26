package com.equipoocho.dogapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.equipoocho.dogapp.DogApp
import com.equipoocho.dogapp.databinding.FragmentEditAppointmentBinding
import com.equipoocho.dogapp.ui.common.ViewModelFactory

class EditAppointmentFragment : Fragment() {

    private val args: EditAppointmentFragmentArgs by navArgs()

    private val viewModel: EditAppointmentViewModel by viewModels {
        val app = requireActivity().application as DogApp
        ViewModelFactory {
            EditAppointmentViewModel(
                appointmentRepository = app.appointmentRepository,
                breedRepository = app.breedRepository,
                appointmentId = args.appointmentId
            )
        }
    }

    private var _binding: FragmentEditAppointmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAppointmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnEditAppointment.setOnClickListener {
            viewModel.onEditClicked()
        }

        viewModel.navigateToHomeEvent.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(
                    EditAppointmentFragmentDirections.actionEditToHome()
                )
                viewModel.consumeNavigateToHomeEvent()
            }
        }

        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            binding.actvBreed.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    breeds
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
