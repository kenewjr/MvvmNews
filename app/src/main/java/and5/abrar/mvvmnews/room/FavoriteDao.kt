package and5.abrar.mvvmnews.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    fun addFavorite(favorite : Favorite) : Long

    @Query("SELECT * FROM Favorite")
    fun getFavorite(): List<Favorite>

    @Delete
    fun deletefavorite(favorite: Favorite):Int

}