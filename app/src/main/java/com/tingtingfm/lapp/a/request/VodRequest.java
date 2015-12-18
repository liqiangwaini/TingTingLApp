package com.tingtingfm.lapp.a.request;

/**
 * Created by lenovo on 2015/12/10.
 */
public class VodRequest extends BaseRequest {
    private int limit = 20;
    private int did = 375;
    private String order = "new";
    private int page = 1;

    public VodRequest(String url) {
        super(url);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
