package com.itechart.app.entity.helpers;

/**
 * Created by Maxim on 12/12/2015.
 */
public enum FamilyStatus {
    single("Single"),
    married("Married"),
    divorced("Divorced"),
    widow("Widow/Widower");

    private String value;

    private FamilyStatus(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
