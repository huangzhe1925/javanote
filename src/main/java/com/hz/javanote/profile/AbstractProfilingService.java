package com.hz.javanote.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class AbstractProfilingService implements ProfilingService {

	private final Logger logger = LoggerFactory.getLogger(AbstractProfilingService.class);

	@Override
	public void generateReports() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					List<? extends StepStatus> stepStatusList = loadStepStatus();
					if (!CollectionUtils.isEmpty(stepStatusList)) {
						StringBuilder[] reportList = generateReports(stepStatusList);
						storeReports(reportList);
						backupLogs();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}).start();
	}
}
