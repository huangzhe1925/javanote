package com.hz.javanote.profile;

import java.util.List;

public interface ProfilingService {

    public static String CSV_SEPARATOR = ",";

    public void logStepStatus(Step step, String... keys);

    public void logMessage(String message);

    public List<? extends StepStatus> loadStepStatus();

    public StringBuilder[] generateReports(List<? extends StepStatus> stepStatusList);

    public void generateReports();

    public void storeReports(StringBuilder[] reports);

    public void backupLogs();

} 
