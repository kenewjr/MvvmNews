package and5.abrar.mvvmnews.viewmodel

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvpnews.network.ApiClient
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNews:ViewModel() {
    lateinit var liveDataNews : MutableLiveData<List<getAllNewsItem>>

    init {
        liveDataNews = MutableLiveData()
    }

    fun getLivedataNews (): MutableLiveData<List<getAllNewsItem>> {
        return  liveDataNews
    }

    fun getApiNews(){
        ApiClient.instance.getAllnews()
            .enqueue(object : Callback<List<getAllNewsItem>> {
                override fun onResponse(
                    call: Call<List<getAllNewsItem>>,
                    response: Response<List<getAllNewsItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataNews.postValue(response.body())
                    }else{
                        liveDataNews.postValue(null)
                    }
                }
                override fun onFailure(call: Call<List<getAllNewsItem>>, t: Throwable) {
                    liveDataNews.postValue(null)
                }

            })
    }
}