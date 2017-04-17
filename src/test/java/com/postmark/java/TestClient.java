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

import java.util.*;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.annotations.SerializedName;

import static org.hamcrest.Matchers.*;
/**
 * Command-line test client.
 */
public class TestClient {

    String apiKey = "POSTMARK_API_TEST";

    @Test
    public void testMessage() throws Exception
    {
        List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new NameValuePair("HEADER", "test"));

        PostmarkMessage message = new PostmarkMessage(
                "team@company.com",
                "user@email.com",
                null,
                null,
                null,
                "Test Subject",
                "<h1>Test heading</h1>",
                true,
                null,
                null);


        PostmarkClient client = new PostmarkClient(apiKey);

        client.sendMessage(message);
    }

    //@Test , disabled until Postmark enables testing against their email/withTemplate endpoint
    public void testTemplate() throws Exception
    {
        List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new NameValuePair("HEADER", "test"));

        PostmarkTemplate pTemplate = new PostmarkTemplate(
            "team@company.com",
            "user@email.com",
            null,
            null,
            null,
            null,
            headers,
            1,
            new TestModel(),
            true
        );

        PostmarkClient client = new PostmarkClient(apiKey);

        client.sendMessage(pTemplate);
    }

    public static class TestModel implements TemplateModel
    {
        @SerializedName("name")
        public String name = "Mary";

        @SerializedName("help_url")
        public String helpUrl = "https://postmarkapp.com";
    }
}
