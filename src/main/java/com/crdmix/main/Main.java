package com.crdmix.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.crdmix.config.AppConfiguration;

public class Main {

    private static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    public static void main(String... args) throws InterruptedException {
        new Main().start();
    }

    public void start() {
        ctx.register(AppConfiguration.class);
        ctx.refresh();
    }

    public static AnnotationConfigApplicationContext getCtx() {
        return ctx;
    }

    public static void setCtx(AnnotationConfigApplicationContext ctx) {
        Main.ctx = ctx;
    }

}
