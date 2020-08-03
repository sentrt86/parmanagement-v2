package com.htc.par.utility;


import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailUtility {
	
	
	@Autowired 
	JavaMailSender javaMailSender;

	@Autowired
    private Configuration freemarkerConfig;

	@Autowired
	public  EmailUtility(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	
	public Boolean sendEmail(Map<String, Object> model,String mailList[]) throws Exception,MailException {
		
		
		Boolean mailSent = false;
	
	 	MimeMessage message = javaMailSender.createMimeMessage();
	 
        MimeMessageHelper helper = new MimeMessageHelper(message);      

        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
   
        Template template = freemarkerConfig.getTemplate("email-template.ftl");
        
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

 
        helper.setTo(mailList);
        helper.setFrom("pradeep.ekambaram@htcinc.com");
        helper.setText(text, true);
        helper.setSubject("New par Allocation");
        
        try {
        	javaMailSender.send(message);
        	System.out.println("email sent");
        	mailSent = true;
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        return mailSent;
        
	}
	
	
}
