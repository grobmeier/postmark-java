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

/**
 * A Java wrapper for the <a href="http://postmarkapp.com">postmarkapp.com</a>
 * email send API.
 * <p>
 * <!-- Description from the API documentation home page -->
 * Postmark helps deliver and track transactional emails for web applications.
 * In a nutshell, the service replaces SMTP (or Sendmail) with a far more
 * reliable, scalable and care-free environment. In addition, you can track
 * statistics such as number of emails sent or processed, opens, bounces and
 * spam complaints.
 * <p>
 * Please read the
 * <a href="http://developer.postmarkapp.com/index.html">"Things you should
 * know"</a> section of the developer documentation in its entirety, but
 * principally:
 * <ul>
 *     <li>Postmark is for transactional email only. You may not send
 *     newsletters, announcements, or any other bulk email.</li>
 *     <li>A confirmed email address is required to start sending.</li>
 * </ul>
 *
 * @see <a href="http://postmarkapp.com/">Postmark</a>
 * @see <a href="http://jaredholdcroft.github.io/postmark-java/"/>Documentation on GitHub</a>
 * @see <a href="http://developer.postmarkapp.com/">Postmark API documentation</a>
 */
package com.postmark.java;
