package dev.khaled.bosta.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.khaled.bosta.BostaApplication
import dev.khaled.bosta.R
import dev.khaled.bosta.data.model.City
import dev.khaled.bosta.data.model.District
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var citiesList: List<City> = emptyList()
    private var districtsList: ArrayList<District> = ArrayList()
    private lateinit var districtListAdapter: DistrictListAdapter
    private val tvCity: AutoCompleteTextView?
        get() = findViewById(R.id.tv_city)
    private val rvDistricts: RecyclerView?
        get() = findViewById(R.id.rv_districts)
    private val loadingView: ProgressBar?
        get() = findViewById(R.id.loading_view)
    private val swipeRefreshLayout: SwipeRefreshLayout?
        get() = findViewById(R.id.swipeRefreshLayout)

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        (application as BostaApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        districtListAdapter = DistrictListAdapter(arrayListOf())

        swipeRefreshLayout?.setOnRefreshListener {
            swipeRefreshLayout?.isRefreshing = false
            mainViewModel.fetchCities()
        }

        rvDistricts?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = districtListAdapter
        }

        mainViewModel.fetchCities()

        observeViewModel()
    }

    private fun observeViewModel() {

        mainViewModel.citiesLiveData.observe(this) { cities ->
            cities?.let {
                citiesList = cities.data
                val citiesArrayString = cities.data.map { it.cityName }.toTypedArray()
                val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, citiesArrayString)
                tvCity?.apply {
                    setAdapter(arrayAdapter)
                    setText(citiesList[0].cityName, false)
                    updateSelectedCityDistricts(0)
                    setOnItemClickListener { _, _, position, _ ->
                        updateSelectedCityDistricts(position)
                    }
                }
                mainViewModel.loading.postValue(false)
            }
        }
        mainViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            mainViewModel.loading.postValue(false)
        }
        mainViewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                loadingView?.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun updateSelectedCityDistricts(index: Int) {
        val selectedCity = citiesList[index]
        districtsList.clear()
        districtsList.addAll(selectedCity.districts)
        districtListAdapter.updateDistricts(districtsList)
    }
}