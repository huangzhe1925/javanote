/* **********************************************************
 * ProfilerService.java
 *
 * Copyright (C) 2017 VMware, Inc.
 * All Rights Reserved
 * **********************************************************/
package com.vce.commons.profiling.profile.lcm;

import com.vce.commons.core.util.CalendarUtil;
import com.vce.commons.core.util.FileSystemUtil;
import com.vce.commons.core.util.StringUtil;
import com.vce.commons.profiling.AbstractProfilingService;
import com.vce.commons.profiling.ProfilingService;
import com.vce.commons.profiling.Step;
import com.vce.commons.profiling.StepStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * A service class for profiling service of LCM
 * Function as log timestamp for pre-defined steps, and calculate step duration after LCM finished.
 * Currently only support LCM profiling
 */
@Service("lcmProfilingService")
public class LCMProfilingService extends AbstractProfilingService implements ProfilingService {

    private final Logger logger = LoggerFactory.getLogger(LCMProfilingService.class);

    private final String PROFILE_LOG_FILE_PATH = "/var/log/mystic/profile-lcm.log";

    @Value("${profiler.file.report.path:/var/log/mystic/profile-lcm-report.log}")
    private String PROFILE_REPORT_FILE_PATH;

    @Value("${profiler.file.report.summary.path:/var/log/mystic/profile-lcm-report-summary.log}")
    private String PROFILE_SUMMARY_REPORT_FILE_PATH;

    private String[] REPORT_LABEL_ARR = {"Step Index", "Step Name", "Step Description", "Component", "Host", "Duration", "Start Time", "End Time"};

    private int KEY_INPUT_COMPONENT = 0;
    private int KEY_INPUT_HOST = 1;
    private int KEY_INPUT_STEP_DESCRIPTION = 2;
    private int[] LCM_PROFILING_INPUT_KEYS = {KEY_INPUT_COMPONENT, KEY_INPUT_HOST, KEY_INPUT_STEP_DESCRIPTION};

    private int RECORD_KEY_DATE_TIME = 0;
    private int RECORD_KEY_THREAD = 1;
    private int RECORD_KEY_STEP = 2;
    private int RECORD_KEY_COMPONENT = 3;
    private int RECORD_KEY_HOST = 4;
    private int RECORD_KEY_STEP_DESCRIPTION = 5;
    private int RECORD_KEY_TIMESTAMP = 6;
    private int[] RECORD_KEYS_LCM_PROFILING= {RECORD_KEY_DATE_TIME, RECORD_KEY_THREAD, RECORD_KEY_STEP, RECORD_KEY_COMPONENT, RECORD_KEY_HOST, RECORD_KEY_STEP_DESCRIPTION, RECORD_KEY_TIMESTAMP};

    private int REPORT_DETAIL_INDEX = 0;
    private int REPORT_SUMMARY_INDEX = 1;
    private int[] REPORTS = {REPORT_DETAIL_INDEX, REPORT_SUMMARY_INDEX};

    /**
     * If found existing in the StepStatusList, only record its timestamp
     * If not found, add new StepStatus to StepStatusList.
     *
     * @param stepStatusList Existing List of StepStatus
     * @param stepStatus     new stepStatus that going to be added in StepStatusList
     */
    private void addStepStatus(List<LCMStepStatus> stepStatusList, LCMStepStatus stepStatus) {
        int index = stepStatusList.indexOf(stepStatus);
        if (index < 0) {
            stepStatusList.add(stepStatus);
        } else {
            LCMStepStatus existingStepStatus = stepStatusList.get(index);
            if (existingStepStatus.getMinTimeStamp() > stepStatus.getMinTimeStamp()) {
                existingStepStatus.setMinTimeStamp(stepStatus.getMinTimeStamp());
            }
            if (existingStepStatus.getMaxTimeStamp() < stepStatus.getMaxTimeStamp()) {
                existingStepStatus.setMaxTimeStamp(stepStatus.getMaxTimeStamp());
            }
        }
    }

    /**
     * Entry of reports generation, will be triggered if LCM upgrade acknowledge method be triggered.
     */
    @Override
    public void generateReports() {
        super.generateReports();
    }

    /**
     * Build StepStatusSummary extracted from detailed StepStatus.
     *
     * @return Summary of StepStatus in a tree format
     */
    private LCMStepStatusSummary buildStepStatusSummary() {
        LCMStepStatusSummary upgradePerformBatchUpgradeEnterMMSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_ENTER_MM);
        LCMStepStatusSummary upgradePerformBatchUpgradeFwStagingSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_FW_STAGING);
        LCMStepStatusSummary upgradePerformBatchUpgradePowerCycleSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_POWER_CYCLE);
        LCMStepStatusSummary upgradePerformBatchUpgradeInMmSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_IN_MM);
        LCMStepStatusSummary upgradePerformBatchExitMmSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_EXIT_MM);

        LCMStepStatusSummary upgradePerformBatchUpgradeUpgradeHostSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE_UPGRADE_HOST);
        upgradePerformBatchUpgradeUpgradeHostSummary.addSubStepSummary(upgradePerformBatchUpgradeEnterMMSummary);
        upgradePerformBatchUpgradeUpgradeHostSummary.addSubStepSummary(upgradePerformBatchUpgradeFwStagingSummary);
        upgradePerformBatchUpgradeUpgradeHostSummary.addSubStepSummary(upgradePerformBatchUpgradePowerCycleSummary);
        upgradePerformBatchUpgradeUpgradeHostSummary.addSubStepSummary(upgradePerformBatchUpgradeInMmSummary);
        upgradePerformBatchUpgradeUpgradeHostSummary.addSubStepSummary(upgradePerformBatchExitMmSummary);

        LCMStepStatusSummary upgradePerformBatchUpgradeSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_BATCH_UPGRADE);
        upgradePerformBatchUpgradeSummary.addSubStepSummary(upgradePerformBatchUpgradeUpgradeHostSummary);

        LCMStepStatusSummary upgradePerformUpgraderUpgradeSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_UPGRADER_UPGRADE);

        LCMStepStatusSummary upgradePerformCompositeUpgradeSummary = new LCMStepStatusSummary(LCMStep.UPGRADE_PERFORM_COMPOSITE_UPGRADE);
        upgradePerformCompositeUpgradeSummary.addSubStepSummary(upgradePerformUpgraderUpgradeSummary);
        upgradePerformCompositeUpgradeSummary.addSubStepSummary(upgradePerformBatchUpgradeSummary);

        LCMStepStatusSummary uploadProcessSummary = new LCMStepStatusSummary(LCMStep.UPLOAD_PROCESS);

        LCMStepStatusSummary lcmUpgradeSummary = new LCMStepStatusSummary(LCMStep.LCM_UPGRADE);
        lcmUpgradeSummary.addSubStepSummary(uploadProcessSummary);
        lcmUpgradeSummary.addSubStepSummary(upgradePerformCompositeUpgradeSummary);

        return lcmUpgradeSummary;
    }

    /**
     * add StepStatus in summary tree recursively
     *
     * @param summary    Summary list
     * @param stepStatus stepStatus that to be added in summary
     */
    private void addStepStatusInSummary(LCMStepStatusSummary summary, LCMStepStatus stepStatus) {
        if (!summary.getStep().equals(stepStatus.getStep())) {
            if (!summary.getChildSummaryList().isEmpty()) {
                for (LCMStepStatusSummary childSummary : summary.getChildSummaryList()) {
                    addStepStatusInSummary(childSummary, stepStatus);
                }
            }
        } else {
            summary.getStepStatusList().add(stepStatus);
        }
    }

    /**
     * A method that record step,component,host and time stamp as raw data.
     *
     * @param step pre-defined LCM step
     * @param keys contains current componentKey,hostKey and stepDescription for current step
     */
    @Override
    public void logStepStatus(Step step, String... keys) {
        //key[0]=componentKey, keys[1]=hostKey, keys[2]=stepDescription
        if (keys == null || LCM_PROFILING_INPUT_KEYS.length != keys.length) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        keys[KEY_INPUT_STEP_DESCRIPTION] = keys[KEY_INPUT_STEP_DESCRIPTION].replaceAll(CSV_SEPARATOR, StringUtil.SPACE);
        logger.info("{}{}{}{}{}{}{}{}{}", step.getStepName(), CSV_SEPARATOR, keys[KEY_INPUT_COMPONENT], CSV_SEPARATOR, keys[KEY_INPUT_HOST], CSV_SEPARATOR, keys[KEY_INPUT_STEP_DESCRIPTION], CSV_SEPARATOR, currentTime);
    }

    /**
     * A method that record message/flag in the log as raw data.
     *
     * @param message Message that being recorded
     */
    @Override
    public void logMessage(String message) {
        long currentTime = System.currentTimeMillis();
        logger.info("{}{}", message, currentTime);
    }

    /**
     * A method to load StepStatus from latest log file
     *
     * @return List of StepStatus
     */
    @Override
    public List<LCMStepStatus> loadStepStatus() {
        File file = new File(PROFILE_LOG_FILE_PATH);
        if (!file.exists()) {
            logger.warn("No profile log was found! ");
            return Collections.emptyList();
        }
        List<LCMStepStatus> stepStatusList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(profileRecord -> {
                //[0] means date&time, [1] means thread,  [2] means step, [3] means componentKey
                //[4] means hostKey, [5] means step description, [6] means currentTimeStamp
                String profileArr[] = StringUtil.split(profileRecord,CSV_SEPARATOR, RECORD_KEYS_LCM_PROFILING);
                if (profileArr == null || RECORD_KEYS_LCM_PROFILING.length != profileArr.length) {
                    return;
                }
                long timeStamp;
                LCMStep step = LCMStep.UNKNOWN;
                try {
                    timeStamp = Long.parseLong(profileArr[RECORD_KEY_TIMESTAMP].trim());
                } catch (NumberFormatException e) {
                    return;
                }

                if (StringUtils.isBlank(profileArr[RECORD_KEY_STEP])) {
                    return;
                }
                step = LCMStep.valueOf(profileArr[RECORD_KEY_STEP].trim());

                //Step step, String stepDescription, String componentKey, String hostKey, long timeStamp)
                LCMStepStatus stepStatus = new LCMStepStatus(step, profileArr[RECORD_KEY_STEP_DESCRIPTION].trim(), profileArr[RECORD_KEY_COMPONENT].trim(), profileArr[RECORD_KEY_HOST].trim(), timeStamp);
                addStepStatus(stepStatusList, stepStatus);
            });
        } catch (IOException e) {
            logger.warn("Error when reading profiling log");
            return stepStatusList;
        }
        return stepStatusList;
    }

    /**
     * Generate kinds reports
     *
     * @param stepStatusList loaded StepStatus List
     */
    @Override
    public StringBuilder[] generateReports(List<? extends StepStatus> stepStatusList) {
        StringBuilder stepReport = new StringBuilder(StringUtil.LF);
        StringBuilder summaryReport = new StringBuilder(StringUtil.LF);
        generateStepAndSummaryReport((List<LCMStepStatus>) stepStatusList, stepReport, summaryReport);
        return new StringBuilder[]{stepReport, summaryReport};
    }


    /**
     * Generate detailed step report and summary report
     *
     * @param stepStatusList loaded StepStatus List
     * @param stepReport     detailed StepStatus report
     * @param summaryReport  Summary report
     */
    private void generateStepAndSummaryReport(List<LCMStepStatus> stepStatusList, StringBuilder stepReport, StringBuilder summaryReport) {
        stepReport.append(StringUtils.join(REPORT_LABEL_ARR, CSV_SEPARATOR)).append(StringUtil.LF);
        LCMStepStatusSummary summary = buildStepStatusSummary();

        //Sort the Step status list first.
        Collections.sort(stepStatusList);
        for (LCMStepStatus stepStatus : stepStatusList) {
            LCMStep step = stepStatus.getStep();
            String stepDuration = stepStatus.getStepDuration();
            String startTimeStamp = CalendarUtil.format(stepStatus.getMinTimeStamp());
            String endTimeStamp = CalendarUtil.format(stepStatus.getMaxTimeStamp());

            //Below String would be like: 21,UPGRADE_PERFORM_BATCH_UPGRADE_COMPONENT_UPGRADE,Perform batch upgrade component upgrade,VxRail VIB,delle07s1esxi-03.localdomain.local,22s 56ms,2018-08-24 14:07:29.167,2018-08-24 14:07:51.223
            stepReport.append(stepStatus.getStep().ordinal()).append(CSV_SEPARATOR);
            stepReport.append(stepStatus.getStep().name()).append(CSV_SEPARATOR);
            stepReport.append(stepStatus.getStep().getStepName()).append(CSV_SEPARATOR);
            stepReport.append(stepStatus.getComponentKey()).append(CSV_SEPARATOR);
            stepReport.append(stepStatus.getHostKey()).append(CSV_SEPARATOR);
            stepReport.append(stepDuration).append(CSV_SEPARATOR);
            stepReport.append(startTimeStamp).append(CSV_SEPARATOR);
            stepReport.append(endTimeStamp).append(StringUtil.LF);

            addStepStatusInSummary(summary, stepStatus);
        }
        stepReport.append(StringUtil.LF);
        LCMStepStatusSummary.getResult(summaryReport, summary, StringUtil.EMPTY, -1);
    }

    /**
     * Write reports into files
     *
     * @param reports String based reports
     */
    @Override
    public void storeReports(StringBuilder[] reports) {
        if (REPORTS.length != reports.length) {
            logger.error("Error when storing reports, reports count do not match");
            return;
        }
        try {
            FileSystemUtil.writeToFile(reports[REPORT_DETAIL_INDEX].toString(), PROFILE_REPORT_FILE_PATH, true);
            FileSystemUtil.writeToFile(reports[REPORT_SUMMARY_INDEX].toString(), PROFILE_SUMMARY_REPORT_FILE_PATH, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Back up LCM profiling log file
     */
    @Override
    public void backupLogs() {
        try {
            if (FileSystemUtil.backupFile(PROFILE_LOG_FILE_PATH, PROFILE_LOG_FILE_PATH, true)) {
                FileSystemUtil.writeToFile(StringUtil.EMPTY, PROFILE_LOG_FILE_PATH, false);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}