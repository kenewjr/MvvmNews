package and5.abrar.mvvmnews.viewmodel

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvpnews.network.ApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelNews @Inject constructor(apiService: ApiService):ViewModel() {
    private var liveDataNews = MutableLiveData<List<getAllNewsItem>>()
            val news : LiveData<List<getAllNewsItem>> = liveDataNews

    init {
         viewModelScope.launch {
            val datanews = apiService.getAllnews()
            delay(2000)
            liveDataNews.value = datanews
        }
    }

}