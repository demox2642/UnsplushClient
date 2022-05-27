package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.PhotoDBContracts
import com.example.database.contracts.ProfileImageDBContracts
import com.example.database.contracts.UrlsDBContracts
import com.example.database.contracts.UserDBContracts
import com.example.database.models.PhotoDB
import com.example.database.models.PhotoWithInfoDB

@Dao
interface PhotoDBDao {
    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun getAllPhotoDB(): List<PhotoDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoDB(photoDB: PhotoDB)

    @Delete
    suspend fun deletePhotoDB(photoDB: PhotoDB)

    @Query(
        "SELECT " +

            "photo.${PhotoDBContracts.Colums.ID}," +
            "photo.${PhotoDBContracts.Colums.WIDTH}," +
            "photo.${PhotoDBContracts.Colums.HEIGHT}," +
            "photo.${PhotoDBContracts.Colums.COLOR}," +
            "photo.${PhotoDBContracts.Colums.DOWNLOADS}," +
            "photo.${PhotoDBContracts.Colums.LIKES}," +
            "url.${UrlsDBContracts.Colums.REGULAR}," +
            "url.${UrlsDBContracts.Colums.SMALL}," +
            "url.${UrlsDBContracts.Colums.THUMB}," +
            "user.${UserDBContracts.Colums.ID}," +
            "user.${UserDBContracts.Colums.USERNAME}," +
            "user.${UserDBContracts.Colums.NAME}," +
            "user.${UserDBContracts.Colums.BIO}," +
            "user.${UserDBContracts.Colums.LOCATION}," +
            "user.${UserDBContracts.Colums.TOTAL_LIKES}," +
            "user.${UserDBContracts.Colums.DOWNLOADS}," +
            "user_im.${ProfileImageDBContracts.Colums.SMALL}," +
            "user_im.${ProfileImageDBContracts.Colums.MEDIUM}," +
            "user_im.${ProfileImageDBContracts.Colums.LARGE}," +
            "user.${UserDBContracts.Colums.TOTAL_PHOTOS}," +
            "user.${UserDBContracts.Colums.TOTAL_COLLECTIONS}," +
            "user.${UserDBContracts.Colums.FOLLOWED_BY_USER}," +
            "user.${UserDBContracts.Colums.FOLLOWERS_COUNT}," +
            "user.${UserDBContracts.Colums.FIRST_NAME}," +
            "user.${UserDBContracts.Colums.LAST_NAME}," +
            "user.${UserDBContracts.Colums.INSTAGRAM_USERNAME}," +
            "user.${UserDBContracts.Colums.TWITTER_USERNAME}," +
            "user.${UserDBContracts.Colums.PORTFOLIO_URL}," +
            "user.${UserDBContracts.Colums.UPDATE_AT}," +
            "photo.${PhotoDBContracts.Colums.LIKED_BY_USER}," +
            "photo.${PhotoDBContracts.Colums.CREATED_AT}," +
            "photo.${PhotoDBContracts.Colums.UPDATE_AT}" +
            " FROM ${PhotoDBContracts.TABLE_NAME}  photo " +
            "LEFT JOIN ${UrlsDBContracts.TABLE_NAME} url ON photo.${PhotoDBContracts.Colums.URLS_ID} =  url.${UrlsDBContracts.Colums.ID} " +
            "LEFT JOIN ${UserDBContracts.TABLE_NAME} user ON photo.${PhotoDBContracts.Colums.USER_ID} = user.${UserDBContracts.Colums.ID} " +
            "LEFT JOIN ${ProfileImageDBContracts.TABLE_NAME} user_im ON user.${UserDBContracts.Colums.ID} = user_im. ${ProfileImageDBContracts.Colums.ID}"
    )
    suspend fun getHomePhoto(): List<PhotoWithInfoDB>
}
