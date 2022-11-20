package com.gyphytestapp.data.db.mapper

import com.gyphytestapp.data.db.entity.LoadedPics
import com.gyphytestapp.network.model.Data

class DataToLoadedMapper : IMapper<Data, LoadedPics> {
    override fun mapData(inData: Data): LoadedPics =
        LoadedPics(id = inData.id ?: "", url = inData.images?.original?.url ?: "")

    override fun mapList(inData: List<Data>): List<LoadedPics> = inData.map { mapData(it) }
}