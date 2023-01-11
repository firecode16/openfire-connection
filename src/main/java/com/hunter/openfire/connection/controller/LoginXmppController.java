package com.hunter.openfire.connection.controller;

import static com.hunter.openfire.connection.util.Util.ERROR_RESPONSE;
import static com.hunter.openfire.connection.util.Util.OK_RESPONSE;

import java.io.IOException;
import java.util.Map;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hunter.openfire.connection.response.OpenfireResponse;
import com.hunter.openfire.connection.service.LoginXmppService;

/**
 * @author Fredi Hernandez
 *
 */
@RestController
public class LoginXmppController {

	@Autowired
	private LoginXmppService service;

	@CrossOrigin
	@PostMapping("/xmppConnection")
	public OpenfireResponse login(@RequestBody Map<String, Object> map) throws SmackException, IOException, XMPPException, InterruptedException {
		OpenfireResponse response;

		String username = (String) map.get("username");
		String password = (String) map.get("password");

		boolean isLogin = service.connect(username, password);
		if (isLogin) {
			response = new OpenfireResponse(OK_RESPONSE, "Authenticated");
		} else {
			response = new OpenfireResponse(ERROR_RESPONSE, "FAILED_AUTHENTICATED");
		}

		return response;
	}

}
