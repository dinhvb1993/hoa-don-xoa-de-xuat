package com.simple_official_spring_template.api.output;

import com.simple_official_spring_template.utils.Data;

public class PictureImgurOutput {
    private String id;
    private String link;
    private Data data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
