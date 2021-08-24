package com.example.demo.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class WorldMap {

    @CsvBindByPosition(position = 0)
    @CsvBindByName (column = "ID_Mask")
    private static int  ID_Mask=1;


    @CsvBindByPosition(position = 1)
    @CsvBindByName (column = "ID")
    private long ID;


    @CsvBindByPosition(position = 2)
    @CsvBindByName (column = "City")
    private String City;


    @CsvBindByPosition(position = 3)
    @CsvBindByName (column = "Country")
    private String Country;


    @CsvBindByPosition(position = 4)
    @CsvBindByName (column = "Phonecode")
    private int Phonecode;


    @CsvBindByPosition(position = 5)
    @CsvBindByName (column = "Capital")
    private String Capital;


    @CsvBindByPosition(position = 6)
    @CsvBindByName (column = "Population")
    private int Population;


    public WorldMap(){

    }
    public WorldMap(String city, String country, int phonecode, String capital, int population) {
        ID = ID_Mask;
        ID_Mask++;
        City = city;
        Country = country;
        Phonecode = phonecode;
        Capital = capital;
        Population = population;
    }

    public static int getID_Mask() {
        return ID_Mask;
    }

    public static void setID_Mask(int ID_Mask) {
        WorldMap.ID_Mask = ID_Mask;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getPhonecode() {
        return Phonecode;
    }

    public void setPhonecode(int phonecode) {
        Phonecode = phonecode;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }
}
