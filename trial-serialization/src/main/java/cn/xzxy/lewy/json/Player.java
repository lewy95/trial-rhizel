package cn.xzxy.lewy.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Integer id;
    private String name;
    private Integer age;
}