package com.hpr.data.db

abstract class BaseDao<out T> {

    abstract suspend fun <T>insert(data : T)

    abstract suspend fun <T>insert(data : List<T>)

    abstract suspend fun <T>delete(data : T)

    abstract suspend fun <T>deleteAll()

    suspend fun <T>safeInsert(data : T?){
        if (data != null)
            insert(data)
    }

}