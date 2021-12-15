#include <jni.h>
#include"cn_xzxy_lewy_jni_HelloJNICpp.h" // 注意因为在同目录引用要用引号引入
#include"HelloJNI_Cpp_Impl.h"

JNIEXPORT void JNICALL Java_cn_xzxy_lewy_jni_HelloJNICpp_sayHello(JNIEnv * jni, jobject thisObj){
    sayHello();  // invoke C++ function
}
