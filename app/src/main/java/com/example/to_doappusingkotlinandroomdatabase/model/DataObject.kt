package com.example.to_doappusingkotlinandroomdatabase.model

object DataObject {

    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String) {
        listdata.add(CardInfo(title, priority))

    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }
    fun deleteAll(){
        listdata.clear()
    }
    fun getData(position:Int):  CardInfo {
        return listdata[position]
    }
    fun deleteData(position: Int) {
        listdata.removeAt(position)
    }
    fun updateData(position: Int, title: String, priority: String){
      listdata[position].title=title
      listdata[position].priority=priority
    }
}