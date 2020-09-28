package uz.fozilbekimomov.koinmvvmcoroutine.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.fozilbekimomov.koinmvvmcoroutine.R
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.DataState

class MainActivity : AppCompatActivity(),HomeContract.View {

    private val homeVM :HomeVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniObservers()

        homeVM.loadPopularMovie()
    }

    private fun iniObservers() {
        homeVM.moviesLiveData.observe(this,movieObserver)
    }

    override val movieObserver: Observer<DataState> = Observer(::onMovieData)

    override fun onMovieData(data: DataState) {

        if (data.showProgress) {
            progress.visibility= View.VISIBLE
        }else{
            progress.visibility= View.GONE
        }

        Log.d("SSSSSS", "onMovieData: $data")


    }
}