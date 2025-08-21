package com.s2p.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

/**
 * File Name: AuthorizationEvents.java
 * Entity: AuthorizationEvents
 * Package: com.s2p.StudentSelfReflection.events
 * Author: pranayramteke
 * Date: 30/06/25
 * Description:
 */

@Component
@Slf4j
public class AuthorizationEvents
{
	@EventListener
	public void onFailure(AuthorizationDeniedEvent deniedEvent)
	{
		log.error("Authorization failed for the user : {} due to : {}", deniedEvent.getAuthentication().get().getName(),
				deniedEvent.getAuthorizationDecision().toString());
	}
}
