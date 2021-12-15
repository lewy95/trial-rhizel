#include<jni.h>
#include<stdio.h>
#include"cn_xzxy_lewy_jni_HelloJNI.h" // 注意因为在同目录引用要用引号引入

JNIEXPORT void JNICALL Java_cn_xzxy_lewy_jni_HelloJNI_sayHello(JNIEnv * jni, jclass jclazz){
     printf("Hello JNI");    // 实现方法
}
