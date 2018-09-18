package io.xmacedo.client.rest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonAnyProperties implements Cloneable, Serializable {

    private static final long serialVersionUID = 4086076323453427051L;

    protected Map<String, Object> other = new LinkedHashMap<>();

    public Object get(String name) {
        return other.get(name);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return other;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        other.put(name, value);
    }

    @Override
    public JsonAnyProperties clone() {
        JsonAnyProperties jsonAnyProperties = new JsonAnyProperties();

        if (other != null) {
            jsonAnyProperties.other.putAll(other);
        }

        return jsonAnyProperties;
    }

}
