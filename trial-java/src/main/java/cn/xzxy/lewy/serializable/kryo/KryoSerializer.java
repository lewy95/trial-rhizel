package cn.xzxy.lewy.serializable.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * 基于Kryo框架实现对象序列化和反序列化（解决线程不安全问题）
 * <p>
 * *******注意*******
 * kryo 对象是一个线程不安全对象，不允许多线程共享，因此需要每个线程都有一份。
 * 那如何保证每个线程此类的实例只有一份呢？可以借助ThreadLocal实现。
 * <p>
 * ThreadLocal 提供一种线程绑定机制，可以基于此对象将某个对象绑定当前线程中，也可以从当前线程获取某个对象。
 * set() 绑定  get() 获取
 */
public class KryoSerializer implements Serializer {

    // kryo 非线程安全的，每个线程都使用独立 kryo
    final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(ct, new BeanSerializer<>(kryo, ct));
            // 设置一：引用
            // 当对某个对象序列化时，默认情况下 kryo 会在每个成员对象第一次序列化时写入一个数字，
            // 该数字逻辑上就代表了对该成员对象的引用，如果后续有引用指向该成员对象，则直接序列化之前存入的数字即可，而不需要再次序列化对象本身。
            // 这种默认策略对于成员存在互相引用的情况较有利，否则就会造成空间浪费（因为没序列化一个成员对象，都多序列化一个数字），
            // 通常情况下可以将该策略关闭，kryo.setReferences(false);
            kryo.setReferences(false);
            // 设置二：设置是否注册全限定名
            kryo.setRegistrationRequired(false);
            // 设置三：设置初始化策略，如果没有默认无参构造器，那么就需要设置此项，使用此策略构造一个无参构造器
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };

    final ThreadLocal<Output> outputLocal = new ThreadLocal<>();
    final ThreadLocal<Input> inputLocal = new ThreadLocal<>();

    private Class<?> ct = null;

    public KryoSerializer(Class<?> ct) {
        this.ct = ct;
    }

    public Class<?> getCt() {
        return ct;
    }

    public void setCt(Class<?> ct) {
        this.ct = ct;
    }

    @Override
    public void serialize(Object obj, byte[] bytes) {
        // 构建处理流output对象
        Output output = getOutput(bytes);
        // 序列化数据
        kryoLocal.get().writeObjectOrNull(output, obj, obj.getClass());
        output.flush();
        output.close();
    }

    @Override
    public void serialize(Object obj, byte[] bytes, int offset, int count) {
        Output output = getOutput(bytes, offset, count);
        kryoLocal.get().writeObjectOrNull(output, obj, obj.getClass());
        output.flush();
        output.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, int offset, int count) {
        // 构建input对象，负责读字节数据
        Input input = getInput(bytes, offset, count);
        // 反序列化数据
        T t = (T) kryoLocal.get().readObjectOrNull(input, ct);
        input.close();
        return t;
    }

    @Override
    public <T> T deserialize(byte[] bytes) {
        return deserialize(bytes, 0, bytes.length);
    }

    private Output getOutput(byte[] bytes) {
        Output output = null;
        if ((output = outputLocal.get()) == null) {
            output = new Output();
            outputLocal.set(output);
        }
        if (bytes != null) {
            output.setBuffer(bytes);
        }
        return output;
    }

    private Output getOutput(byte[] bytes, int offset, int count) {
        Output output = null;
        if ((output = outputLocal.get()) == null) {
            output = new Output();
            outputLocal.set(output);
        }
        if (bytes != null) {
            output.writeBytes(bytes, offset, count);
        }
        return output;
    }

    private Input getInput(byte[] bytes, int offset, int count) {
        Input input = null;
        if ((input = inputLocal.get()) == null) {
            input = new Input();
            inputLocal.set(input);
        }
        if (bytes != null) {
            input.setBuffer(bytes, offset, count);
        }
        return input;
    }

}
