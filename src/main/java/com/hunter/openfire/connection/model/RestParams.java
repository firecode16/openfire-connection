package com.hunter.openfire.connection.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Fredi Hernandez
 *
 */
@Component
@ConfigurationProperties
public class RestParams implements Serializable {
	private static final long serialVersionUID = 1L;
}
