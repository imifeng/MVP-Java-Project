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
public class DoubleTypeAdapter extends TypeAdapter<Double> {
    @Override
    public Double read(JsonReader reader) throws IOException {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return 0.0;
        }

        if (reader.peek() == JsonToken.BOOLEAN) {
            return reader.nextBoolean() ? 1.0 : 0.0;
        }

        if (reader.peek() == JsonToken.STRING) {
            return quearyString(reader.nextString());
        }

        return reader.nextDouble();
    }

    @Override
    public void write(JsonWriter writer, Double value) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue();
            return;
        }

        writer.value(value);
    }


    private double quearyString(String data) {
        if (TextUtils.isEmpty(data)) {
            return 0.0;
        } else {
            return Double.parseDouble(data);
        }

    }

}