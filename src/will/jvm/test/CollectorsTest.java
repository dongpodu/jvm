package will.jvm.test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 *
 * -XX:+UseSerialGC: Serial+Serial Old(java 8之前);Copy+MarkSweepCompact(java8以及java8之后)
 * -XX:+UseParNewGC: ParNew+Serial Old(java 8之前);Serial+MarkSweepCompact(java8以及java8之后)
 * -XX:+UseConcMarkSweepGC: ParNew+ConcurrentMarkSweep(Serial Old作为CMS的后备，在发生Concurrent Mode Failure时使用)
 * -XX:+UseParallelGC: PS Scavenge+PS MarkSweep
 * -XX:+UseParallelOldGC: PS Scavenge+PS Old(java 8之前);PS Scavenge+PS MarkSweep(java8以及java8之后)
 * -XX:+UseG1GC: G1
 */
public class CollectorsTest {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
        while (true){

        }
    }

}
