package cn.xzxy.lewy.hessian;

import cn.xzxy.lewy.json.Player;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestHessian {
    public static void main(String[] args) throws Exception {
        // 1. 序列化
        Player player = new Player(1, "Lewy", 33);
        byte[] serializeBytes = serialize(player);
        System.out.println("Hessian序列化后字节数组长度：" + serializeBytes.length);

        // 2. 反序列化
        Player deserializeZhuZi = deserialize(serializeBytes);
        System.out.println(deserializeZhuZi.toString());
    }

    /**
     * 序列化方法
     *
     * @param player 需要序列化的对象
     * @return 序列化后生成的字节流
     */
    private static byte[] serialize(Player player) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output h2o = new Hessian2Output(bos);
        h2o.writeObject(player);
        h2o.close();
        return bos.toByteArray();
    }

    /**
     * 反序列化方法
     *
     * @param bytes 字节序列（字节流）
     * @return 实体类对象
     */
    private static Player deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Hessian2Input h2i = new Hessian2Input(bis);
        Player player = (Player) h2i.readObject();
        h2i.close();
        return player;
    }
}
