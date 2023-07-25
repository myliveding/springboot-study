package com.example.springbootstudy.properties;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author dingzr
 */
@Data
@Component
@ConfigurationProperties(prefix = "config-listen")
public class ConfigListen {

    private String name;

    @NotNull
    private List<String> nameList;


    @NotNull
    private List<String> nameIdList;

    @NotNull
    private Set<String> nameSet;

}
