package cn.xzxy.lewy.jdk;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * JDK原生序列化 之 SerializeId
 * <p>
 * 当对象序列化完毕后，如果更改对象的结构，比如新增一个私有属性等，再次反序列化时会报错。
 * 因为当序列化时，会自动生成一个 SerializeId，反序列化时，会根据字节流返回的SerializeId来比较与序列化时的一致，
 * 如果更改了对象结构会导致两个 SerializeId 不一样，从而反序列化失败，只有同一次编译的 class 才具有同样的 SerializeId。
 * 所以解决办法是：手动加上SerializeId，使其保持不变
 * <p>***********************************************************************
 * JDK原生序列化 之 transient
 * <p>
 * 某些场景，对于某些字段我们不想序列化，出于安全的角度，比如密码。
 * 此时，就可以用 transient 关键字，在序列化时忽略此字段。
 */
public class PersonTest2 {

    public static void main(String[] args) throws Exception {
        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("trial-java/data/serializable/person"));
        Person person2 = (Person) ois.readObject();
        System.out.println(person2);
    }

}