package com.tikal.utils;

/**
 * Created by Sopher on 21/03/2017.
 */
public class OperationSystemDetermination {

    public final static String WINDOWS = "Windows";
    public final static String LINUX = "Linux";


    public static String getOperationSystem() {
        return System.getProperty("os.name");
    }
}
