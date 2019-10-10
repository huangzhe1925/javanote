package com.hz.javanote.profile.lcm;

import com.hz.javanote.profile.Step;

public enum LCMStep implements Step {
    SURROGATE_DEPLOYMENT("Surrogate deployment"),

    SURROGATE_WAIT_FOR_START_UP("Wait for to surrogate start up"),

    UPLOAD_PROCESS("Composite upload process"),

    UPLOAD_UNPACK_PROCESS("Unpack the uploaded composite bundle file"),

    UPLOAD_PRECHECK_PROCESS("Pre-check process within upload process"),

    UPLOAD_FILES_TO_VSAN_PROCESS("Upload files to vsan process"),


    UPGRADE_BEFORE_UPGRADE("Before upgrade"),

    UPGRADE_PERFORM_COMPOSITE_UPGRADE("Perform composite upgrade"),

    UPGRADE_PERFORM_GLOBAL_PRECHECK("Perform global pre-check"),

    UPGRADE_PERFORM_BUNDLE_PRECHECK("Perform bundle pre-check"),

    UPGRADE_PERFORM_BUNDLE_PREHOOK("Perform bundle pre-hook"),

    UPGRADE_PERFORM_UPGRADER_UPGRADE("Perform upgrader upgrade"),

    UPGRADE_PERFORM_BUNDLE_POST_HOOK("Perform bundle post hook"),

    UPGRADE_PERFORM_BUNDLE_POST_CHECK("Perform bundle post check"),


    UPGRADE_PERFORM_BATCH_UPGRADE("perform batch upgrade"),

    UPGRADE_PERFORM_BATCH_UPGRADE_PRECHECK("Perform batch upgrade pre-check"),

    UPGRADE_PERFORM_BATCH_UPGRADE_PREHOOK("Perform batch upgrade pre-hook"),

    UPGRADE_PERFORM_BATCH_UPGRADE_PREPARE_FW("Perform batch upgrade prepare fw"),

    UPGRADE_PERFORM_BATCH_UPGRADE_UPGRADE_HOST("Perform batch upgrade upgrade host"),

    UPGRADE_PERFORM_BATCH_UPGRADE_ENTER_MM("Perform batch upgrade enter maintainance mode"),

    UPGRADE_PERFORM_BATCH_UPGRADE_DRYRUN_PRECHECK("Perform batch upgrade dryrun precheck"),

    UPGRADE_PERFORM_BATCH_UPGRADE_COMPONENT_UPGRADE("Perform batch upgrade component upgrade"),

    UPGRADE_PERFORM_BATCH_UPGRADE_FW_STAGING("Perform batch upgrade check firmware upgrade status"),

    UPGRADE_PERFORM_BATCH_UPGRADE_POWER_CYCLE("Perform batch upgrade power cycle"),

    UPGRADE_PERFORM_BATCH_UPGRADE_EXIT_MM("Perform upgrade batch exit maintainance mode"),

    UPGRADE_PERFORM_BATCH_UPGRADE_IN_MM("Perform batch upgrade in maintainance mode"),

    UPGRADE_PERFORM_BATCH_UPGRADE_POST_HOOK("Perform batch upgrade post hook"),

    UPGRADE_PERFORM_BATCH_UPGRADE_POST_CHECK("Perform batch upgrade post check"),

    UPGRAD_AFTER_UPGRADE_SUCCESS("After upgrade success"),


    LCM_UPGRADE("LCM upgrade process"),

    UNKNOWN("Unknown");

    private String stepDesc;

    LCMStep(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    @Override
    public String getStepName(){
        return this.name();
    }

    @Override
    public int getStepOrder(){
        return this.ordinal();
    }

    @Override
    public String getStepDescription(){
        return this.stepDesc;
    }
}
