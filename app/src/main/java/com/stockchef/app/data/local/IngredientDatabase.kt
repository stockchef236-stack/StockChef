package com.stockchef.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [IngredientEntity::class],
    version = 1
)
abstract class IngredientDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao

    companion object {

        @Volatile
        private var INSTANCE: IngredientDatabase? = null

        fun getDatabase(context: Context): IngredientDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IngredientDatabase::class.java,
                    "ingredient_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}