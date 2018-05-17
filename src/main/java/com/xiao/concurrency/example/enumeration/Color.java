package com.xiao.concurrency.example.enumeration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public enum Color {

    a(1, 2, 3), b(2, 3, 4), c(4, 5, 6);

    @Getter
    private Object object;

    @Getter
    @Setter
    private int aa;

    @Getter
    @Setter
    private int bb;

    @Getter
    @Setter
    private int cc;

    private Color(int aa, int bb, int cc) {
        this.aa = aa;
        this.bb = bb;
        this.cc = cc;
        object = new Object();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("{}", Color.a.getObject().hashCode());
            }
        });
        executorService.shutdown();
    }

}
