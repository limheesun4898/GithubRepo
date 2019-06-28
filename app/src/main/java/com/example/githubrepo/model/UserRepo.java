package com.example.githubrepo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRepo {
    @SerializedName("name")
    public String name;

    public UserRepo(String name, String description, String language, String updated_at) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.updated_at = updated_at;
    }

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("language")
    @Expose
    public String language;

    @SerializedName("updated_at")
    public String updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
