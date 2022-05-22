package and5.abrar.mvvmnews.view.film

import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.model.GetAllFilmItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class AdapterFilm(private var onclik : (GetAllFilmItem)->Unit):RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    private var datafilm : List<GetAllFilmItem>? = null
    fun setDataFilm(film : List<GetAllFilmItem>){
        this.datafilm = film
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardfilm.setOnClickListener {
            onclik(datafilm!![position])
        }
        Glide.with(holder.itemView.context)
            .load(datafilm!![position].image)
            .into(holder.itemView.gambarfilm)
        holder.itemView.judulfilm.text= datafilm!![position].name
        holder.itemView.Director.text = datafilm!![position].director
        holder.itemView.tanggalfilm.text = datafilm!![position].date
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return  0
        }else{
            return  datafilm!!.size
        }
    }
}