// The MIT License
//
// Copyright (c) 2015 Micheal Swiggs
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
import java.util.Map;

/**
 * Wrapper class for a Postmark message.
 * <p/>
 * This library can be used to leverage the postmarkapp.com functionality from a Java client
 * <p/>
 * http://github.com/jaredholdcroft/postmark-java
 */
public class PostmarkTemplate extends PostmarkMessageBase
{

    //Required, refers to the template to use.
    @SerializedName("TemplateId")
    private int templateId;

    //Required, the model to be applied to the template to generate HtmlBody, TextBody and Subject.
    @SerializedName("TemplateModel")
    private TemplateModel templateModel;


    //Optional, default true, if styling should be applied as inline css.
    @SerializedName("InlineCss")
    private boolean inlineCss;


    public PostmarkTemplate(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String bccAddress, String tag, List<NameValuePair> headers, int templateId, TemplateModel templateModel, boolean inlineCss) {
        super(fromAddress, toAddress, replyToAddress, ccAddress, bccAddress, tag,  headers);

        this.templateId = templateId;
        this.templateModel = templateModel;
        this.inlineCss = inlineCss;
    }

    public PostmarkTemplate(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String tag, List<NameValuePair> headers, int templateId, TemplateModel templateModel, boolean inlineCss) {
        this(fromAddress, toAddress, replyToAddress, ccAddress, null, tag, headers, templateId, templateModel, inlineCss);
    }



    public PostmarkTemplate(String fromAddress, String toAddress, String replyToAddress, String ccAddress, String tag, int templateId, TemplateModel templateModel, boolean inlineCss) {
        this(fromAddress, toAddress, replyToAddress, ccAddress, null, tag, null, templateId, templateModel, inlineCss);
    }

    public void clean() {
        super.clean();
    }

    /**
     * @return the template id.
     */
    public int getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId The template id to render the email.
     */
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the template model.
     */
    public TemplateModel getTemplateModel() {
        return templateModel;
    }

    /**
     * @param templateModel the template model
     */
    public void setTemplateModel (TemplateModel templateModel) {
        this.templateModel = templateModel;
    }

    /**
     * @return if email styling should be inlined CSS.
     */
    public boolean getInlineCss() {
        return inlineCss;
    }

    /**
     * @param inlineCss set if email styling should be inlined CSS.
     */
    public void setInlineCss(boolean inlineCss) {
        this.inlineCss = inlineCss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostmarkTemplate that = (PostmarkTemplate) o;

        if (ccAddress != null ? !ccAddress.equals(that.ccAddress) : that.ccAddress != null) return false;
        if (bccAddress != null ? !bccAddress.equals(that.bccAddress) : that.bccAddress != null) return false;
        if (fromAddress != null ? !fromAddress.equals(that.fromAddress) : that.fromAddress != null) return false;
        if (toAddress != null ? !toAddress.equals(that.toAddress) : that.toAddress != null) return false;
        if (tag != null ? !tag.equals(that.toAddress) : that.tag != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        if (replyToAddress != null ? !replyToAddress.equals(that.replyToAddress) : that.replyToAddress != null) return false;

        if (templateId != that.templateId) return false;
        if (inlineCss != that.inlineCss) return false;

        //Need a check for templateModel

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromAddress != null ? fromAddress.hashCode() : 0;
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + (ccAddress != null ? ccAddress.hashCode() : 0);
        result = 31 * result + (bccAddress != null ? bccAddress.hashCode() : 0);
        result = 31 * result + (replyToAddress != null ? replyToAddress.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);

        result = 31 * result + (templateModel != null ? templateModel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PostmarkTemplate");
        sb.append("{ fromAddress='").append(fromAddress).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append(", ccAddress='").append(ccAddress).append('\'');
        sb.append(", bccAddress='").append(bccAddress).append('\'');
        sb.append(", replyToAddress='").append(replyToAddress).append('\'');

        sb.append(", templateId=").append(templateId);
        sb.append(", templateModel='").append(templateModel).append('\'');
        sb.append(", inlineCss=").append(inlineCss);

        sb.append(", tag='").append(tag).append('\'');
        sb.append(", headers=").append(headers);
        sb.append('}');
        return sb.toString();
    }

}


