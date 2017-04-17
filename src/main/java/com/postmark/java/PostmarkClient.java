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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class that does the heavy lifting
 */
public class PostmarkClient {

    public static final String API_ENDPOINT = "https://api.postmarkapp.com";

    private static Logger logger = Logger.getLogger("com.postmark.java");
    private String serverToken;
    private String serverPath;

    private static GsonBuilder gsonBuilder = new GsonBuilder();

    static {
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter());
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setExclusionStrategies(new SkipMeExclusionStrategy(Boolean.class));
        gsonBuilder.disableHtmlEscaping();

        logger.addHandler(new ConsoleHandler());
        logger.setLevel(Level.ALL);
    }


    /**
     * Initializes a new instance of the PostmarkClient class.
     * <p/>
     * If you do not have a server token you can request one by signing up to
     * use Postmark: http://postmarkapp.com.
     *
     * @param serverToken the postmark server token
     */
    public PostmarkClient(String serverToken) {
        this(serverToken, API_ENDPOINT);
    }

    /**
     * Initializes a new instance of the PostmarkClient class.
     * <p/>
     * If you do not have a server token you can request one by signing up to
     * use Postmark: http://postmarkapp.com.
     *
     * @param serverToken the postmark server token
     * @param serverPath an alternative server path e.g https://api.postmarkapp.com
     */
    public PostmarkClient(String serverToken, String serverPath) {
        this.serverToken = serverToken;
        this.serverPath = serverPath;
    }

    /**
     * Sends a message through the Postmark API.
     * All email addresses must be valid, and the sender must be
     * a valid sender signature according to Postmark. To obtain a valid
     * sender signature, log in to Postmark and navigate to:
     * http://postmarkapp.com/signatures.
     *
     * @param from    An email address for a sender
     * @param to      An email address for a recipient
     * @param replyTo An email address for the reply-to
     * @param cc      An email address for CC
     * @param subject The message subject line
     * @param body    The message body
     * @param isHTML  Is the body text HTML
     * @param tag     A tag to identify the message in postmark
     * @return {@link PostmarkResponse} with details about the transaction
     */
    public PostmarkResponse sendMessage(String from, String to, String replyTo, String cc, String subject, String body, boolean isHTML, String tag) throws PostmarkException {
        return sendMessage(from, to, replyTo, cc, null, subject, body, isHTML, tag, null);
    }

    /**
     * Sends a message through the Postmark API.
     * All email addresses must be valid, and the sender must be
     * a valid sender signature according to Postmark. To obtain a valid
     * sender signature, log in to Postmark and navigate to:
     * http://postmarkapp.com/signatures.
     *
     * @param from    An email address for a sender
     * @param to      An email address for a recipient
     * @param replyTo An email address for the reply-to
     * @param cc      An email address for CC
     * @param subject The message subject line
     * @param body    The message body
     * @param isHTML  Is the body text HTML
     * @param tag     A tag to identify the message in postmark
     * @param headers A collection of additional mail headers to send with the message
     * @return {@link PostmarkResponse} with details about the transaction
     */
    public PostmarkResponse sendMessage(String from, String to, String replyTo, String cc, String subject, String body, boolean isHTML, String tag, List<NameValuePair> headers) throws PostmarkException {
        return sendMessage(from, to, replyTo, cc, null, subject, body, isHTML, tag, headers);
    }

    /**
     * Sends a message through the Postmark API.
     * All email addresses must be valid, and the sender must be
     * a valid sender signature according to Postmark. To obtain a valid
     * sender signature, log in to Postmark and navigate to:
     * http://postmarkapp.com/signatures.
     *
     * @param from    An email address for a sender
     * @param to      An email address for a recipient
     * @param replyTo An email address for the reply-to
     * @param cc      An email address for CC
     * @param bcc     An email address for BCC
     * @param subject The message subject line
     * @param body    The message body
     * @param isHTML  Is the body text HTML
     * @param tag     A tag to identify the message in postmark
     * @param headers A collection of additional mail headers to send with the message
     * @return {@link PostmarkResponse} with details about the transaction
     */
    public PostmarkResponse sendMessage(String from, String to, String replyTo, String cc, String bcc, String subject, String body, boolean isHTML, String tag, List<NameValuePair> headers) throws PostmarkException {
        PostmarkMessage message = new PostmarkMessage(from, to, replyTo, subject, bcc, cc, body, isHTML, tag, headers);
        return sendMessage(message);
    }

    /**
     * Sends a template message through the Postmark API.
     * All email addresses must be valid, and the sender must be
     * a valid sender signature according to Postmark. To obtain a valid
     * sender signature, log in to Postmark and navigate to:
     * http://postmarkapp.com/signatures.
     *
     * @param templateMessage A prepared template message instance.</param>
     * @return A response object
     */
    public PostmarkResponse sendMessage(PostmarkTemplate templateMessage) throws PostmarkException {
        return sendPostmarkMessage("/email/withTemplate", templateMessage);
    }

    /**
     * Sends a message through the Postmark API.
     * All email addresses must be valid, and the sender must be
     * a valid sender signature according to Postmark. To obtain a valid
     * sender signature, log in to Postmark and navigate to:
     * http://postmarkapp.com/signatures.
     *
     * @param message A prepared message instance.</param>
     * @return A response object
     */
    public PostmarkResponse sendMessage(PostmarkMessage message) throws PostmarkException {
        return sendPostmarkMessage("/email", message);
    }

    private PostmarkResponse sendPostmarkMessage(String endpoint, PostmarkMessageBase message ) throws PostmarkException {

        HttpClient httpClient = new DefaultHttpClient();
        PostmarkResponse theResponse = new PostmarkResponse();

        try {

            // Create post request to Postmark API endpoint
            HttpPost method = new HttpPost(serverPath+endpoint);

            // Add standard headers required by Postmark
            method.addHeader("Accept", "application/json");
            method.addHeader("Content-Type", "application/json; charset=utf-8");
            method.addHeader("X-Postmark-Server-Token", serverToken);
            method.addHeader("User-Agent", "Postmark-Java");

            // Validate and clean the message
            message.validate();
            message.clean();

            // Convert the message into JSON content
            Gson gson = gsonBuilder.create();
            String messageContents = gson.toJson(message);
            logger.info("Message contents: " + messageContents);

            // Add JSON as payload to post request
            StringEntity payload = new StringEntity(messageContents, "UTF-8");
            method.setEntity(payload);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    if (status >= 200 && status < 300) {
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        Scanner s = new Scanner(entity.getContent()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        // Output body in case of trouble for further debugging
                        logger.warning(result);

                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            
            try {
                String response = httpClient.execute(method, responseHandler);
                logger.info("Message response: " + response);
                theResponse = gsonBuilder.create().fromJson(response, PostmarkResponse.class);
                theResponse.status = PostmarkStatus.SUCCESS;
            } catch (HttpResponseException hre) {
                switch(hre.getStatusCode()) {

                    case 401:
                    case 422:
                        logger.log(Level.SEVERE, "There was a problem with the email: " + hre.getMessage());
                        theResponse.setMessage(hre.getMessage());
                        theResponse.status = PostmarkStatus.USERERROR;
                        throw new PostmarkException(hre.getMessage(), theResponse);
                    case 500:
                        logger.log(Level.SEVERE, "There has been an error sending your email: " + hre.getMessage());
                        theResponse.setMessage(hre.getMessage());
                        theResponse.status = PostmarkStatus.SERVERERROR;
                        throw new PostmarkException(hre.getMessage(), theResponse);
                    default:
                        logger.log(Level.SEVERE, "There has been an unknow error sending your email: " + hre.getMessage());
                        theResponse.status = PostmarkStatus.UNKNOWN;
                        theResponse.setMessage(hre.getMessage());
                        throw new PostmarkException(hre.getMessage(), theResponse);
                }
            }

        } catch (PostmarkException e) {
            //Log it and rethrow it, don't wrap it
            logger.log(Level.SEVERE, "There has been an error sending your email: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "There has been an error sending your email: " + e.getMessage());
            throw new PostmarkException(e);
        }
        finally {

            httpClient.getConnectionManager().shutdown();
        }

        return theResponse;
    }
}
