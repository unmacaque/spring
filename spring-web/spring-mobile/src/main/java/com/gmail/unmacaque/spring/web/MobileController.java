package com.gmail.unmacaque.spring.web;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MobileController {
	public static final String SPRING_MOBILE_SITE_PREFERENCE_COOKIE = "org.springframework.mobile.device.site.CookieSitePreferenceRepository.SITE_PREFERENCE";

	@ModelAttribute("device")
	public String device(Device device) {
		if (device.isMobile()) {
			return "Mobile";
		}
		if (device.isTablet()) {
			return "Tablet";
		}
		return "Generic device";
	}

	@ModelAttribute("platform")
	public String platform(Device device) {
		switch (device.getDevicePlatform()) {
			case ANDROID:
				return "Android";
			case IOS:
				return "iOS";
			default:
				return "Unspecified vendor";
		}
	}

	@ModelAttribute("sitePreference")
	public SitePreference isSitePreference(SitePreference sitePreference) {
		return sitePreference;
	}

	@ModelAttribute("sitePreferenceSet")
	public boolean isSitePreferenceSet(
			@RequestParam(value = "site_preference", required = false) String sitePreferenceParam,
			@CookieValue(value = SPRING_MOBILE_SITE_PREFERENCE_COOKIE, defaultValue = "") String sitePreferenceCookie) {
		return !StringUtils.isEmpty(sitePreferenceParam) || !StringUtils.isEmpty(sitePreferenceCookie);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
