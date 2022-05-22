package and5.abrar.mvvmnews.view.film


import and5.abrar.mvvmnews.R
import and5.abrar.mvvmnews.model.GetAllFilmItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_film.*
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.activity_detail_news.imgdetail
import kotlinx.android.synthetic.main.activity_detail_news.tvDesc
import kotlinx.android.synthetic.main.activity_detail_news.tvJudul

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        val detailfilm = intent.getParcelableExtra("detailfilm") as GetAllFilmItem?

        tvJudul.text = detailfilm?.name
        tvDesc.text = detailfilm?.description
        tvDirector.text = detailfilm?.director
        tvDate.text = detailfilm?.date
        Glide.with(this).load(detailfilm?.image).into(imgdetail)
    }
}