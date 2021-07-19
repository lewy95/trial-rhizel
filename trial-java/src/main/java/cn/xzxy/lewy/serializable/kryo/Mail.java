package cn.xzxy.lewy.serializable.kryo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义邮件实体类
 */
@Data
public class Mail implements Serializable {
    private static final long serialVersionUID = 6599166688654530165L;
    private Integer id;
    private String title;
    private String content;
    private Date createdTime;
    private List<Receiver> receivers = new ArrayList<>();
}
