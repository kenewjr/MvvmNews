package and5.abrar.mvvmnews.view.film

import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.view.DetailNews
import and5.abrar.mvvmnews.viewmodel.ViewModelFIlm
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_film.*

@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {
    lateinit var adapterFilm: AdapterFilm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        iniViewmodel()
    }
    fun iniViewmodel(){
        adapterFilm = AdapterFilm(){
            val pindah = Intent(applicationContext, DetailFilmActivity::class.java)
            pindah.putExtra("detailfilm",it)
            startActivity(pindah)
        }
        rvFilm.layoutManager = LinearLayoutManager(this)
        rvFilm.adapter = adapterFilm
        val viewModel = ViewModelProvider(this).get(ViewModelFIlm::class.java)
        viewModel.film.observe(this, Observer {
            if (it != null){
                adapterFilm.setDataFilm(it)
                adapterFilm.notifyDataSetChanged()
            }
        })
    }
}