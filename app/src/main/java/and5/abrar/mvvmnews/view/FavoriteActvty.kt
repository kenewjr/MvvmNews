package and5.abrar.mvvmnews.view


import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.room.Favorite
import and5.abrar.mvvmnews.room.FavoriteDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite_actvty.*
import kotlinx.android.synthetic.main.item_news_fav.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActvty : AppCompatActivity() {
    var newsDb : FavoriteDatabase? = null
    lateinit var adapterNEws: AdapterNEws
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_actvty)
        getFilmFav()
        newsDb=  FavoriteDatabase.getinstance(this)

    }
    fun getFilmFav(){
        rv_favnews.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch {
            val listFavFilm = newsDb?.favoritedao()?.getFavorite()
            runOnUiThread{
                if (listFavFilm?.size != null) {
                    if(listFavFilm.isEmpty()){
                        tv.text = "data kosong"
                    }else{
                        listFavFilm.let {
                            rv_favnews.adapter = AdapterNewsFavorite(it){
                                val pindah = Intent(applicationContext,DetailNews::class.java)
                                pindah.putExtra("detailnewsfav",it)
                                startActivity(pindah)
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        getFilmFav()
    }

}