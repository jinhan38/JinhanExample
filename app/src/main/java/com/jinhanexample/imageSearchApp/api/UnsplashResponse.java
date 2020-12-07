package com.jinhanexample.imageSearchApp.api;

import com.jinhanexample.imageSearchApp.data.UnsplashPhoto;

import java.util.List;

public class UnsplashResponse {
    private List<UnsplashPhoto> results;

    public List<UnsplashPhoto> getResults() {
        return results;
    }

    public void setResults(List<UnsplashPhoto> results) {
        this.results = results;
    }
}
