package com.example.bijay.myapp_empblob;

import java.sql.Blob;

/**
 * Created by Bijay on 14-05-2016.
 */
public class Emp {
    private String eName;
    private int eAge;
    private Blob ePhoto;

    public Emp(){}

    public Emp(String ename, int eage){
        super();
        this.eName=ename;
        this.eAge=eage;
    }

    public String getEname(){return eName;}
    public void setEname(String ename){this.eName=ename;}
    public int getEage(){return eAge;}
    public void setEage(int eage) {this.eAge=eage;}
}
