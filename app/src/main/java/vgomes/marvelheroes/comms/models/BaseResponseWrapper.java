package vgomes.marvelheroes.comms.models;

import com.google.gson.annotations.Expose;

/**
 * Created by victorgomes on 26/06/17.
 */

public class BaseResponseWrapper<T> {

    @Expose
    Integer code;
    @Expose
    String status;
    @Expose
    String copyright;
    @Expose
    String attributionText;
    @Expose
    String attributionHTML;
    @Expose
    String etag;
    @Expose
    BaseDataContainer<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public BaseDataContainer<T> getData() {
        return data;
    }

    public void setData(BaseDataContainer<T> data) {
        this.data = data;
    }
}
