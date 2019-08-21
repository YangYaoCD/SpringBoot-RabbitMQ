package com.yangyao.rabbit.model;

import lombok.Data;

@Data
public class Book {
    private String bName;
    private String writer;

    public Book(String bName, String writer) {
        this.bName = bName;
        this.writer = writer;
    }

    public Book() {
    }
}
