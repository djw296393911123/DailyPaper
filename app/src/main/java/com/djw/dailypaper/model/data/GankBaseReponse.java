package com.djw.dailypaper.model.data;

/**
 * Created by JasonDong on 2017/3/21.
 */

public class GankBaseReponse<T> {
    private boolean error;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
