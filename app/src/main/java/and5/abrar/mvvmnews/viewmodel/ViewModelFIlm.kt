package and5.abrar.mvvmnews.viewmodel


import and5.abrar.mvpnews.network.ApiService
import and5.abrar.mvvmnews.model.GetAllFilmItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFIlm @Inject constructor(apiService: ApiService): ViewModel() {
    private var liveDataFilm = MutableLiveData<List<GetAllFilmItem>>()
    val film : LiveData<List<GetAllFilmItem>> = liveDataFilm

    init {
        viewModelScope.launch {
            val datafilm = apiService.getAllfilm()
            delay(2000)
            liveDataFilm.value = datafilm
        }
    }
}