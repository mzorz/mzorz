package com.zorz.mario.api.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zorz.mario.model.PhotoItem;

import java.lang.reflect.Type;

/**
 * Created by mariozorz on 1/18/15.
 */
public class PhotoItemSerializer implements JsonDeserializer<PhotoItem> {

    @Override
    public PhotoItem deserialize(JsonElement jsonElement, Type typeOF,
                            JsonDeserializationContext context) throws JsonParseException {

        if (jsonElement.isJsonPrimitive()) {
            return null;
        }
        JsonObject jobject = jsonElement.getAsJsonObject();
        PhotoItem photo = new PhotoItem();

        if (jobject != null){
            if (jobject.has("filename"))
                photo.filename = jobject.get("filename").getAsString();

            if (jobject.has("original"))
                photo.original = jobject.get("original").getAsString();

            if (jobject.has("full"))
                photo.full = jobject.get("full").getAsString();

            if (jobject.has("big"))
                photo.big = jobject.get("big").getAsString();

            if (jobject.has("small"))
                photo.small = jobject.get("small").getAsString();

            if (jobject.has("thumb"))
                photo.thumb = jobject.get("thumb").getAsString();

            if (jobject.has("ext"))
                photo.ext = jobject.get("ext").getAsString();

            if (jobject.has("path"))
                photo.path = jobject.get("path").getAsString();

        }

        return photo;
    }
}
