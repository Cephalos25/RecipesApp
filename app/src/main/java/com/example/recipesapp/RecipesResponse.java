package com.example.recipesapp;

import java.util.List;

public class RecipesResponse {
    private String title;
    private double version;
    private String href;
    private List<Recipe> results;

    public RecipesResponse(String title, double version, String href, List<Recipe> results) {
        this.title = title;
        this.version = version;
        this.href = href;
        this.results = results;
    }

    public RecipesResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
