

# Activity生命周期

问题1:onStart()和onResume(),onPause()和onStop(),有什么实质的不同.
onStart()和onStop()是从Activity是否可见这个角度来回调的,
而onResume()onPause()是从Activity是否位于前台(是否可交互)这个角度回调的,其他就没有什么实质的差别的.

问题2:假设当前Activity为A,如果这时用户打开一个新的ActivityB,那么B的onResume和A的onPause()那个先执行?
旧的Activity先onPause(),然后新的Activity再启动.

# Activity的启动模式

任务栈和taskAffinity

1. standard:

2. singleTop:   

onNewIntent()

3. singleTask:

onNewIntent():

4. singleInstance:


# IPC简介

IPC是Inter-Process Communication的缩写,含义是进程间通信或者跨进程通信,是指两个进程间进行数据交换的过程.

Window的IPC,可以通过剪切板,管道,邮槽进行进程间通讯.
Linux上可以通过命令管道,共享内存,信号量等来进行进程间通讯.
Android中使用Binder和Socket来进行进程间通讯.

同一个app内部的多进程,会出现的问题:
1. 静态成员和单例模式完全失效
2. 线程同步机制完全失效
3. SharePreferences的可靠性下降
4. Application会多创建.

这是因为,当一个组件跑在一个新的进程中的时候,由于系统要在创建新的进程同时分配独立的虚拟机,所以这个过程其实就是
启动一个应用过程.

# Binder介绍

Binder是Android的一个类,它实现了IBinder接口.从IPC角度来说,Binder是Android的一种跨进程通信方式,Binder还可以理解为一种虚拟的物理设备.
从AndroidFramework角度来说,Binder是ServiceManager链接各种Manager(ActivityManager,WindowManager)和ManagerService的桥梁.
从Android应用层来说,Binder是客户端和服务端进行通讯的媒介,当bindService的时候,服务端返回一个包含了服务端业务调用的Binder对象,

| 名称 | 优点 | 缺点 | 适用场景
| :--------: |:-------------:| :-----:| :---:|
| **Bundle** | 简单易用　| 只能Bundle支持的数据类型 | 四大组件间的进程通信
| **文件共享** | 简单易用 | 不适合高并发场景，并且无法做到进程间的即时通讯 | 无并发访问情形，交换简单的数据实时性不高的场景
| **AIDL** | 功能强大，支持一对多并发通讯，支持实时通讯 | 适用稍复杂，需要处理好线程同步 | 一对多通讯且有RPC(远程过程调用协议)需求
| **Messager**|功能一般，支持一对多串行通信，支持实时通讯|不能很好的处理高并发情形，不支持RPC，数据通过Message进行传输，因此只能传输Bundle支持的数据类型|低并发的一对多即时通讯，无RPC需求，或者无需要返回结果的RPC需求
|**ContentProvider**|在数据源访问方面功能强大，支持一对多并发数据共享，可通过Call方法扩展其他操作|可以理解为受约束的AIDL，只要提供数据源的CRUD操作|一对多进程间的数据共享
|**Socket**|功能强大，可以通过网络传输字节流，支持一对多并发实时通讯|实现细节稍微麻烦，不支持直接RPC|网络数据交换

# View动画的方式

1. scrollTo/scrollBy,注意是对view内容的滑动
2. 动画
3. 改变布局参数

# View事件分发机制

1. dispatchTouchEvent(MotionEvent ev)
2. onInterceptTouchEvent(MotionEvent ev)
3. onTouchEvent(MotionEvent ev);

```java
public boolean dispatchTouchEvent(MotionEvent ev){
    boolean consume = false;
    if (onInterceptTouchEvent(ev){
        consume = onTouchEvent(ev);
    } else {
        consume = child.dispatchTouchEvent(ev);
    }
    return consume;
}
```

事件的传递顺序:
Activity -> Window ->View

Activity的dispatchTouchEvent 具体由Window来完成的, Window传给DecorView

# 理解Window和WindowManager

Andriod中所有的视图都是通过Window来呈现的,不管是Activity,Dialog还是Toast,他们都是附加在Window上的,因此Window是View的实际管理者.

WindowManager添加一个View的过程:
1. WindowManagerImpl -> WindowManagerGlobal -> addView
2. 创建ViewRootImpl -> setView()方法更新View -> WindowSession -> WindowManagerService处理．