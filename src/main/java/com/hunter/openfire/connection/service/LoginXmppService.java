package com.hunter.openfire.connection.service;

import static com.hunter.openfire.connection.util.Util.XMPP_DOMAIN;
import static com.hunter.openfire.connection.util.Util.XMPP_PORT;
import static com.hunter.openfire.connection.util.Util.modifyWildcards;

import java.io.IOException;
import java.net.InetAddress;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.carbons.CarbonManager;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Resourcepart;
import org.springframework.stereotype.Service;

import com.hunter.openfire.connection.exception.ExceptionLoggingCallback;

/**
 * @author Fredi Hernandez
 *
 */
@Service
public class LoginXmppService {

	private AbstractXMPPConnection connection;

	public boolean connect(String username, String password) throws SmackException, IOException, XMPPException, InterruptedException {
		// SPARK-2140 - add support to Spark for stream management. Challenges expected around reconnection logic
		XMPPTCPConnection.setUseStreamManagementDefault(false);

		Resourcepart resource = Resourcepart.from(modifyWildcards(InetAddress.getLocalHost().getHostName()).trim());
		DomainBareJid xmppDomain = JidCreate.domainBareFrom(XMPP_DOMAIN);

		final XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder()
				.setSecurityMode(SecurityMode.disabled)
				.setUsernameAndPassword(username, password)
				.setXmppDomain(xmppDomain)
				.setPort(XMPP_PORT)
				.setSendPresence(true)
				.setResource(resource);

		connection = new XMPPTCPConnection(configBuilder.build());
		connection.setParsingExceptionCallback(new ExceptionLoggingCallback());
		connection.connect();
		connection.login();

		final CarbonManager carbonManager = CarbonManager.getInstanceFor(connection);
		if (carbonManager.isSupportedByServer()) {
			carbonManager.enableCarbons();
		}

		if (connection.isAuthenticated() & connection.isConnected()) {
			return true;
		}

		return false;
	}
}
