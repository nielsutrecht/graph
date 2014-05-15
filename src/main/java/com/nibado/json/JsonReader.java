package com.nibado.json;

import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public abstract class JsonReader {

    protected JsonParser _parser;

    public JsonReader() {

    }

    public void setInput(final File input) throws Exception {
        _parser = new JsonFactory().createJsonParser(input);
    }

    public void setParser(final JsonParser parser) {
        _parser = parser;
    }

    protected void readUnexpected() throws Exception {
        final JsonToken current = _parser.getCurrentToken();

        if (current == JsonToken.FIELD_NAME) {
            _parser.nextToken();
            readUnexpected();
        }
        else if (current == JsonToken.START_OBJECT) {
            readUnexpectedObject();
        }
        else if (current == JsonToken.START_ARRAY) {
            readUnexpectedArray();
        }
    }

    protected void readUnexpectedObject() throws Exception {
        while (_parser.getCurrentToken() != JsonToken.END_OBJECT) {
            readUnexpected();
        }
    }

    protected void readUnexpectedArray() throws Exception {
        while (_parser.getCurrentToken() != JsonToken.END_ARRAY) {
            readUnexpected();
        }
    }

    protected String readField() throws Exception {
        expectField();
        return _parser.getCurrentName();
    }

    protected void expectStartObject() throws Exception {
        if (_parser.nextToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException("Object expected", _parser.getCurrentLocation());
        }
    }

    protected void expectEndObject() throws Exception {
        if (_parser.nextToken() != JsonToken.END_OBJECT) {
            throw new JsonParseException("Object end expected", _parser.getCurrentLocation());
        }
    }

    protected void expectField() throws Exception {
        expectField(null);
    }

    protected void expectField(final String name) throws Exception {
        if (_parser.nextToken() != JsonToken.FIELD_NAME) {
            throw new JsonParseException("Field expected", _parser.getCurrentLocation());
        }
        if (name != null && !_parser.getCurrentName().equals(name)) {
            throw new JsonParseException("Field '" + name + "' expected", _parser.getCurrentLocation());
        }
    }
}
