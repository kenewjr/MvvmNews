package and5.abrar.mvvmnews.view

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.room.Favorite
import and5.abrar.mvvmnews.room.FavoriteDatabase
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news_fav.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNewsFavorite(val datafav: List<Favorite>,
                        private var onClik : (Favorite)->Unit ):
    RecyclerView.Adapter<AdapterNewsFavorite.ViewHolder>(){
    var newsDb : FavoriteDatabase? = null
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_fav,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardNewsFav.setOnClickListener {
            onClik(datafav[position])
        }
        Glide.with(holder.itemView.context)
            .load(datafav[position].image)
            .into(holder.itemView.newsimagefav)
        holder.itemView.tv_newstitle.text = datafav[position].title
        holder.itemView.tv_newsAuthor.text = datafav[position].author
        holder.itemView.del_fav.setOnClickListener {
            newsDb = FavoriteDatabase.getinstance(it.context)
            AlertDialog.Builder(it.context)
                .setTitle("Hapus data")
                .setMessage("yakin hapus data")
                .setPositiveButton("ya"){
                        dialogInterface : DialogInterface, i : Int ->
                    GlobalScope.async {
                        val result = newsDb?.favoritedao()?.deletefavorite(datafav[position])
                        (holder.itemView.context as FavoriteActvty).runOnUiThread{
                            if (result != 0){
                                Toast.makeText(it.context, "data ${datafav[position].title} dihapus",
                                    Toast.LENGTH_LONG).show()
                                (holder.itemView.context as FavoriteActvty).getFilmFav()
                            }else{
                                Toast.makeText(it.context, "gagal di hapus", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Tidak"){
                        dialogInterface : DialogInterface, i : Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return datafav.size
    }
}