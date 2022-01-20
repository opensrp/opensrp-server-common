package org.opensrp.common.audit;

import static java.util.Collections.binarySearch;
import static org.opensrp.common.audit.AuditMessageType.NORMAL;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class Auditor {
	
	private List<AuditMessage> messages;
	
	private final int numberOfAuditMessagesToHoldOnTo;
	
	private static long messageIndex = DateTime.now().getMillis();
	
	private static ReentrantLock lock = new ReentrantLock();
	
	private static Logger logger = LogManager.getLogger(Auditor.class.toString());
	
	@Autowired
	public Auditor(@Value("#{opensrp['number.of.audit.messages']}") int numberOfAuditMessagesToHoldOnTo) {
		this.messages = new ArrayList<>();
		this.numberOfAuditMessagesToHoldOnTo = numberOfAuditMessagesToHoldOnTo;
	}
	
	public AuditMessageBuilder audit(AuditMessageType type) {
		return new AuditMessageBuilder(this, type);
	}
	
	public List<AuditMessage> messagesSince(long messageIndex) {
		if (messageIndex <= 0) {
			return messages;
		}
		
		int index = binarySearch(messages, new AuditMessage(new DateTime(), messageIndex, NORMAL, null));
		int position = Math.abs(index + 1);
		
		if (position >= messages.size()) {
			return Collections.emptyList();
		}
		
		return messages.subList(position, messages.size());
	}
	
	private void prune() {
		if (messages.size() > numberOfAuditMessagesToHoldOnTo) {
			messages.remove(0);
		}
	}
	
	private void createAuditMessage(AuditMessageType messageType, Map<String, String> data) {
		lock.lock();
		try {
			AuditMessage auditMessage = new AuditMessage(new DateTime(), messageIndex, messageType, data);
			messages.add(auditMessage);
			messageIndex++;
			logger.debug(MessageFormat.format("Added message: {0}", auditMessage));
			prune();
		}
		finally {
			lock.unlock();
		}
	}
	
	public static class AuditMessageBuilder {
		
		private final Auditor auditor;
		
		private final AuditMessageType type;
		
		private Map<String, String> extraData;
		
		public AuditMessageBuilder(Auditor auditor, AuditMessageType type) {
			this.auditor = auditor;
			this.type = type;
			this.extraData = new HashMap<>();
		}
		
		public AuditMessageBuilder with(String key, String value) {
			if (!type.supports(key)) {
				throw new ForbiddenFieldInAuditMessage(type, key, value);
			}
			extraData.put(key, value);
			return this;
		}
		
		public void done() {
			auditor.createAuditMessage(type, extraData);
		}
	}
}
