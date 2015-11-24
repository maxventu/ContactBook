package com.itechart.app.Entities;

import java.io.Serializable;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Entity implements Serializable,Cloneable {
    public Entity(){
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

}
