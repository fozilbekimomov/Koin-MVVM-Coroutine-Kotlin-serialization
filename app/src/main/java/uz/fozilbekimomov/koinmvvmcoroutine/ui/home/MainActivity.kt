package uz.fozilbekimomov.koinmvvmcoroutine.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.fozilbekimomov.koinmvvmcoroutine.R
import uz.fozilbekimomov.koinmvvmcoroutine.core.list.HomeAdapter
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.DataState
import uz.fozilbekimomov.koinmvvmcoroutine.ui.movieList.MovieListActivity

class MainActivity : AppCompatActivity(),HomeContract.View, HomeAdapter.OnAllClick {

    private val homeVM: HomeVM by viewModel()

    private val adapter=HomeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Glide.with(this).load(APP_HEADER_ICON).into(homeIcon)

        homeList.adapter = adapter
        homeList.layoutManager=LinearLayoutManager(this)

        iniObservers()

        homeVM.loadNowPlayingMovies()
        homeVM.loadPopularMovie()
        homeVM.loadTopRated()
        homeVM.loadUpcoming()
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

        if (data.movies != null && !data.movies.consumed)
            data.movies.consume()?.let { movies ->
                println(movies)
                adapter.setData(movies)
            }

        Log.d("SSSSSS", "onMovieData: $data")


    }

    override fun onOpenMovieList(id: Int,title:String) {
        val intent = Intent(this, MovieListActivity::class.java)
        intent.putExtra("categoryID", id)
        intent.putExtra("title", title)
        startActivity(intent)
    }
}