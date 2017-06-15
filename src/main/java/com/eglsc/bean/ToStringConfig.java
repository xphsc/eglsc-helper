package com.eglsc.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by ${huipei.x} on 2017-6-12.
 */
public class ToStringConfig {
    private static final long serialVersionUID = 3182446945343865398L;
    public static final String DEFAULT_CONNECTOR = ",";
    public static final ToStringConfig DEFAULT_CONFIG = new ToStringConfig();
    public static final ToStringConfig IGNORE_NULL_OR_EMPTY_CONFIG = new ToStringConfig(",", false);
    private String connector = ",";
    private boolean isJoinNullOrEmpty = true;

    public ToStringConfig() {
    }

    public ToStringConfig(String connector) {
        this.connector = connector;
    }

    public ToStringConfig(String connector, boolean isJoinNullOrEmpty) {
        this.connector = connector;
        this.isJoinNullOrEmpty = isJoinNullOrEmpty;
    }

    public String getConnector() {
        return this.connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public boolean getIsJoinNullOrEmpty() {
        return this.isJoinNullOrEmpty;
    }

    public void setIsJoinNullOrEmpty(boolean isJoinNullOrEmpty) {
        this.isJoinNullOrEmpty = isJoinNullOrEmpty;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
