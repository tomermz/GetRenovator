package com.example.getrenovator.Models;

public abstract class FlagClass {

    public static boolean flag;

    public FlagClass() {
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flagc) {
        flag = flagc;
    }
}
