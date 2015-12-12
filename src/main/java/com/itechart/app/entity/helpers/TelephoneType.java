package com.itechart.app.entity.helpers;

/**
 * Created by Maxim on 12/12/2015.
 */
public enum TelephoneType {

    MOB("Mobile"),
    WOR("Work"),
    HOM("Home"),
    WF("Work Fax"),
    HF("Home Fax"),
    PAG("Pager"),
    OTH("Other");

    private String value;

    public static TelephoneType get(String input) {

        for (TelephoneType tel : TelephoneType.values()) {
            if (tel.name().equals(input)) {
                return tel;
            }
        }
        return TelephoneType.MOB;
    }
    private TelephoneType(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
