#调优命令
jps : 查看虚拟机进程，如jps -v
jmap : JVM Memory Map，用于查看java运行时内存，如jmap -heap 11222
jstat : JVM statistics Monitoring，是用于监视虚拟机运行时状态信息的命令，它可以显示出虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。
jstack : 用于生成java虚拟机当前时刻的线程快照
jinfo : JVM Configuration info 这个命令作用是实时查看和调整虚拟机运行参数

#调优参数
–XX:NewRatio 等于老年代/新生代，如-XX:NewRatio=2
–XX:NewSize  指定新生代初始内存大小，如-XX:NewSize=10M
–XX:MaxNewSize  指定新生代最大内存大小,如-XX:MaxNewSize=10M
-Xmn  等同于设置了相同的-XX:NewSize和-XX:MaxNewSize，如-Xmn20M
–XX:SurvivorRatio  等于eden/s0或eden/s1，如-XX:SurvivorRatio=1
-Xms 最小堆内存，如-Xms10m 
-Xmx 最大堆内存，如-Xmx10m

#垃圾回收器
-XX:+UseSerialGC Serial+Serial Old(java 8之前);Copy+MarkSweepCompact(java8以及java8之后)
-XX:+UseParNewGC ParNew+Serial Old(java 8之前);Serial+MarkSweepCompact(java8以及java8之后)
-XX:+UseConcMarkSweepGC ParNew+ConcurrentMarkSweep(Serial Old作为CMS的后备，在发生Concurrent Mode Failure时使用)
-XX:+UseParallelGC PS Scavenge+PS MarkSweep
-XX:+UseParallelOldGC PS Scavenge+PS Old(java 8之前);PS Scavenge+PS MarkSweep(java8以及java8之后)
-XX:+UseG1GC G1

#其他
-XX:+PrintCommandLineFlags -version  显示出JVM初始化完毕后所有跟最初的默认值不同的参数及它们的值。
-XX:+PrintFlagsInitial 显示所有可设置参数及默认值，可结合-XX:+PrintFlagsFinal对比设置前、设置后的差异，方便知道对那些参数做了调整。

#什么场景下会发生内存泄漏（即不再会被使用的对象的内存不能被回收）
现在有一个Product类代表一种产品，这个类被设计为不可扩展的，而此时我们想要为每个产品增加一个编号。一种解决方案是使用HashMap<Product, Integer>。
于是问题来了，如果我们已经不再需要一个Product对象存在于内存中（比如已经卖出了这件产品），假设指向它的引用为productA，我们这时会给productA赋值为null，
然而这时productA过去指向的Product对象并不会被回收，因为它显然还被HashMap引用着。所以这种情况下，我们想要真正的回收一个Product对象，仅仅把它的强引用赋值为null是不够的，
还要把相应的条目从HashMap中移除。显然“从HashMap中移除不再需要的条目”这个工作我们不想自己完成，我们希望告诉垃圾收集器：在只有HashMap中的key在引用着Product对象的情况下，就可以回收相应Product对象了。
显然，根据前面弱引用的定义，使用弱引用能帮助我们达成这个目的。我们只需要用一个指向Product对象的弱引用对象来作为HashMap中的key就可以了
