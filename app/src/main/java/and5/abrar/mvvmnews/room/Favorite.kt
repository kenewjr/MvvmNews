package and5.abrar.mvvmnews.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Favorite(
  @PrimaryKey(autoGenerate = true) val id :Int?,
  @ColumnInfo(name = "author") val author :String,
  @ColumnInfo(name = "description") val description :String,
  @ColumnInfo(name = "image") val image :String,
  @ColumnInfo(name = "title") val title : String
):Parcelable
