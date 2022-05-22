package and5.abrar.mvvmnews.view

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvpnews.network.ApiClient
import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.viewmodel.ViewModelNews
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var adapterNEws: AdapterNEws
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewmodel()
        fav.setOnClickListener {
            startActivity(Intent(this,FavoriteActvty::class.java))
        }
//      iniBukanview()
    }
    fun iniViewmodel(){
        adapterNEws = AdapterNEws(){
            val pindah = Intent(applicationContext,DetailNews::class.java)
            pindah.putExtra("detailnews",it)
            startActivity(pindah)
        }
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapterNEws
        val viewModel = ViewModelProvider(this).get(ViewModelNews::class.java)
        viewModel.news.observe(this, Observer {
            if (it != null){
                adapterNEws.setDataNews(it)
                adapterNEws.notifyDataSetChanged()
            }
        })
    }
//    fun iniBukanview(){
//        ApiClient.instance.getAllnews()
//            .enqueue(object : Callback<List<getAllNewsItem>> {
//                override fun onResponse(
//                    call: Call<List<getAllNewsItem>>,
//                    response: Response<List<getAllNewsItem>>
//                ) {
//                    if (response.isSuccessful){
//                       datanews = response.body()!!
//                        adapterNEws.setDataFilm(datanews)
//                        adapterNEws.notifyDataSetChanged()
//                    }else{
//                        Toast.makeText(this@MainActivity,response.message(), Toast.LENGTH_LONG).show()
//                    }
//                }
//                override fun onFailure(call: Call<List<getAllNewsItem>>, t: Throwable) {
//                    Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
//                }
//            })
//    }
}