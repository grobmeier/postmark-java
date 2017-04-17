// The MIT License
//
// Copyright (c) 2010 Jared Holdcroft
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
 */
public class PostmarkMessage extends PostmarkMessageBase {

    // The message subject line.
    @SerializedName("Subject")
    private String subject;

    // The message body, if the message contains HTML.
    @SerializedName("HtmlBody")
    private String htmlBody;

    // The message body, if the message is plain text.
    @SerializedName("TextBody")
    private String textBody;

    @SkipMe
    private boolean isHTML;

    public PostmarkMessage(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String bccAddress, String subject, String body, boolean isHTML, String tag, List<NameValuePair> headers) {

        super(fromAddress, toAddress, replyToAddress, ccAddress, bccAddress, tag,  headers);

        this.isHTML = isHTML;
        this.subject = subject;
        if (isHTML)
            this.htmlBody = body;
        else
            this.textBody = body;

    }

    public PostmarkMessage(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String subject, String body, boolean isHTML, String tag, List<NameValuePair> headers) {

        this(fromAddress, toAddress, replyToAddress, ccAddress, null, subject, body, isHTML, tag, headers);

    }



    public PostmarkMessage(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String subject, String body, boolean isHTML, String tag) {

        this(fromAddress, toAddress, replyToAddress, ccAddress, null, subject, body, isHTML, tag, null);

    }

    // Copy Constructor
    public PostmarkMessage(PostmarkMessage message) {
        super(message);

        this.isHTML = message.isHTML;
        this.subject = message.subject;
        this.htmlBody = message.htmlBody;
        this.textBody = message.textBody;
    }

    public void clean() {
        super.clean();
        this.subject = (this.subject == null) ? "" : this.subject.trim();
    }

    /**
     * @return the email subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject The email subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the body of a HTML message
     */
    public String getHtmlBody() {
        return htmlBody;
    }

    /**
     * @param htmlBody The HTML body content of the message
     */
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    /**
     * @return the body of a plain text message
     */
    public String getTextBody() {
        return textBody;
    }

    /**
     * @param textBody The plain text body content of the message
     */
    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostmarkMessage that = (PostmarkMessage) o;

        if (ccAddress != null ? !ccAddress.equals(that.ccAddress) : that.ccAddress != null) return false;
        if (bccAddress != null ? !bccAddress.equals(that.bccAddress) : that.bccAddress != null) return false;
        if (fromAddress != null ? !fromAddress.equals(that.fromAddress) : that.fromAddress != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        if (htmlBody != null ? !htmlBody.equals(that.htmlBody) : that.htmlBody != null) return false;
        if (replyToAddress != null ? !replyToAddress.equals(that.replyToAddress) : that.replyToAddress != null)
            return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (textBody != null ? !textBody.equals(that.textBody) : that.textBody != null) return false;
        if (toAddress != null ? !toAddress.equals(that.toAddress) : that.toAddress != null) return false;
        if (tag != null ? !tag.equals(that.toAddress) : that.tag != null) return false;
        if (isHTML != that.isHTML) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromAddress != null ? fromAddress.hashCode() : 0;
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + (ccAddress != null ? ccAddress.hashCode() : 0);
        result = 31 * result + (bccAddress != null ? bccAddress.hashCode() : 0);
        result = 31 * result + (replyToAddress != null ? replyToAddress.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (htmlBody != null ? htmlBody.hashCode() : 0);
        result = 31 * result + (textBody != null ? textBody.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PostmarkMessage");
        sb.append("{ fromAddress='").append(fromAddress).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append(", ccAddress='").append(ccAddress).append('\'');
        sb.append(", bccAddress='").append(bccAddress).append('\'');
        sb.append(", replyToAddress='").append(replyToAddress).append('\'');
        sb.append(", subject='").append(subject).append('\'');

        sb.append(", htmlBody='").append(htmlBody).append('\'');
        sb.append(", textBody='").append(textBody).append('\'');

        sb.append(", tag='").append(tag).append('\'');
        sb.append(", headers=").append(headers);
        sb.append('}');
        return sb.toString();
    }


}


