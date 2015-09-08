// The MIT License
//
// Copyright (c) 2010 Micheal Swiggs
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.postmark.java;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for a Postmark message.
 * <p/>
 * This library can be used to leverage the postmarkapp.com functionality from a Java client
 * <p/>
 * http://github.com/jaredholdcroft/postmark-java
 */
public class PostmarkMessageBase {

    // The sender's email address.
    @SerializedName("From")
    protected String fromAddress;

    // The recipients's email address.
    @SerializedName("To")
    protected String toAddress;

    // The email address to reply to. This is optional.
    @SerializedName("ReplyTo")
    protected String replyToAddress;

    // The email address to carbon copy to. This is optional.
    @SerializedName("Cc")
    protected String ccAddress;

    // The email address to blind carbon copy to. This is optional.
    @SerializedName("Bcc")
    protected String bccAddress;

    // An optional tag than can be associated with the email.
    @SerializedName("Tag")
    protected String tag;

    // A collection of optional message headers.
    @SerializedName("Headers")
    protected List<NameValuePair> headers;

    @SerializedName("Attachments")
    protected List<Attachment> attachments;


    protected PostmarkMessageBase(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String bccAddress, String tag, List<NameValuePair> headers) {

        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.replyToAddress = replyToAddress;
        this.ccAddress = ccAddress;
        this.bccAddress = bccAddress;

        this.tag = tag;

        this.headers = (headers == null) ? new ArrayList<NameValuePair>() : headers;
    }

    protected PostmarkMessageBase(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String tag, List<NameValuePair> headers) {

        this(fromAddress, toAddress, replyToAddress, ccAddress, null, tag, headers);
    }

    protected PostmarkMessageBase(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String tag) {

        this(fromAddress, toAddress, replyToAddress, ccAddress, null, tag, null);
    }

    // Copy Constructor
    public PostmarkMessageBase(PostmarkMessageBase message) {

        this.fromAddress = message.fromAddress;
        this.toAddress = message.toAddress;
        this.replyToAddress = message.replyToAddress;
        this.ccAddress = message.ccAddress;
        this.bccAddress = message.bccAddress;
        this.headers = message.headers;
    }

    public void clean() {
        this.fromAddress = this.fromAddress.trim();
        this.toAddress = this.toAddress.trim();
    }

    public void validate() throws PostmarkException {

        if ((this.fromAddress == null) || (this.fromAddress.equals(""))) {
            throw new PostmarkException("You must specify a valid 'From' email address.");
        }
        if ((this.toAddress == null) || (this.toAddress.equals(""))) {
            throw new PostmarkException("You must specify a valid 'To' email address.");
        }

        // TODO: add more validation using regex
    }


    /**
     * @return the from email address
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress The email address the message is sent from
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return the to email address
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress The email address the message is sent to
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the cc email address
     */
    public String getCcAddress() {
        return ccAddress;
    }

    /**
     * @param ccAddress The email address the message is sent to
     */
    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    /**
     * @return the bcc email address
     */
    public String getBccAddress() {
        return bccAddress;
    }

    /**
     * @param bccAddress The email address a blind carbon copy of the message is sent to
     */
    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    /**
     * @return the reply-to email address
     */
    public String getReplyToAddress() {
        return replyToAddress;
    }

    /**
     * @param replyToAddress The reply-to email address of the message
     */
    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    /**
     * @return the tag (an optional category) that is associated with this mail
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag The tag (an optional category) that is associated with this mail
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the email headers
     */
    public List<NameValuePair> getHeaders() {
        return headers;
    }

    /**
     * @param headers A map of all the email headers
     */
    public void setHeaders(List<NameValuePair> headers) {
        this.headers = headers;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Lets you attach file. See {@link Attachment} for details.
     *
     * @param attachments list of attachments
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

}


