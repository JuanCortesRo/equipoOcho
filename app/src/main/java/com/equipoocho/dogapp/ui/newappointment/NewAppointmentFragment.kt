package com.equipoocho.dogapp.ui.newappointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.equipoocho.dogapp.R
import com.equipoocho.dogapp.data.local.AppDatabase
import com.equipoocho.dogapp.data.remote.DogApiService
import com.equipoocho.dogapp.data.remote.RetrofitClient
import com.equipoocho.dogapp.data.repository.AppointmentRepository
import com.equipoocho.dogapp.data.repository.BreedRepository
import com.equipoocho.dogapp.databinding.FragmentNewAppointmentBinding
import com.equipoocho.dogapp.ui.common.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewAppointmentFragment : Fragment() {

    private var _binding: FragmentNewAppointmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel: NewAppointmentViewModel by viewModels {
        val appContext = requireContext().applicationContext
        val database = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
        val appointmentRepo = AppointmentRepository(database.appointmentDao())

        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val breedRepo = BreedRepository(retrofit.create(DogApiService::class.java))

        ViewModelFactory {
            NewAppointmentViewModel(
                appointmentRepository = appointmentRepo,
                breedRepository = breedRepo
            )
        }
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
            val selectedSymptom = binding.spinnerSymptoms.selectedItem?.toString().orEmpty()
            viewModel.onSaveClicked(selectedSymptom)
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

        viewModel.navigateToHomeEvent.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(
                    NewAppointmentFragmentDirections.actionNewAppointmentToHome()
                )
                viewModel.consumeNavigateToHomeEvent()
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
