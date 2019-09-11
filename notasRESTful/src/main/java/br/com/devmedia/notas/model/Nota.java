package br.com.devmedia.notas.model;

import com.owlike.genson.annotation.JsonIgnore;
import com.owlike.genson.annotation.JsonProperty;

public class Nota {

    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Body")
    private String body;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
