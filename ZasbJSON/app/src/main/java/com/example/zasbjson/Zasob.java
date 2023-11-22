package com.example.zasbjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zasob {

    public Info info;
    public HashMap<Integer, String> Grupa1;
}

class Info{
    Info info;
    public String przedmiot;
    public String prowadzacy;
    public String szkola;
    public String miasto;
    public String dataczas;

}
