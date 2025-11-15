package com.gmail.unmacaque.spring.togglz.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum TogglzFeature implements Feature {

	@EnabledByDefault
	@Label("Feature A")
	FEATURE_A,

	@Label("Feature B")
	FEATURE_B,

	@Label("Feature C")
	FEATURE_C,

}
