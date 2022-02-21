package com.example.demo.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "my-profile")
@Data
public class LibraryProperties {
    private String name;

}
