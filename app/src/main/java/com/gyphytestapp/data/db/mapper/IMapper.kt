package com.gyphytestapp.data.db.mapper

interface IMapper<In, Out> {
    fun mapData(inData: In): Out
    fun mapList(inData: List<In>): List<Out>
}