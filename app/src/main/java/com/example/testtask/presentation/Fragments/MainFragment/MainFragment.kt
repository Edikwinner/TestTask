package com.example.testtask.presentation.Fragments.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.R
import com.example.testtask.databinding.FragmentMainBinding
import com.example.testtask.presentation.Adapter.ResponseAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private var transportType = ""
    private var date = SimpleDateFormat("yyyy-MM-dd").format(Date())
    private var adapter: ResponseAdapter = ResponseAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.responseRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.responseRecycler.adapter = adapter

        viewModel.allStationResponse.observe(viewLifecycleOwner){
            if(it == null){
                binding.search.text = getString(R.string.loading)
                binding.search.isClickable = false
            }
            else{
                binding.search.text = getString(R.string.find)
                binding.search.isClickable = true
            }
        }

        viewModel.response.observe(viewLifecycleOwner){
            if(it.size > 0) {
                adapter.setArray(it)
            }
            else{
                Snackbar.make(requireView(), getString(R.string.error_no_way), Snackbar.LENGTH_LONG).show()
                adapter.setArray(arrayListOf())
            }
            adapter.notifyDataSetChanged()
            binding.search.text = getString(R.string.find)
            binding.search.isClickable = true
        }

        binding.search.setOnClickListener {
            if(binding.stationFrom.text.isNotEmpty() and binding.stationTo.text.isNotEmpty()){
                binding.search.text = getString(R.string.loading)
                binding.search.isClickable = false
                viewModel.getSearch(
                    from = binding.stationFrom.text.toString(),
                    to = binding.stationTo.text.toString(),
                    transportType = transportType,
                    date = date
                )
            }
            else{
                Snackbar.make(it, getString(R.string.error_no_input_output), Snackbar.LENGTH_LONG).show()
            }
        }

        binding.change.setOnClickListener{
            val text = binding.stationFrom.text
            binding.stationFrom.text = binding.stationTo.text
            binding.stationTo.text = text
        }

        binding.transportTypeGroup.addOnButtonCheckedListener{ _, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    binding.any.id -> transportType = ""
                    binding.plane.id -> transportType = "plane"
                    binding.train.id -> transportType = "train"
                    binding.suburbun.id -> transportType = "suburban"
                    binding.bus.id -> transportType = "bus"
                }
            }
        }

        binding.dayGroup.addOnButtonCheckedListener{ _, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    binding.today.id -> date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                    binding.tomorrow.id -> date = SimpleDateFormat("yyyy-MM-dd").format(Date().time + 24 * 60 * 60 * 1000)
                    binding.calendar.id -> {
                        val datePicker = MaterialDatePicker
                            .Builder
                            .datePicker()
                            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                            .build()
                        datePicker.addOnCancelListener {
                            datePicker.dismiss()
                        }
                        datePicker.addOnPositiveButtonClickListener { time ->
                            date = SimpleDateFormat("yyyy-MM-dd").format(time)
                            binding.calendar.text = SimpleDateFormat("dd.MM.yy").format(time)
                        }
                        datePicker.show(parentFragmentManager, "TAG")
                    }
                }
            }

        }

        return binding.root
    }
}