package com.petr.todo.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValidationErrors {
    /**
     * {
     *   "Item": [" no item with id: 21"],
     * }
     */
    private HashMap<String, ArrayList<String>> errors;

    public ValidationErrors() {
        this.errors = new HashMap<>();
    }

    public boolean isEmpty() {
        return this.errors.isEmpty();
    }

    public boolean hasErrors() {
        return !this.isEmpty();
    }

    public void addError(String field, String message) {
        // if(this.errors.containsKey(field)) {
        //     this.errors.get(field).add(message);
        // } else {
        //     ArrayList<String> newList = new ArrayList<>();
        //     newList.add(message);
        //     errors.put(field, newList);
        // }
        this.errors.computeIfAbsent(field, f -> new ArrayList<>()).add(message);
    }

    // returns unmodifiable map
    public Map<String, ArrayList<String>> getErrors() {
        return Collections.unmodifiableMap(this.errors);
    }
}
