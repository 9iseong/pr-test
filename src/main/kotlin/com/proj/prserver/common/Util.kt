package com.proj.prserver.common

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime


inline fun getStrURLDecode(value: String): String = URLDecoder.decode(value, StandardCharsets.UTF_8.toString())

inline fun <reified T> ObjectMapper.readValue(s: String): T = this.readValue(s, object : TypeReference<T>() {})


//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
  return convert()
}

//convert a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(): T {
  return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {

  val gson = GsonBuilder().registerTypeAdapter(
    LocalDateTime::class.java,
    JsonDeserializer<LocalDateTime> { json, type, jsonDeserializationContext ->
      ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()
    } as JsonDeserializer<LocalDateTime?>?).create()

  val json = gson.toJson(this)
  return gson.fromJson(json, object : TypeToken<O>() {}.type)
}
