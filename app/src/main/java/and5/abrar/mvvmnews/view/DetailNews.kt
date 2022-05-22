package and5.abrar.mvvmnews.view

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.room.Favorite
import and5.abrar.mvvmnews.room.FavoriteDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DetailNews : AppCompatActivity() {
    var newsDb : FavoriteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        newsDb=  FavoriteDatabase.getinstance(this)
        val detailnews = intent.getParcelableExtra("detailnews") as getAllNewsItem?
        val detailnewsfav = intent.getParcelableExtra("detailnewsfav") as Favorite?
        if(detailnews != null){
            tvJudul.text = detailnews?.title
            tvDesc.text = detailnews?.description
            Glide.with(this).load(detailnews?.image).into(imgdetail)
        }else if (detailnewsfav != null){
            tvJudul.text = detailnewsfav?.title
            tvDesc.text = detailnewsfav?.description
            Glide.with(this).load(detailnewsfav?.image).into(imgdetail)
            btn_fav.visibility = View.INVISIBLE
        }


        btn_fav.setOnClickListener {
            addfavfilm()
        }

    }
    fun addfavfilm(){

        val detailNews = intent.getParcelableExtra<getAllNewsItem>("detailnews")

        GlobalScope.async {
            val author = detailNews!!.author
            val description = detailNews!!.description
            val title = detailNews!!.title
            val image = detailNews!!.image

            newsDb?.favoritedao()?.addFavorite(Favorite(null, author, description, image, title) )

        }
        startActivity(Intent(this, FavoriteActvty::class.java))
    }
}