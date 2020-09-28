package uz.fozilbekimomov.koinmvvmcoroutine.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home.view.*
import uz.fozilbekimomov.koinmvvmcoroutine.R
import uz.fozilbekimomov.koinmvvmcoroutine.core.models.HomeData
import uz.fozilbekimomov.koinmvvmcoroutine.core.utils.HomeItemDecoration


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 9/28/20
 * @project Koin+MVVM+Coroutine
 */


class HomeAdapter (val onAllClick: OnAllClick): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val data = ArrayList<HomeData>()

     fun setData(data: HomeData) {
        this.data.add(data)
        notifyItemInserted(this.data.size)
    }

   inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindData(d: HomeData) {
            itemView.movieList.layoutManager =
                LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
            val adapter = MovieListAdapter(R.layout.item_movie1)
            itemView.movieList.adapter = adapter
            itemView.movieList.addItemDecoration(HomeItemDecoration(10))
            d.data?.let { adapter.setData(it) }

            itemView.categoryTitle.text=d.categoryTitle
            itemView.all.setOnClickListener {
                onAllClick.onOpenMovieList(d.categoryId,d.categoryTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    public interface OnAllClick{
        fun onOpenMovieList(id:Int,title:String)
    }

}