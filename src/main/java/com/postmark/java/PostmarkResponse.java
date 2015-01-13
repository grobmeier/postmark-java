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
import org.joda.time.DateTime;

/**
 * Class that wraps the Postmark response
 */

public class PostmarkResponse {

    /**
     * The status outcome of the response.
     */
    @SerializedName("Status")
    public PostmarkStatus status;

    /**
     * The message from the API.
     * In the event of an error, this message may contain helpful text.
     */
    @SerializedName("Message")
    public String message;

    /**
     * The message ID of the sent message.
     * This may be used to identify the message to other API endpoints not yet
     * supported here.
     */
    @SerializedName("MessageID")
    public String messageId;

    /**
     * The time the request was received by Postmark.
     */
    @SerializedName("SubmittedAt")
    public DateTime submittedAt;

    /**
     * The recipient of the submitted request.
     */
    @SerializedName("To")
    public String to;

    /**
     * The error code returned from Postmark.
     * This does not map to HTTP status codes.
     */
    @SerializedName("ErrorCode")
    public int errorCode;

    public PostmarkStatus getStatus() {
        return status;
    }

    public void setStatus(PostmarkStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(DateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PostmarkResponse");
        sb.append("{ status=").append(status);
        sb.append(", to='").append(to).append('\'');
        sb.append(", submittedAt='").append(submittedAt).append('\'');
        sb.append(", messageId='").append(messageId).append('\'');
        sb.append(", errorCode=").append(errorCode);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
