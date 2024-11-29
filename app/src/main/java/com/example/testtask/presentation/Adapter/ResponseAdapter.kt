package com.example.testtask.presentation.Adapter

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.databinding.ResponseItemBinding
import com.example.testtask.domain.Entity.Response
import org.koin.dsl.koinApplication

class ResponseAdapter(private var responseArray: ArrayList<Response>) : RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ResponseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(response: Response){

            binding.transportTypeName.text = response.tripTransportName
            binding.threadNumber.text = response.tripNumber
            binding.threadTitle.text = response.tripTitle
            binding.arrivalDate.text = response.arrivalDate
            binding.arrivalTime.text = response.arrivalTime
            binding.arrivalTitle.text = response.arrivalTitle
            binding.arrivalTerminal.text = response.arrivalTerminal
            binding.tripTime.text = response.tripTime
            binding.departureDate.text = response.departureDate
            binding.departureTime.text = response.departureTime
            binding.departureTitle.text = response.departureTitle
            binding.departureTerminal.text = response.departureTerminal
            when (response.transportType) {
                "plane" -> binding.transportType.setImageResource(R.drawable.plane)
                "train" -> {
                    binding.transportType.setImageResource(R.drawable.train)
                    binding.departureTerminal.visibility = View.GONE
                    binding.arrivalTerminal.visibility = View.GONE
                    binding.emptyView.visibility = View.GONE
                }
                "suburban" -> binding.transportType.setImageResource(R.drawable.suburban)
                "bus" -> {
                    binding.transportType.setImageResource(R.drawable.bus)
                    binding.threadNumber.visibility = View.GONE
                    binding.departureTerminal.visibility = View.GONE
                    binding.arrivalTerminal.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ResponseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = responseArray[position]
        holder.bind(response)
    }

    override fun getItemCount() = responseArray.size

    fun setArray(array: ArrayList<Response>){
        this.responseArray = array
    }
}