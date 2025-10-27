package com.modefin.monitorservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modefin.monitorservice.emailService.MonitorService;

@RestController
public class MonitorController {

	@Autowired
	private final MonitorService monitorService;

	public MonitorController(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	// ✅ API to manually trigger monitoring
	@GetMapping("/monitor-now")
	public String monitorNow() {
		monitorService.monitorApps();
		return "🔍 Monitoring executed manually!";
	}

	@GetMapping("/")
	public String home() {
		return "✅ Monitor Service is running! Use /monitor-now to trigger monitoring manually.";
	}

}
