package com.example.bakhtiyar.overviewallfirebase;

/**
 * Created by Bakhtiyar on 12/7/2016.
 */
public class Data {

    String name, fathername;

    double salary;

    String id;

    String picUrl;

    boolean flag;

    public Data(String name, String fathername, double salary, String id, String picUrl, boolean flag) {
        this.name = name;
        this.fathername = fathername;
        this.salary = salary;
        this.id = id;
        this.picUrl = picUrl;
        this.flag = flag;
    }

    public Data() {
    }

    public String getName() {
        return name;
    }

    public String getFathername() {
        return fathername;
    }

    public double getSalary() {
        return salary;
    }

    public String getId() {
        return id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public boolean isFlag() {
        return flag;
    }
}
