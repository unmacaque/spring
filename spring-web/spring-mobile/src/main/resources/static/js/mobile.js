window.deleteSitePreference = function() {
	document.cookie = 'org.springframework.mobile.device.site.CookieSitePreferenceRepository.SITE_PREFERENCE=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	window.location = '/';
}
