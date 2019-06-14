package com.demo;

import java.io.Serializable;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-12 9:03 AM
 * @since v8.0
 **/
public class Order implements Serializable {
    private static final long serialVersionUID = -4854630176295264326L;
    private Integer id;
    private String name;

    public Order() {

    }

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
