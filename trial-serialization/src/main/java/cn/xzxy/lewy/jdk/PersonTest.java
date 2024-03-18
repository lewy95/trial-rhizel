package cn.xzxy.lewy.jdk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * JDK 原生序列化测试
 */
public class PersonTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setAge(31);
        person.setName("Robert");

        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("trial-serialization/data/person"));
        oos.writeObject(person);
        oos.flush();
        oos.close();
        System.out.println("Serialize OK");

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("trial-serialization/data/person"));
        Person person2 = (Person) ois.readObject();
        System.out.println(person2);

    }
}
