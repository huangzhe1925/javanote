/* **********************************************************
 * StepStatusSummary.java
 *
 * Copyright (C) 2017 VMware, Inc.
 * All Rights Reserved
 * **********************************************************/
package com.hz.javanote.profile.lcm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hz.javanote.profile.ProfilingService;

/**
 * A POJO class for summary report of profiling LCM
 */
public class LCMStepStatusSummary {

    public static final String RESULT_PREFIX = "-----";
    private LCMStep step = LCMStep.UNKNOWN;
    private List<LCMStepStatus> stepStatusList;
    private List<LCMStepStatusSummary> childSummaryList;
    private final static int STEP_MAX_LEVEL = 4;

    LCMStepStatusSummary() {
    }

    LCMStepStatusSummary(LCMStep step) {
        this.step = step;
    }

    public void addSubStepSummary(LCMStepStatusSummary childSummary) {
        getChildSummaryList().add(childSummary);
    }

    public LCMStep getStep() {
        return step;
    }

    public void setStep(LCMStep step) {
        this.step = step;
    }

    public List<LCMStepStatus> getStepStatusList() {
        if (this.stepStatusList == null) {
            this.stepStatusList = new ArrayList<>();
        }
        return stepStatusList;
    }

    public void setStepStatusList(List<LCMStepStatus> stepStatusList) {
        this.stepStatusList = stepStatusList;
    }

    public List<LCMStepStatusSummary> getChildSummaryList() {
        if (this.childSummaryList == null) {
            this.childSummaryList = new LinkedList<>();
        }
        return childSummaryList;
    }

    public void setChildSummaryList(List<LCMStepStatusSummary> childSummaryList) {
        this.childSummaryList = childSummaryList;
    }

    public static void getResult(StringBuilder summaryReport, LCMStepStatusSummary summaryHead, String hostKey, int level) {
        level++;
        for (LCMStepStatusSummary summary : summaryHead.getChildSummaryList()) {
            for (LCMStepStatus stepStatus : summary.getStepStatusList()) {
                if (hostKey.trim().isEmpty() || hostKey.equals(stepStatus.getHostKey())) {
//                    String prefix = StringUtils.repeat(RESULT_PREFIX, ProfilingService.CSV_SEPARATOR, level);
//                    summaryReport.append(prefix)
//                            .append(prefix.isEmpty() ? StringUtils.EMPTY : ProfilingService.CSV_SEPARATOR)
//                            .append(stepStatus.getStep().name())
//                            .append(StringUtils.repeat(ProfilingService.CSV_SEPARATOR, STEP_MAX_LEVEL - level))
//                            .append(stepStatus.getHostKey())
//                            .append(ProfilingService.CSV_SEPARATOR)
//                            .append(stepStatus.getComponentKey())
//                            .append(ProfilingService.CSV_SEPARATOR)
//                            .append(stepStatus.getStepDuration())
//                            .append(StringUtils.LF);
                    getResult(summaryReport, summary, stepStatus.getHostKey(), level);
                }
            }
        }
    }
}

