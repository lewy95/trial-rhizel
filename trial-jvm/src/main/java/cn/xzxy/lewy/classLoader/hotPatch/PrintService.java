package cn.xzxy.lewy.classLoader.hotPatch;

/**
 * 这是源文件，区别于补丁文件
 * 补丁文件位于 D:\workSets\javaSets\trial-rhizel\trial-jvm\data\patchClasses
 */
public class PrintService {
    public void printName(String name) {
        System.out.println("print, " + name);
    }
}
