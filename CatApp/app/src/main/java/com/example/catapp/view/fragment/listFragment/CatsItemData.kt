package com.example.catapp.view.fragment.listFragment

import com.example.catapp.model.dto.CatsItemsDTO


data class CatsItemData(
    val id: String,
    val url: String
) {

    companion object {

        fun MAP_TO_CATS_ITEM(list: List<CatsItemsDTO>) : List<CatsItemData>{


            val newList = arrayListOf<CatsItemData>()
            for (item: CatsItemsDTO in list) {
                newList.add(CatsItemData(item.id, item.url))
            }
            return newList
        }
    }
}