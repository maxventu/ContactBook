package com.itechart.app.controller.helpers;

import java.util.ArrayList;

/**
 * Created by Maxim on 12/16/2015.
 */
public class EntityHelper {
    protected static ArrayList<Integer> getIntListFromArray(String[] array){
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(array!=null)
        for(int i=0;i<array.length;i++)
            list.add(Integer.parseInt(array[i]));
        return list;
    }
}
