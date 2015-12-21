package com.itechart.app.entity.helpers;

/**
 * Created by Maxim on 12/20/2015.
 */
public class Template {

    private String id;
    private String name;
    private String structure;

    public Template(String id, String name, String structure) {
        this.id = id;
        this.name = name;
        this.structure = structure;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }


}
