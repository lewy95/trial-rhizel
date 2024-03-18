package cn.xzxy.lewy.jdk;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1500208610485192912L;

    private String name;
    private int age;

}
