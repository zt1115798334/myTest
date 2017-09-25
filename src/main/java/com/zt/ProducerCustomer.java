package com.zt;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangtong on 2017/7/4.
 */

class Factory {
    private int i = 0;
    private boolean created = false;
    List<String> filePath = Lists.newArrayList();

    public void traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        filePath.add(file2.getAbsolutePath());
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public synchronized void create() {
        while (created) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        i = i + 1;
        System.out.println(Thread.currentThread().getName() + "-create-" + i);
        this.created = true;
        notifyAll();
    }

    public synchronized void consume() {
        while (created) {
            System.out.println(Thread.currentThread().getName() + "-consume-" + i);
            this.created = false;
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 生产者与消费者的基类
 */
abstract class AbsFactory implements Runnable {
    protected Factory factory = null;

    public AbsFactory(Factory factory) {
        this.factory = factory;
    }

    abstract protected void execute();

    public void run() {
        while (true) {
            execute();
        }
    }
}

class Producer extends AbsFactory {
    public Producer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.create();
    }
}

class Consumer extends AbsFactory {
    public Consumer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.consume();
    }
}


public class ProducerCustomer {

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println("Usage:java ProducerCustomer number");
//            System.out.println("Please restart again....");
//            System.exit(0);
//        }
//        int count = 0;
//        try {
//            count = Integer.parseInt(args[0]);
//        } catch (Throwable t) {
//            System.out.println("Please enter a integer type number...");
//            System.exit(0);
//        }
        Factory factory = new Factory();
        List<String> filePathList = Arrays.asList("E:\\采集数据\\供应链金融语科\\供应链金融语科0701\\供应链金融语料样本-搜狗0701.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0701\\供应链金融语料样本-百度0701.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0702\\供应链金融语料样本-搜狗0702.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0702\\供应链金融语料样本-百度0702.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0703\\供应链金融语料样本-搜狗0703.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0703\\供应链金融语料样本-百度0703.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月29日\\供应链金融语料样本-搜狗_0629.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月29日\\供应链金融语料样本-百度_0629.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月30日\\供应链金融语料样本-搜狗.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月30日\\供应链金融语料样本-百度.xlsx");
        new Thread(new Producer(factory)).start();
        new Thread(new Consumer(factory)).start();
    }
}



