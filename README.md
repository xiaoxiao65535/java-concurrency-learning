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