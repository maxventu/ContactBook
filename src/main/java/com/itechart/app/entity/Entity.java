package com.itechart.app.entity;

import java.io.Serializable;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Entity implements Serializable,Cloneable {

    private Integer id;

    public Entity(){
    }

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
