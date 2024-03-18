package cn.xzxy.lewy.kryo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestOfficial {
    public static void main(String[] args) throws IOException {

        KryoSerializer kryoSerializer = new KryoSerializer(Mail.class);
        byte[] bytes = new byte[200];

        Mail mail = new Mail();
        mail.setId(101);
        mail.setTitle("School Notice");
        mail.setContent("ym");
        mail.setCreatedTime(new Date());
        List<Receiver> receivers = new ArrayList<>();
        receivers.add(new Receiver(1, "Thomas"));
        receivers.add(new Receiver(2, "James"));
        mail.setReceivers(receivers);

        // 序列化
        kryoSerializer.serialize(mail, bytes);
        System.out.println(mail.toString());
        System.out.println("Serialize OK，array length：" + bytes.length);

        // 反序列化
        Mail mail2 = kryoSerializer.deserialize(bytes);
        System.out.println(mail2.toString());
        System.out.println("Deserialize OK，array length：" + bytes.length);
    }

}
