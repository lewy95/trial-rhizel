package cn.xzxy.lewy.serializable.kryo;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义邮件接收者类
 */
@Data
public class Receiver implements Serializable {
    private static final long serialVersionUID = 6599166688654111165L;
    private Integer id;
    private String name;

    public Receiver(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
