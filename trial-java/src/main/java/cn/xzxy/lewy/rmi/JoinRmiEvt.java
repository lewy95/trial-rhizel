package cn.xzxy.lewy.rmi;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * RMI 事件类，要求事件 Remote 接口
 */
public class JoinRmiEvt implements Remote, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public JoinRmiEvt(Long id, String name) {
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
}