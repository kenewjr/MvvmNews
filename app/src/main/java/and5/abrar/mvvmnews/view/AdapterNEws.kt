package and5.abrar.mvvmnews.view


import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvvmnews.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class AdapterNEws(private var onclick :(getAllNewsItem)->Unit):RecyclerView.Adapter<AdapterNEws.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    private var datanews : List<getAllNewsItem>? = null
    fun setDataNews(news : List<getAllNewsItem>){
        this.datanews = news
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardnews.setOnClickListener {
            onclick(datanews!![position])
        }
        Glide.with(holder.itemView.context)
            .load(datanews!![position].image)
            .into(holder.itemView.gambarnews)
        holder.itemView.judulnews.text = datanews!![position].title
        holder.itemView.tanggalNews.text = datanews!![position].createdAt
        holder.itemView.Author.text = datanews!![position].author
    }

    override fun getItemCount(): Int {
        if (datanews == null){
            return  0
        }else{
            return  datanews!!.size
        }
    }
}