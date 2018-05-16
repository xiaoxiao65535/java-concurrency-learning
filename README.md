# java-concurrency-learning
Java并发编程实践

> CAS底层原理？
    
    compare and set
    取出当前值和底层值对比，如果相同，执行逻辑。如果不同，继续取出当前值和底层值对比。

> AtomicLong 和 LongAdder的优劣对比

    1. 更关心数据一致性请选用AtomicLong（LongAdder有可能会产生偏差）
    2. 线程高竞争下请选用LongAdder（性能很好）
    
> AtomicStampReference

    解决CAS ABA问题
    ABA问题：某个值曾经被修改过，又修改回来，而其他线程不知道该值被修改过
    解决方式：增加版本号，每次值被修改，版本+1
    
> AtomicBoolean

    实用场景：某段代码只需要执行一次

### 原子性（锁）
> synchronized

    依赖JVM
    synchronized只作用于当前类，子类不会起作用
    修饰代码块：大括号括起来的代码，作用于【调用对象】
    修饰方法: 整个方法，作用域【调用对象】
    修饰静态方法：整个静态方法，作用于【所有对象】
    修饰类：括号括起来的部分，作用于【所有对象】
    
> Lock

    依赖特殊的CPU指令，代码实现，ReentrantLock
    
> synchronized与Lock对比？

    synchronized：不可中断锁，不适合竞争激烈的场景，可读性好
    Lock：可中断锁，多样化同步，竞争激烈的场景下可维持常态
    Atomic：性能比Lock更好，但是只能维护一个值
    
### 可见性
> synchronized

    JMM关于synchronized的两条规定
    1. 线程解锁前，必须把最新的共享变量的值刷新到主内存
    2. 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时
    需要从主内存中读取共享变量最新的值（注意，加锁与解锁是同一把锁）
    
> volatail

    通过加入内存屏障和禁止重排序优化来实现
    volatile不具备原子性，不保证线程安全
    使用场景：状态值，检查两次。
    1. 对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量刷新到主内存中
    2. 对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取最新的共享变量值到工作内存
    
> final

### 有序性

