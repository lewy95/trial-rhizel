package cn.xzxy.lewy.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * JMX 中有四种类型的 MBean：
 * 1.标准MBeans（Standard MBeans）设计和实现是最简单的，这类MBean使用自己的方法名作为管理接口；
 * 2.动态MBeans（Dynamic MBeans）动态MBean适用于描述那些要在运行时才可确切知道的数据结构的资源。
 *   与标准 MBean不同的是，动态MBean所暴露的管理接口不必事先确定，而是可根据运行时的情况，判断生成最终的管理接口。
 *   比如用以描述配置的MBean，可能需要通过对配置文件的分析来最终获得属性的名称和类型。
 * 3.开放MBeans（Open MBeans）属于动态MBeans，这类MBean依靠基础数据类型来实现通用管理，Open MBean是一类特殊的动态Mbean，
 *   它的特征是只针对特定对象，比如原始的数据类型(如int和float)和复合的数据类型。
 * 4.模型MBeans（Model MBeans）同样也是动态MBeans，这类MBeans是完全可配置的，在运行期间进行自我声明；
 *   与标准和动态MBean相比，你可以不用写MBean类，只需使用javax.management.modelmbean.RequiredModelMBean即可。
 *
 * 关于标准 MBean 实例的说明：
 * 标准 MBean 是最简单的 MBean 类型。通过标准 MBean 来管理一个 Java 对象需要以下步骤：
 * 1. 创建一个接口，命名规范为：Java类名 + MBean。如Java类为Greet，则需要创建GreetMBean接口；
 * 2. 创建Java类，使它实现刚创建的接口，命名规则为类名+MBean必须等于接口名；
 * 3. 获取MBeanServer，创建一个ObjectName来设定MBean的名称(name=MBean名)，然后将 MBean 注册到 MBeanServer。
 *
 * 关于查看 MBean 信息，可用使用 jconsole
 */
public class StandardMBeanMain {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(new Greet(), new ObjectName("MBeanTest:name=StandardMBean"));
        Thread.sleep(60 * 60 * 1000);
    }
}
