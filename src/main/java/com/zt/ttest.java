package com.zt;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class ttest {
    public static void main(String[] args0) throws IOException {
        Phone p = new Phone();
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.submit(p);
        executor.shutdown();
//        p.getPhoneAddr();
    }


}
