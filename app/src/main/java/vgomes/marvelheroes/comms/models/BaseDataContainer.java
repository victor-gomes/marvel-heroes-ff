package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 27/06/17.
 */

public class BaseDataContainer<T> {

    @Expose
    Integer offset;
    @Expose
    Integer limit;
    @Expose
    Integer total;
    @Expose
    Integer count;
    @Expose
    T[] results;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T[] getResults() {
        return results;
    }

    public void setResults(T[] results) {
        this.results = results;
    }
}
