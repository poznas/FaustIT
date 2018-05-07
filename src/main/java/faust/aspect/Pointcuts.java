package faust.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* faust.controller.*.*(..))")
    public static void forControllers() {
    }

    @Pointcut("execution(* faust.dao.*.*(..))")
    public static void forDAO() {
    }

    @Pointcut("execution(* faust.service.*.*(..))")
    public static void forServices() {
    }

    @Pointcut("forControllers() || forDAO() || forServices()")
    public static void logging() {
    }
}
