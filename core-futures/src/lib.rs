#![allow(non_snake_case)]

use std::sync::{Arc, mpsc};
use std::thread;
use jni::JNIEnv;
use jni::objects::{JClass, JMethodID, JObject};
use jni::signature::{Primitive, ReturnType};
use jni::sys::jobject;

/*
 * section: Java_org_cadium_impl_futures_FutureExecutorImpl
 */

#[no_mangle]
pub extern "system" fn Java_org_cadium_impl_futures_FutureExecutorImpl_runAsynchronous(env: JNIEnv, _class: JClass, j_runnable: JObject) {
    let runnable_class = env.find_class("java/lang/Runnable").unwrap();
    let method_id = env.get_method_id(runnable_class, "run", ReturnType::Primitive(Primitive::Void).to_string()).unwrap();
    thread::spawn(move || {
        env.call_method_unchecked(j_runnable, method_id, ReturnType::Primitive(Primitive::Void), &[]).unwrap();
    });
}

#[no_mangle]
pub extern "system" fn Java_org_cadium_impl_futures_FutureExecutorImpl_run(env: JNIEnv, _class: JClass, handler: JObject) -> jobject {
    let runnable_class = env.find_class("org/cadium/base/futures/FutureValue").unwrap();
    let method_id = env.get_method_id(runnable_class, "yield", ReturnType::Object.to_string()).unwrap();
    let channel = mpsc::channel();
    channel.0.send(Arc::new((method_id, handler))).unwrap();
    *thread::spawn(move || {
        let received: (JMethodID, JObject) = mpsc::channel().1.recv().unwrap().clone();
        Arc::new(env.call_method_unchecked(received.1, received.0, ReturnType::Object, &[]).unwrap().l().unwrap().into_raw())
    }).join().unwrap().clone()
}