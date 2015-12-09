package com.itechart.app.entity;

import java.io.Serializable;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Entity <T> implements Serializable,Cloneable {

    private T id;

    public Entity(){
    }

    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
