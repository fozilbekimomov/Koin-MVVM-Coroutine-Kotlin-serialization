package uz.fozilbekimomov.koinmvvmcoroutine.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie1.view.*
import uz.fozilbekimomov.koinmvvmcoroutine.core.models.response.Movie
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.MOVIE_POSTER_BASE_URL
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.changeDrawableColor


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


class MovieListAdapter(val layoutId: Int) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var data: ArrayList<Movie>? = null

    private var onPageEnded: OnPageEnded? = null

    fun setPageEndedListener(onPageEnded: OnPageEnded) {
        this.onPageEnded = onPageEnded
    }

    fun setData(movies: ArrayList<Movie>) {
        this.data = movies
        notifyDataSetChanged()
    }

    fun addData(movies: ArrayList<Movie>) {
        if (data == null) {
            this.data = movies
            notifyDataSetChanged()
        } else {
            this.data?.let {
                it.addAll(movies)
                notifyItemRangeInserted(it.size - movies.size, movies.size)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let {
            holder.bindData(it[position])
        }

        if (position == itemCount - 4) {
            onPageEnded?.onPageEnd()
        }
    }

    override fun getItemCount(): Int {
        return if (data == null) {
            0
        } else data!!.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindData(movie: Movie) {
            Glide.with(itemView).load(MOVIE_POSTER_BASE_URL + movie.posterPath)
                .into(itemView.imageMovie)
            itemView.titleMovie.text = movie.movieTitle
            itemView.dateMovie.text = movie.releaseDate

            movie.vote?.let {
//                    itemView.rateMovie?.setPercentage((it * 10).toInt())
                itemView.rateMovie?.text = (it.toString())
                itemView.rateMovie?.changeDrawableColor(getColorFromRate(it), "#ffffff")
            }

        }

        private fun getColorFromRate(rate: Float): String {
            return if (rate <= 2.5)
                "#FF9800"
            else if (rate <= 5.0)
                "#8BC34A"
            else if (rate <= 7.5)
                "#4CAF50"
            else "#3F51B5"
        }
    }


    interface OnPageEnded {
        fun onPageEnd()
    }


}