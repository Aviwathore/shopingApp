package com.example.userinformation.cloth

data class ClothList(var clothName : String, var price :Double)
{
    override fun toString(): String {

        return "$clothName                                             $price"
    }
}

//fun updateClothItemFavoriteStatus(itemId: Int, isFavorite: Boolean): Int {
//    val db = this.writableDatabase
//    val values = ContentValues().apply {
//        put(IS_FAV, if (isFavorite) 1 else 0) // SQLite does not have a boolean type, so we use 1 for true and 0 for false
//    }
//    val selection = "$ID = ?"
//    val selectionArgs = arrayOf(itemId.toString())
//    val rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs)
//    db.close()
//    return rowsUpdated
//}
