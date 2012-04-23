README
======

This library allows you to leverage the Postmark REST API to send emails.

Example usage:

```java
List<NameValuePair> headers = new ArrayList<NameValuePair>();

headers.add(new NameValuePair("HEADER", "test"));

PostmarkMessage message = new PostmarkMessage("TO_ADDRESS",
        "FROM_ADDRESS",
        "REPLY_TO_ADDRESS",
        null,
        "SUBJECT",
        "CONTENT",
        false,
        null,
        headers);

PostmarkClient client = new PostmarkClient("POSTMARK_API_TEST");

try {
       client.sendMessage(message);
} catch (PostmarkException pe) {
       System.out.println("An error has occured : " + pe.getMessage());
}
```

Attachments can also be sent:

```java
Attachment attachment = new Attachment();
attachment.setContentType("application/pdf");
attachment.setName("SomethingAwesome.pdf");

// convert file contents to base64
File file = new File("SomethingAwesome.pdf");
byte[] ba = new byte[(int) file.length()];
FileInputStream fis = new FileInputStream(file);
Fileis.read(ba);
fis.close();
attachment.setContent(new Base64().encodeToString(ba));

// can handle multiple attachments
Vector<Attachment> v = new Vector<Attachment>();
v.add(attachment);
message.setAttachments(v);
```

Dependencies:

All dependencies are included in the lib folder
