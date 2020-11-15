package com.jinhanexample.jetBrain.shoppingList.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jinhanexample.jetBrain.shoppingList.data.db.entity.ShoppingItem
//import androidx.lifecycle.LiveData

@Dao
interface ShoppingDao {

    // OnConflictStrategy.REPLACE = Insert 할때 PrimaryKey가 겹치는 것이 있으면 덮어 쓴다
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
}