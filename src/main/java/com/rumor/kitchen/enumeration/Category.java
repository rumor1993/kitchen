package com.rumor.kitchen.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    KOREAN, CHINESE, JAPANESE, WESTERN, ARTICLE, POST, HOME;

    @JsonCreator
    public static Category fromString(String key) {
        for(Category action : Category.values()) {
            if(action.name().equalsIgnoreCase(key)) {
                return action;
            }
        }
        return null;
    }
}
