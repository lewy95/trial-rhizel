package cn.xzxy.lewy.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Kryo 序列化简单测试
 */
public class TestSimple {
    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setId(100);
        mail.setTitle("School Notice");
        mail.setContent("yy");
        mail.setCreatedTime(new Date());

        // 创建 Kryo 对象
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false); // 将默认类的自动注册功能关闭（默认会将类全名序列化）

        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 构建字节数组输出流（内置可扩容数组）
        Output output = new Output(bos);
        kryo.writeObject(output, mail);
        output.close();
        System.out.println("Serialize OK，array.length：" + bos.toByteArray().length);

        // 反序列化
        byte[] data = bos.toByteArray();
        Input input = new Input(data);
        Mail mail2 = kryo.readObject(input, Mail.class);
        input.close();
        System.out.println(mail2);
    }
}
