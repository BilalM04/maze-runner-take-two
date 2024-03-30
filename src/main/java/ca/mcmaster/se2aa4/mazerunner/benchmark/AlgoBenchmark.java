package ca.mcmaster.se2aa4.mazerunner.benchmark;

import ca.mcmaster.se2aa4.mazerunner.configuration.Method;

public class AlgoBenchmark implements Benchmark {
    private Method method;
    private Method baseline;

    public AlgoBenchmark(Method method, Method baseline, String file) {

    }
    
    public String generateReport() {
        return "";
    }

    private double getLoadTime() {
        return 0;
    }

    private double getMethodTime() {
        return 0;
    }

    private double getBaselineTime() {
        return 0;
    }

    private double getSpeedup() {
        return 0;
    }
}
