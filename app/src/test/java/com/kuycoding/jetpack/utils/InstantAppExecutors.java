package com.kuycoding.jetpack.utils;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;

public class InstantAppExecutors extends AppExecutors{
    private static Executor instant = Runnable::run;

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}