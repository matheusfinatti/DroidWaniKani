package com.mfinatti.wanikanisimple.core.network.adapters

import com.mfinatti.wanikanisimple.core.network.data.ResponseDTO
import com.mfinatti.wanikanisimple.core.network.data.model.PreferencesDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanaVocabularyDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanjiDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.RadicalDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.VocabularyDTO
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class ResponseAdapter(
    private val moshi: Moshi
) : JsonAdapter<ResponseDTO<SubjectDTO>>() {

    @FromJson
    override fun fromJson(reader: JsonReader): ResponseDTO<SubjectDTO>? {
        // Parse the JSON into a Map first
        val jsonMap = reader.readJsonValue() as? Map<*, *> ?: return null

        // Extract the `object` field to determine the type
        val objectType = jsonMap["object"] as? String ?: throw JsonDataException("Missing `object` field")

        // Create an adapter for the appropriate `SubjectDTO` subtype
        val dataAdapter = when (objectType) {
            "radical" -> moshi.adapter(RadicalDTO::class.java)
            "kanji" -> moshi.adapter(KanjiDTO::class.java)
            "vocabulary" -> moshi.adapter(VocabularyDTO::class.java)
            "kana_vocabulary" -> moshi.adapter(KanaVocabularyDTO::class.java)
            else -> throw JsonDataException("Unknown `object` type: $objectType")
        }

        // Parse other fields in `ResponseDTO`
        val id = jsonMap["id"] as Int
        val obj = jsonMap["object"] as String
        val url = jsonMap["url"] as String
        val dataUpdatedAt = jsonMap["data_updated_at"] as String
        val dataJson = jsonMap["data"] as Map<*, *>
        val data = dataAdapter.fromJsonValue(dataJson)

        val preferences = jsonMap["preferences"] as? PreferencesDTO

        return ResponseDTO(
            id = id,
            obj = obj,
            url = url,
            dataUpdatedAt = dataUpdatedAt,
            data = data ?: throw JsonDataException("Unable to parse `data` field"),
            preferences = preferences,
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ResponseDTO<SubjectDTO>?) {
        throw UnsupportedOperationException("toJson not supported")
    }
}
