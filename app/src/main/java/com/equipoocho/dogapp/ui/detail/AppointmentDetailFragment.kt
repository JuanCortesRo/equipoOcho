package com.equipoocho.dogapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.equipoocho.dogapp.DogApp
import com.equipoocho.dogapp.databinding.FragmentAppointmentDetailBinding
import com.equipoocho.dogapp.ui.common.ViewModelFactory

class AppointmentDetailFragment : Fragment() {

    private val args: AppointmentDetailFragmentArgs by navArgs()

    private val viewModel: AppointmentDetailViewModel by viewModels {
        ViewModelFactory {
            AppointmentDetailViewModel(
                appointmentRepository = (requireActivity().application as DogApp).appointmentRepository,
                appointmentId = args.appointmentId
            )
        }
    }

    private var _binding: FragmentAppointmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAppointmentDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.fabDelete.setOnClickListener {
            viewModel.deleteAppointment()
        }

        binding.fabEdit.setOnClickListener {
            findNavController().navigate(
                AppointmentDetailFragmentDirections.actionDetailToEdit(args.appointmentId)
            )
        }

        viewModel.navigateHomeEvent.observe(viewLifecycleOwner) { navigate ->
            if (navigate == true) {
                findNavController().navigate(
                    AppointmentDetailFragmentDirections.actionDetailToHome()
                )
                viewModel.consumeNavigateHomeEvent()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
