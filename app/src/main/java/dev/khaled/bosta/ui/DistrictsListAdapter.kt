package dev.khaled.bosta.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.khaled.bosta.R
import dev.khaled.bosta.data.model.District
import dev.khaled.bosta.databinding.DropdownItemBinding

class DistrictListAdapter(
    private var districts: ArrayList<District>
) :
    RecyclerView.Adapter<DistrictListAdapter.DistrictViewHolder>() {


    fun updateDistricts(newDistricts: List<District>) {
        districts.clear()
        districts.addAll(newDistricts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DropdownItemBinding.inflate(inflater, parent, false)
        return DistrictViewHolder(binding)
    }


    override fun getItemCount() = districts.size

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        holder.bind(districts[position])
    }

    class DistrictViewHolder(private val binding: DropdownItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(district: District) {
            binding.textView2.text = binding.root.context.getString(R.string.district, district.zoneName, district.districtName)
        }
    }
}