package com.modefin.monitorservice.emailService;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.modefin.monitorservice.Utils.EmailUtil;
import com.modefin.monitorservice.Utils.PortUtil;
import com.modefin.monitorservice.config.AppConfig;
import com.modefin.monitorservice.config.MonitorProperties;

@Service
public class MonitorService {

	private static final Logger log = LoggerFactory.getLogger(MonitorService.class);

	private final EmailUtil emailUtil;
	private final MonitorProperties props;

	public MonitorService(EmailUtil emailUtil, MonitorProperties props) {
		this.emailUtil = emailUtil;
		this.props = props;
	}

	// Use cron from yml
	@Scheduled(cron = "${monitor.cron}")
	public void monitorApps() {
		try {
			props.getApps().stream().filter(app -> !PortUtil.isAppRunning(app.getHost(), app.getPort()))
					.forEach(app -> {
						log.error("❌ {} is NOT running on {}:{}", app.getName(), app.getHost(), app.getPort());
						emailUtil.sendEmail(props.getAlertEmail(), "App Down Alert: " + app.getName(),
								"The application " + app.getName() + " on " + app.getHost() + ":" + app.getPort()
										+ " is not running.");
					});

			Optional<AppConfig> anyDown = props.getApps().stream()
					.filter(app -> !PortUtil.isAppRunning(app.getHost(), app.getPort())).findAny();

			if (anyDown.isEmpty()) {
				log.info("✅ All applications are running fine at {}", new Date());
			}

		} catch (Exception e) {
			log.error("⚠️ Monitoring job failed due to unexpected error: {}", e.getMessage(), e);
		}
	}
}

//@Service
//public class MonitorService {
//
//	private final EmailUtil emailUtil;
//
//	// ✅ Inject Email utility
//	public MonitorService(EmailUtil emailUtil) {
//		this.emailUtil = emailUtil;
//	}
//
//	// Apps to monitor
//	private final List<AppConfig> appsToMonitor = Arrays.asList(new AppConfig("localhost", 2220, "Service"));
//
//	private final String alertMail = "nswaroop789@gmail.com";
//
//	// Scheduled task runs every 2 minutes
////	@Scheduled(fixedRate = 120000) // 2 minutes
//	@Scheduled(cron = "0 */2 * * * *")
//	public void monitorApps() {
//		appsToMonitor.stream().filter(app -> !PortUtil.isAppRunning(app.getHost(), app.getPort())).forEach(app -> {
//			System.out.println("❌ " + app.getName() + " is NOT running on port " + app.getPort());
//			emailUtil.sendEmail(alertMail, "App Down Alert: " + app.getName(), "The application " + app.getName()
//					+ " on " + app.getHost() + ":" + app.getPort() + " is not running.");
//		});
//
//		// If all apps are running
//		Optional<AppConfig> anyDown = appsToMonitor.stream()
//				.filter(app -> !PortUtil.isAppRunning(app.getHost(), app.getPort())).findAny();
//
//		if (anyDown.isEmpty()) {
//			System.out.println("✅ All applications are running fine! --> " + new Date());
//		}
//	}
//}
