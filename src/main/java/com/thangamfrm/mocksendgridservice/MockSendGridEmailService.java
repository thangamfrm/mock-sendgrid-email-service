package com.thangamfrm.mocksendgridservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import ch.qos.logback.classic.Logger;

import com.thangamfrm.mocksendgridservice.dto.SendGridEmail;
import com.thangamfrm.mocksendgridservice.dto.SendGridEmailResponse;

@Controller
public class MockSendGridEmailService {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(MockSendGridEmailService.class);

    @Autowired
    private View jsonView;

    private static final String MESSAGE_FIELD = "message";
    private static final String MAIL_FIELD = "mails";
    private static final String[] CREDENTIALS_KEYS = {"api_user", "api_key"};

    @RequestMapping(value = "/api/mail.read.json/{emailId}", method = RequestMethod.GET)
    public ModelAndView readMails(@PathVariable("emailId") String emailAddress) {
        return new ModelAndView(jsonView, MAIL_FIELD, MailStorage.getInstance().read(emailAddress));
    }

    @RequestMapping(value = "/api/mail.clear.json/{emailId}", method = RequestMethod.GET)
    public ModelAndView clearMails(@PathVariable("emailId") String emailAddress) {
        if (StringUtils.isEmpty(emailAddress)) {
            String errMsg = "Invalid Parameters! Specify the Email ID to clear all messages.";
            return createErrorResponse(errMsg);
        }

        MailStorage.getInstance().clear(emailAddress);
        
        LOG.info("Cleared all mails with address {}", emailAddress);
        return new ModelAndView(jsonView, MESSAGE_FIELD, "success");
    }

    @RequestMapping(value = "/api/mail.send.json", method = RequestMethod.POST, produces="application/json",
    		headers = "content-type=application/x-www-form-urlencoded")
    public @ResponseBody SendGridEmailResponse sendMail(HttpServletRequest request) {
        LOG.info("Received SendGrid mail delivery request!");
        String apiUser = request.getParameter(CREDENTIALS_KEYS[0]);
        String apiKey = request.getParameter(CREDENTIALS_KEYS[1]);

        if (StringUtils.isEmpty(apiUser) || StringUtils.isEmpty(apiKey)) {
        	String errorMsg = "API User (or) Key NOT found!";
			LOG.error(errorMsg);
			return new SendGridEmailResponse("error", errorMsg);        	
        } else {
        	LOG.info("Found valid API User & Key!");
        }

        try {
        	@SuppressWarnings("unchecked")
			Map<String, String[]> requestMap = request.getParameterMap();
        	LOG.info("key count: " + requestMap.size());
        	for (String str : requestMap.keySet()) {
        		if (str.equals(CREDENTIALS_KEYS[0]) || str.equals(CREDENTIALS_KEYS[1])) {
        			// skip credentials from logging
        			continue;
        		}
        		String[] values = requestMap.get(str);
        		for (String value : values) {
        			LOG.info("Key: " + str + " , value: " + value);
        		}
        	}
		} catch (Exception e) {
			LOG.error("Unable to send mail!", e);
			return new SendGridEmailResponse("error", e.getMessage());
		}

        SendGridEmail sendGridEmail = parseAndSaveMailRequest(request);
        MailStorage.getInstance().save(sendGridEmail);
        return new SendGridEmailResponse("success");
    }

    private SendGridEmail parseAndSaveMailRequest(HttpServletRequest request) {
    	SendGridEmail sendGridEmail = new SendGridEmail();
    	sendGridEmail.setTo(getRequestParameter(request, "to"));
    	sendGridEmail.setToName(getRequestParameter(request, "toname"));
    	sendGridEmail.setxSmtpApi(getRequestParameter(request, "x-smtpapi"));
    	sendGridEmail.setSubject(getRequestParameter(request, "subject"));
    	sendGridEmail.setText(getRequestParameter(request, "text"));
    	sendGridEmail.setHtml(getRequestParameter(request, "html"));
    	sendGridEmail.setFrom(getRequestParameter(request, "from"));
    	sendGridEmail.setBcc(getRequestParameter(request, "bcc"));
    	sendGridEmail.setFromName(getRequestParameter(request, "fromname"));
    	sendGridEmail.setReplyTo(getRequestParameter(request, "replyto"));
    	sendGridEmail.setDate(getRequestParameter(request, "date"));
    	sendGridEmail.setFiles(getRequestParameter(request, "files"));
    	sendGridEmail.setContent(getRequestParameter(request, "content"));
    	sendGridEmail.setHeaders(getRequestParameter(request, "headers"));
    	return sendGridEmail;
    }

    private String getRequestParameter(HttpServletRequest request, String parameter) {
    	String value = request.getParameter(parameter);
    	return StringUtils.isEmpty(parameter) ? "" : value;
    }

    public void setJsonView(View view) {
        this.jsonView = view;
    }

    private ModelAndView createErrorResponse(String errMsg) {
        return new ModelAndView(jsonView, MESSAGE_FIELD, errMsg);
    }

}
