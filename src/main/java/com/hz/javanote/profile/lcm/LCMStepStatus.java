/* **********************************************************
 * StepStatus.java
 *
 * Copyright (C) 2017 VMware, Inc.
 * All Rights Reserved
 * **********************************************************/
package com.hz.javanote.profile.lcm;


import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

import com.hz.javanote.profile.StepStatus;

/**
 * A POJO class for logging of LCM profiling service.
 */
public class LCMStepStatus implements StepStatus,Comparable<LCMStepStatus> {

    private LCMStep step = LCMStep.UNKNOWN;
    private String stepDescription;
    private String componentKey = "";
    private String hostKey = "";
    private Long minTimeStamp;
    private Long maxTimeStamp;

    LCMStepStatus(LCMStep step, String stepDescription, String componentKey, String hostKey, long timeStamp) {
        this.step = step;
        this.stepDescription = stepDescription;
        this.hostKey = hostKey;
        this.componentKey = componentKey;
        this.setMinTimeStamp(timeStamp);
        this.setMaxTimeStamp(timeStamp);
    }

    LCMStepStatus(LCMStep step) {
        this.step = step;
    }

    @Override
    public String getStepDuration() {
        long durationMills = this.getMaxTimeStamp() - this.getMaxTimeStamp();
        long durationInDay = TimeUnit.MILLISECONDS.toDays(durationMills);
        long durationInHour = TimeUnit.MILLISECONDS.toHours(durationMills) - TimeUnit.DAYS.toHours(durationInDay);
        long durationInMins = TimeUnit.MILLISECONDS.toMinutes(durationMills) - TimeUnit.DAYS.toMinutes(durationInDay) - TimeUnit.HOURS.toMinutes(durationInHour);
        long durationInSecs = TimeUnit.MILLISECONDS.toSeconds(durationMills) - TimeUnit.DAYS.toSeconds(durationInDay) - TimeUnit.HOURS.toSeconds(durationInHour) - TimeUnit.MINUTES.toSeconds(durationInMins);
        long durationInMillSecond = durationMills - TimeUnit.DAYS.toMillis(durationInDay) - TimeUnit.HOURS.toMillis(durationInHour) - TimeUnit.MINUTES.toMillis(durationInMins) - TimeUnit.SECONDS.toMillis(durationInSecs);

        StringBuilder durationString = new StringBuilder();
        durationString.append((durationInDay > 0) ? durationInDay + "days " : "");
        durationString.append((durationInHour > 0) ? durationInHour + "hours " : "");
        durationString.append((durationInMins > 0) ? durationInMins + "mins " : "");
        durationString.append((durationInSecs > 0) ? durationInSecs + "s " : "");
        durationString.append((durationInMillSecond >= 0) ? durationInMillSecond + "ms" : "");
        return durationString.toString();
    }

    public LCMStep getStep() {
        return step;
    }

    public void setStep(LCMStep step) {
        this.step = step;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public String getComponentKey() {
        return componentKey;
    }

    public void setComponentKey(String componentKey) {
        this.componentKey = componentKey;
    }

    public String getHostKey() {
        return hostKey;
    }

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
    }

    public Long getMinTimeStamp() {
        if (minTimeStamp == null) {
            minTimeStamp = 0L;
        }
        return minTimeStamp;
    }

    public void setMinTimeStamp(Long minTimeStamp) {
        this.minTimeStamp = minTimeStamp;
    }

    public Long getMaxTimeStamp() {
        if (maxTimeStamp == null) {
            maxTimeStamp = 0L;
        }
        return maxTimeStamp;
    }

    public void setMaxTimeStamp(Long maxTimeStamp) {
        this.maxTimeStamp = maxTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LCMStepStatus that = (LCMStepStatus) o;
        return getStep() == that.getStep() &&
                Objects.equals(getComponentKey(), that.getComponentKey()) &&
                Objects.equals(getHostKey(), that.getHostKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStep(), getComponentKey(), getHostKey());
    }

    @Override
    public int compareTo(LCMStepStatus other) {
        Integer ss1Index = this.getStep().ordinal();
        Integer ss2Index = other.getStep().ordinal();
        return ss1Index.compareTo(ss2Index);
    }
}