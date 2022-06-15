package com.example.database.dao

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.database.contracts.PhotoDBContracts
import com.example.database.models.PhotoDB

@Dao
interface PhotoDBDao {
    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun getAllPhotoDB(): List<PhotoDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoDB(photoDB: PhotoDB)

    @Delete
    suspend fun deletePhotoDB(photoDB: PhotoDB)

//    @Query(
//        "SELECT " +
//
//            "photo.${PhotoDBContracts.Colums.ID}," +
//            "photo.${PhotoDBContracts.Colums.WIDTH}," +
//            "photo.${PhotoDBContracts.Colums.HEIGHT}," +
//            "photo.${PhotoDBContracts.Colums.COLOR}," +
//            "photo.${PhotoDBContracts.Colums.DOWNLOADS}," +
//            "photo.${PhotoDBContracts.Colums.LIKES}," +
//            "url.${UrlsDBContracts.Colums.REGULAR}," +
//            "url.${UrlsDBContracts.Colums.SMALL}," +
//            "url.${UrlsDBContracts.Colums.THUMB}," +
//            "userDomain.${UserDBContracts.Colums.ID}," +
//            "userDomain.${UserDBContracts.Colums.USERNAME}," +
//            "userDomain.${UserDBContracts.Colums.NAME}," +
//            "userDomain.${UserDBContracts.Colums.BIO}," +
//            "userDomain.${UserDBContracts.Colums.LOCATION}," +
//            "userDomain.${UserDBContracts.Colums.TOTAL_LIKES}," +
//            "userDomain.${UserDBContracts.Colums.DOWNLOADS}," +
//            "user_im.${ProfileImageDBContracts.Colums.SMALL}," +
//            "user_im.${ProfileImageDBContracts.Colums.MEDIUM}," +
//            "user_im.${ProfileImageDBContracts.Colums.LARGE}," +
//            "userDomain.${UserDBContracts.Colums.TOTAL_PHOTOS}," +
//            "userDomain.${UserDBContracts.Colums.TOTAL_COLLECTIONS}," +
//            "userDomain.${UserDBContracts.Colums.FOLLOWED_BY_USER}," +
//            "userDomain.${UserDBContracts.Colums.FOLLOWERS_COUNT}," +
//            "userDomain.${UserDBContracts.Colums.FIRST_NAME}," +
//            "userDomain.${UserDBContracts.Colums.LAST_NAME}," +
//            "userDomain.${UserDBContracts.Colums.INSTAGRAM_USERNAME}," +
//            "userDomain.${UserDBContracts.Colums.TWITTER_USERNAME}," +
//            "userDomain.${UserDBContracts.Colums.PORTFOLIO_URL}," +
//            "userDomain.${UserDBContracts.Colums.UPDATE_AT}," +
//            "photo.${PhotoDBContracts.Colums.LIKED_BY_USER}," +
//            "photo.${PhotoDBContracts.Colums.CREATED_AT}," +
//            "photo.${PhotoDBContracts.Colums.UPDATE_AT}" +
//            " FROM ${PhotoDBContracts.TABLE_NAME}  photo " +
//            "LEFT JOIN ${UrlsDBContracts.TABLE_NAME} url ON photo.${PhotoDBContracts.Colums.URLS_ID} =  url.${UrlsDBContracts.Colums.ID} " +
//            "LEFT JOIN ${UserDBContracts.TABLE_NAME} userDomain ON photo.${PhotoDBContracts.Colums.USER_ID} = userDomain.${UserDBContracts.Colums.ID} " +
//            "LEFT JOIN ${ProfileImageDBContracts.TABLE_NAME} user_im ON userDomain.${UserDBContracts.Colums.ID} = user_im. ${ProfileImageDBContracts.Colums.ID}"
//    )
//    suspend fun getHomePhoto(): List<PhotoWithInfoDB>
}
