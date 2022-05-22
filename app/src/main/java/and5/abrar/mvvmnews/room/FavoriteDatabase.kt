package and5.abrar.mvvmnews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoritedao():FavoriteDao

    companion object{
        private var INSTANCE:FavoriteDatabase? = null

        fun getinstance(context: Context):FavoriteDatabase?{
            if(INSTANCE == null){
                synchronized(FavoriteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteDatabase::class.java,"favorite.db").build()
                }
            }
            return INSTANCE
        }
    }
}