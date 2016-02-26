package com.aarnott.sortable.util;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class IsoZonedDateTimeConverter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    @Override
    public JsonElement serialize(
            final ZonedDateTime dateTimeToSerialize,
            final Type type,
            final JsonSerializationContext context) {

        return new JsonPrimitive(dateTimeToSerialize.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }

    @Override
    public ZonedDateTime deserialize(
            final JsonElement jsonElementToDeserialize,
            final Type deserializedType,
            final JsonDeserializationContext context) throws JsonParseException {

        return ZonedDateTime.parse(jsonElementToDeserialize.getAsString(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

}
