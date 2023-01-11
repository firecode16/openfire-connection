package com.hunter.openfire.connection.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jivesoftware.smack.UnparseableStanza;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;

/**
 * @author Fredi Hernandez
 * 
 */
public class ExceptionLoggingCallback implements ParsingExceptionCallback {

	private static final Logger LOGGER = Logger.getLogger(ExceptionLoggingCallback.class.getName());

	@Override
	public void handleUnparsableStanza(UnparseableStanza stanzaData) throws Exception {
		LOGGER.log(Level.SEVERE, "Smack message parsing exception. Content: '" + stanzaData.getContent() + "'", stanzaData.getParsingException());
	}

}
