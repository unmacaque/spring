package com.gmail.unmacaque.spring.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum Features implements Feature {

	@EnabledByDefault
	@Label("Feature A")
	FEATURE_A,

	@Label("Feature B")
	FEATURE_B,

	@Label("Feature C")
	FEATURE_C;

	public boolean isActive() {
		return FeatureContext.getFeatureManager().isActive(this);
	}
}
