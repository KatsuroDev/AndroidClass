package ca.qc.cstj.s06remotedatasource.presentation.ui.planet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.core.notifyAllItemChanged
import ca.qc.cstj.s06remotedatasource.databinding.ActivityPlanetListBinding
import ca.qc.cstj.s06remotedatasource.presentation.adapters.PlanetRecyclerViewAdapter

class PlanetListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanetListBinding
    private val viewModel: PlanetListViewModel by viewModels()

    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter()

        binding.rcvPlanets.apply {
            layoutManager = GridLayoutManager(this@PlanetListActivity, 3)
            adapter = planetRecyclerViewAdapter
        }

        viewModel.planets.observe(this) {
            when(it)
            {
                is Resource.Success -> {
                    planetRecyclerViewAdapter.planets = it.data!!
                    planetRecyclerViewAdapter.notifyAllItemChanged()
                }
                is Resource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}