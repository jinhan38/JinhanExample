//package com.jinhanexample.jetBrain.shoppingList.data.repository
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.jinhanexample.jetBrain.shoppingList.data.db.ShoppingDao
//import com.jinhanexample.jetBrain.shoppingList.data.db.ShoppingDatabase
//import com.jinhanexample.jetBrain.shoppingList.data.db.entity.ShoppingItem
//
//
//@Database(
//    entities = [ShoppingItem::class],
//    version = 1
//)
//
//abstract class ShoppingRepository : RoomDatabase() {
//
//    abstract fun getShoppingDao(): ShoppingDao
//
//    companion object {
//
//        @Volatile
//        private var instance: ShoppingDatabase? = null
//        private val LOCK = Any()
//        //invoke는 생성자와 비슷한 개념
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also {
//                instance = it
//            }
//        }
//
//
//        private fun createDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            ShoppingDatabase::class.java,
//            "ShoppingDB.db"
//        ).build()
//
//    }
//}