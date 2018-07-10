package will.jvm.test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 *
 * -XX:+UseSerialGC: Serial+Serial Old
 * -XX:+UseParNewGC: ParNew+Serial Old
 * -XX:+UseConcMarkSweepGC: ParNew+CMS(Serial Old作为CMS的后备，在发生Concurrent Mode Failure时使用)
 * -XX:+UseParallelGC: PS Scavenge+PS MarkSweep
 * -XX:+UseParallelOldGC: PS Scavenge+PS Old
 */
public class CollectorsTest {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
    }

}
