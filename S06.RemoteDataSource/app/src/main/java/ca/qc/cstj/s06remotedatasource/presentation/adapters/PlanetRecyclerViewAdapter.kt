package ca.qc.cstj.s06remotedatasource.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s06remotedatasource.databinding.ItemPlanetBinding
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import com.bumptech.glide.Glide

class PlanetRecyclerViewAdapter(var planets: List<Planet> = listOf()) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlanetRecyclerViewAdapter.ViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)
    }

    override fun getItemCount() = planets.size


    inner class ViewHolder(private val binding: ItemPlanetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet:Planet) {
            binding.txvPlanetName.text = planet.name

            Glide.with(binding.root.context)
                .load(planet.image)
                .into(binding.imgImagePlanet)
        }
    }


}