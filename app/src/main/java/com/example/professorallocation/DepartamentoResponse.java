package com.example.professorallocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"allocations"})
public class DepartamentoResponse {
    private int id;
    private String name;
    private String string;
    //private List<Allocation> allocationList;


    public DepartamentoResponse() {
    }

    public DepartamentoResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "DepartamentoResponse{}";
    }
}


