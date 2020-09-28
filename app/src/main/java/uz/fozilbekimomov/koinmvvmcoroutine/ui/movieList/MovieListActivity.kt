package uz.fozilbekimomov.koinmvvmcoroutine.ui.movieList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.fozilbekimomov.koinmvvmcoroutine.R
import uz.fozilbekimomov.koinmvvmcoroutine.core.list.MovieListAdapter
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.DataState
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.HomeItemDecoration

class MovieListActivity : AppCompatActivity(), MoveListContract.View, MovieListAdapter.OnPageEnded {

    private val homeVM: MovieListVM by viewModel()

    private val adapter = MovieListAdapter(R.layout.item_movie2)

    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

       id = intent.getIntExtra("categoryID", 0)
        val title = intent.getStringExtra("title")

        titleList.text = title

        movieList.adapter = adapter
        movieList.layoutManager = GridLayoutManager(this, 2)
        movieList.addItemDecoration(HomeItemDecoration(10))

        adapter.setPageEndedListener(this)

        iniObservers()
        loadData()
    }

    private fun loadData() {
        when (id) {
            0 -> homeVM.loadPopularMovie()
            1 -> homeVM.loadNowPlayinMovies()
            2 -> homeVM.loadTopRated()
            3 -> homeVM.loadUpcoming()
        }
    }

    private fun iniObservers() {
        homeVM.moviesLiveData.observe(this, movieObserver)
    }

    override val movieObserver: Observer<DataState> = Observer(::onMovieData)

    override fun onMovieData(data: DataState) {

        if (data.showProgress) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }

        if (data.movies != null && !data.movies.consumed)
            data.movies.consume()?.let { movies ->
                println(movies)
                movies.data?.let { adapter.addData(it) }
            }

        Log.d("SSSSSS", "onMovieData: $data")


    }

    override fun onPageEnd() {
        loadData()
    }
}