package com.poc.bcaf.solace.messaging.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.util.UUID;

public class SendEmail implements Serializable {

    private String From;
    private String To;
    private String Cc;
    private String Subject;

    private String Body;
    private String AttachmentName;
    private String AttachmentDescription;
    private String AttachmentType;
    private String AttachmentBody;

    public SendEmail() {
    }

    public SendEmail(String from, String to, String cc, String subject, String body, String attachmentName, String attachmentDescription, String attachmentType, String attachmentBody) {
        this.From = from;
        this.To = to;
        this.Cc = cc;
        this.Subject = subject;
        this.Body = body;
        this.AttachmentName = attachmentName;
        this.AttachmentDescription = attachmentDescription;
        this.AttachmentType = attachmentType;
        this.AttachmentBody = attachmentBody;
    }

    public SendEmail(String from, String to) {
        From = from;
        To = to;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getCc() {
        return Cc;
    }

    public void setCc(String cc) {
        Cc = cc;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        AttachmentName = attachmentName;
    }

    public String getAttachmentDescription() {
        return AttachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        AttachmentDescription = attachmentDescription;
    }

    public String getAttachmentType() {
        return AttachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        AttachmentType = attachmentType;
    }

    public String getAttachmentBody() {
        return AttachmentBody;
    }

    public void setAttachmentBody(String attachmentBody) {
        AttachmentBody = attachmentBody;
    }

    @Override
    public String toString() {
        return "SendEmail{" +
                "From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", Cc='" + Cc + '\'' +
                ", Subject='" + Subject + '\'' +
                ", Body='" + Body + '\'' +
                ", AttachmentName='" + AttachmentName + '\'' +
                ", AttachmentDescription='" + AttachmentDescription + '\'' +
                ", AttachmentType='" + AttachmentType + '\'' +
                ", AttachmentBody='" + AttachmentBody + '\'' +
                '}';
    }

    public String toJSON() throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        ObjectNode childNode1 = mapper.createObjectNode();
        childNode1.put("From", From);
        childNode1.put("To", To);
        childNode1.put("CC", Cc);
        childNode1.put("Subject", Subject);
        childNode1.put("Body", Body);
        childNode1.put("AttachmentName", AttachmentName);
        childNode1.put("AttachmentDescription", AttachmentDescription);
        childNode1.put("AttachmentType", getAttachmentType());
        childNode1.put("AttachmentBody", AttachmentBody);
        rootNode.set("RequestParameter", childNode1);

        ObjectNode childNode2 = mapper.createObjectNode();

        rootNode.put("TrxId" , String.valueOf(uuid));
        rootNode.put("Source" , "Financore");


        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        return jsonString;
    }

}
