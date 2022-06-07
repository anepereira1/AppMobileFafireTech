package com.example.professorallocation;

public class DepartamentoPost {
    private String name;

    public DepartamentoPost(String name) {
        this.name = name;
    }

    public DepartamentoPost(DepartamentoPost departamentoPost) {
    }

    public DepartamentoPost() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
}
