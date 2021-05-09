package com.example.util.simpletimetracker.domain.repo

import com.example.util.simpletimetracker.domain.model.RecordTag

interface RecordTagRepo {

    suspend fun getAll(): List<RecordTag>

    suspend fun get(id: Long): RecordTag?

    suspend fun getByType(typeId: Long): List<RecordTag>

    suspend fun add(tag: RecordTag): Long

    suspend fun archive(id: Long)

    suspend fun remove(id: Long)

    suspend fun clear()
}