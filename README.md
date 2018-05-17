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
    
    1. 修饰类：类不能被继承，类中的所有成员方法隐式指定为final
    2. 修饰方法：不能被重写，效率高（最近的JDK不需要）。
    3. 修饰变量： 只能被赋值一次
                基本数据类型（值不变）
                引用数据类型（指向地址值不变，对象的内部值可变）

### 有序性


### 安全发布对象的四种方法

    1. 在静态初始化函数中初始化一个对象引用
    2. 将对象的引用保存到volatile类型域或者AtomicReference对象中
    3. 将对象的引用保存到某个正确构造对象的final类型域中
    4. 将对象的引用保存到一个由锁保护的域中
    
### 不可变对象（避免并发下 线程安全）

    1. 对象是被正确创建的（没有发生this逃逸）
    2. 对象一经创建，其状态不能修改
    3. 对象所有的域都是final类型
    tip1：参考String类
        1. 声明类为final
        2. 所有的成员私有
        3. 变量不提供set方法，对所有可变成员声明为final，只能赋值一次
        4. 通过构造器初始化所有成员，进行深度拷贝
        5. get方法中不返回对象本省，返回对象的拷贝
    tip2：
        Collections.unmodifiableXXX 处理之后对象的值不能修改

### 线程封闭（避免并发下 线程安全）

    堆栈封闭：局部变量，无并发问题
    ThreadLocal线程封闭：特别好的线程封闭方式
    
### StringBuffer和StringBuilder

    StringBuffer的方法都加上了synchronized，所以是线程安全的
    StringBuilder线程不安全，如果StringBuilder是定义在方法内部，属于堆栈封闭，此时应该选择StringBuilder提升性能
    

