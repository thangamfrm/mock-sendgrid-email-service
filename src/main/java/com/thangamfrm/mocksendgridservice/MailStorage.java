package com.thangamfrm.mocksendgridservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.thangamfrm.mocksendgridservice.dto.SendGridEmail;

public class MailStorage {

    private static final Logger LOG = (Logger) LoggerFactory
            .getLogger(MailStorage.class);

    private static MailStorage instance;

    private Map<String, List<SendGridEmail>> sendGridEmails;

    private MailStorage() {
        LOG.info("Initializing MailStorage!");
        sendGridEmails = new ConcurrentHashMap<String, List<SendGridEmail>>();
        LOG.info("Initalization Completed!");
    }

    public static MailStorage getInstance() {
        if (instance == null) {
            instance = new MailStorage();
        }
        return instance;
    }

    public void save(SendGridEmail sendGridEmail) {
        if (sendGridEmails.containsKey(sendGridEmail.getTo())) {
            List<SendGridEmail> mails = sendGridEmails.get(sendGridEmail
                    .getTo());
            mails.add(sendGridEmail);
            LOG.info(String
                    .format("Email Address: %s already exist! Adding new SendGridEmail with subject: %s",
                            sendGridEmail.getTo(), sendGridEmail.getSubject()));
        } else {
            List<SendGridEmail> mails = new ArrayList<SendGridEmail>();
            mails.add(sendGridEmail);
            sendGridEmails.put(sendGridEmail.getTo(), mails);
            LOG.info(String
                    .format("Email Address: %s doesn't exist! Adding new SendGridEmail with subject: %s",
                            sendGridEmail.getTo(), sendGridEmail.getSubject()));
        }
    }

    public int clear(String emailAddress) {
        if (sendGridEmails.containsKey(emailAddress)) {
            int count = sendGridEmails.size();
            LOG.info(String
                    .format("Email Address: %s already exist! Clearing SendGridEmail count: %d",
                            emailAddress, count));
            sendGridEmails.remove(emailAddress);
            return count;
        } else {
            LOG.info(String.format(
                    "Email Address: %s doesn't exist! Noting to clear!",
                    emailAddress));
            return 0;
        }
    }

    public List<SendGridEmail> read(String emailAddress) {
        if (sendGridEmails.containsKey(emailAddress)) {
            return sendGridEmails.get(emailAddress);
        } else {
            return new ArrayList<SendGridEmail>();
        }
    }

}
