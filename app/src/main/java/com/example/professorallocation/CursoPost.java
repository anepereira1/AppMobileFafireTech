package com.example.professorallocation;

public class CursoPost{
    private String name;

    public CursoPost(String name) {
        this.name = name;
    }

    public CursoPost(CursoPost cursoPost) {
    }

    public CursoPost() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
