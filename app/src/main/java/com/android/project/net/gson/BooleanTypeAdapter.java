package com.android.project.net.gson;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Finn
 * @date 2020
 */
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(JsonReader reader) throws IOException {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return false;
        }

        if (reader.peek() == JsonToken.NUMBER) {
            return reader.nextInt() == 0 ? false : true;
        }

        if (reader.peek() == JsonToken.STRING) {
            return quearyString(reader.nextString());
        }

        return reader.nextBoolean();
    }

    @Override
    public void write(JsonWriter writer, Boolean value) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue();
            return;
        }

        writer.value(value);
    }


    private boolean quearyString(String data) {
        if (TextUtils.isEmpty(data)) {
            return false;
        } else {
            return Boolean.parseBoolean(data);
        }

    }

}