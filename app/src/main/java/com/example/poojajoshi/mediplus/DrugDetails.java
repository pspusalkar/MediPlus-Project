package com.example.poojajoshi.mediplus;

public class DrugDetails {
    //private variables
    int drug_id;
    String drug_name;
    String drug_description;
    String drug_price;

    // Empty constructor
    public DrugDetails(){

    }

    // constructor
    public DrugDetails(int id, String name, String description, String price){
        this.drug_id = id;
        this.drug_name = name;
        this.drug_description = description;
        this.drug_price = price;
    }

    // constructor
    public DrugDetails(String name, String description, String price){
        this.drug_name = name;
        this.drug_description = description;
        this.drug_price = price;
    }

    // getting ID
    public int getID(){
        return this.drug_id;
    }

    // setting id
    public void setID(int id){
        this.drug_id = id;
    }

    // getting name
    public String getDrug_name(){
        return this.drug_name;
    }

    // setting name
    public void setDrug_name(String name){
        this.drug_name = name;
    }

    // getting drug description
    public String getDrug_description(){
        return this.drug_description;
    }

    // setting drug description
    public void setDrug_description(String description){
        this.drug_description = description;
    }

    // getting drug price
    public String getDrug_price(){
        return this.drug_price;
    }

    // setting drug price
    public void setDrug_price(String price){
        this.drug_price = price;
    }
}
