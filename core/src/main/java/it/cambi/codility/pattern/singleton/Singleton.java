package it.cambi.codility.pattern.singleton;

public class Singleton {

    private static Singleton singleton;

    private Singleton() {
        // private constructor for Singleton instance
    }

    public static synchronized Singleton load() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

}