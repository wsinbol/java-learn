package com.example.demo.controller;

import com.example.demo.component.LibraryProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/config")
public class ReadConfigController {
    private final LibraryProperties libraryProperties;

    public ReadConfigController(LibraryProperties libraryProperties) {
        this.libraryProperties = libraryProperties;
    }


    /*
    Config读取顺序：

        1、config/application.properties（项目根目录中config目录下）

        2、config/application.yml

        3、application.properties（项目根目录下）

        4、application.yml

        5、resources/config/application.properties（项目resources目录中config目录下）

        6、resources/config/application.yml

        7、resources/application.properties（项目的resources目录下）

        8、resources/application.yml

     */
    @GetMapping(value = "/read")
    public String read(){
        return libraryProperties.getName();

//        List<LibraryProperties.Book> books = libraryProperties.getBooks();
//        System.out.println(books);
//        return books;
    }
}
