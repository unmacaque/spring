package com.gmail.unmacaque.spring.mobile;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	@ModelAttribute("device")
	public String device(Device device) {
		if (device.isMobile()) {
			return "Mobile";
		}
		if (device.isTablet()) {
			return "Tablet";
		}
		return "Regular";
	}

	@ModelAttribute("platform")
	public String platform(Device device) {
		switch (device.getDevicePlatform()) {
		case ANDROID:
			return "Android";
		case IOS:
			return "iOS";
		default:
			return "Generic";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
