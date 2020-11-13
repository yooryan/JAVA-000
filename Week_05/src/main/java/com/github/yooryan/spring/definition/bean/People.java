package com.github.yooryan.spring.definition.bean;

import javax.annotation.PostConstruct;

/**
 * @author linyunrui
 */
public class People {

    private Long id;

    private String name;

    public People() {
    }

    public People(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
