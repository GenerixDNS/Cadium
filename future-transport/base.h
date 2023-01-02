/*
 * Created by Alexander J. on 02.01.2023.
 */
#include <jni.h>
#include <future>
#include <iostream>
#include <any>
#include <vector>
#include <exception>

#ifndef FUTURE_TRANSPORT_BASE_H
#define FUTURE_TRANSPORT_BASE_H

#endif

typedef unsigned int JPointer;

/* returns future of the java unique_id */
std::future<std::any> * GET_BY_UNIQUE(JPointer pointer);

bool FLUSH_BY_UNIQUE_ID(JPointer pointer);

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    async
 * Signature: (ILorg/cadium/future/FutureTask;)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_async
        (JNIEnv *, jclass, jint, jobject);

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    collect
 * Signature: (I)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_cadium_future_FutureStandardExecutor_collect
        (JNIEnv *, jclass, jint);

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    flush
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_flush
        (JNIEnv *, jclass, jint);

/*
 * Class:     org_cadium_future_FutureStandardExecutor
 * Method:    wait
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_cadium_future_FutureStandardExecutor_wait
        (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
