package com.gyphytestapp.data.db.repository

import com.gyphytestapp.data.db.GifsAppDb
import com.gyphytestapp.data.db.mapper.DataToLoadedMapper
import com.gyphytestapp.network.model.Data
import javax.inject.Inject

class LoadedPicsRepository @Inject constructor(
    db: GifsAppDb,
    private val mapper: DataToLoadedMapper
) {
    private val dao = db.loadedPicsDao()

    fun insertList(list: List<Data>) {
        dao.insertAll(mapper.mapList(list))
    }

    fun getDeletedList(list: List<Data>) =
        dao.selectDeleted(list.filter { !it.id.isNullOrEmpty() }.map { it.id ?: "" })

    fun setLoadedPicDeleted(pic: Data) {
        val loadedPic = mapper.mapData(pic)
        loadedPic.isDeleted = true
        dao.setDeleted(loadedPic)
    }
}