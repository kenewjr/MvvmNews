package and5.abrar.mvvmnews.view

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvpnews.network.ApiClient
import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.viewmodel.ViewModelNews
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapterNEws: AdapterNEws
    private lateinit var datanews : List<getAllNewsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterNEws = AdapterNEws()
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapterNEws
//        iniViewmodel()
        iniBukanview()
    }
    fun iniViewmodel(){
        val viewModel = ViewModelProvider(this).get(ViewModelNews::class.java)
        viewModel.getLivedataNews().observe(this, Observer {
            if (it != null){
                adapterNEws.setDataFilm(it)
                adapterNEws.notifyDataSetChanged()
            }
        })
            viewModel.getApiNews()
    }
    fun iniBukanview(){
        ApiClient.instance.getAllnews()
            .enqueue(object : Callback<List<getAllNewsItem>> {
                override fun onResponse(
                    call: Call<List<getAllNewsItem>>,
                    response: Response<List<getAllNewsItem>>
                ) {
                    if (response.isSuccessful){
                       datanews = response.body()!!
                        adapterNEws.setDataFilm(datanews)
                        adapterNEws.notifyDataSetChanged()
                    }else{
                        Toast.makeText(this@MainActivity,response.message(), Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<List<getAllNewsItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
                }
            })
    }
}