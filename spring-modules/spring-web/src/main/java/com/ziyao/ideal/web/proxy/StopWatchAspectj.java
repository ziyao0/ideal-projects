package com.ziyao.ideal.web.proxy;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.metrics.StopWatches;
import com.ziyao.ideal.core.metrics.Watch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author ziyao zhang
 */
@Aspect
public class StopWatchAspectj {

    private final StopWatchConfig config;

    public StopWatchAspectj(StopWatchConfig config) {
        this.config = config;
    }

    @Around(value = "@annotation(watch)")
    public Object around(ProceedingJoinPoint point, Watch watch) throws Throwable {

        if (!config.isEnabled()) {
            return point.proceed();
        }

        if (config.getUniqCodes().stream()
                .anyMatch(uniq -> uniq.equals(watch.value()))) {
            try {
                StopWatches.enabled(Strings.isEmpty(
                        watch.description()) ? watch.value() : watch.description());

                StopWatches.start("Total time spent");
                return point.proceed();
            } finally {
                StopWatches.stop("Total time spent");
                StopWatches.consolePrettyPrintByLogOrPrintln();
                StopWatches.disabled();
            }
        } else return point.proceed();
    }

    public static void main(String[] args) {
        StopWatches.enabled("Total time spent");
        StopWatches.start("Total time spent");
        StopWatches.stop("Total time spent");
        StopWatches.consolePrettyPrintByLogOrPrintln();
        StopWatches.disabled();

    }
}
