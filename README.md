Introduction
------------

This mock email service helps to simplify email test automation. 

In QA environments, configure your application to this mock (instead of real  SendGrid service). When application
running in QA environment sends/post message to this mock service, the messages are just stored in memory
(concurrent hashmap) and they are NOT delivered to actual email address. 

The test scripts can read (or) clear messages using REST end-points. This means no need to poll mail servers (Gmail etc) for messages.

Send/Post Message URL
---------------------

SendGrid: https://api.sendgrid.com/api/mail.send.json

Mock Service: http://<<Mock_Service_Host>>/api/mail.send.json/

Method: POST

Content-Type: application/x-www-form-urlencoded

Sample message body:

api_user=your_sendgrid_username&api_key=your_sendgrid_password&to=destination@example.com&toname=Destination&subject=Example_Subject&text=testingtextbody&from=info@domain.com&html=<html><body><p>testing html body</p></body></html>

Response: 

{
    "message": "success",
    "errors": []
}


Read Message URL
----------------

Mock Service: http://<<Mock_Service_Host>>/api/mail.read.json/<<Email_Address>>


Response:

{"mails":[{"to":"destination@example.com","subject":"Example_Subject","text":"testingtextbody","html":"<html><body><p>testing html body</p></body></html>","from":"info@domain.com","bcc":null,"date":null,"files":null,"content":null,"headers":null,"toname":"Destination","x-smtpapi":null,"fromname":null,"replyto":null}]}


Clear Messages URL
------------------

Mock Service: http://<<Mock_Service_Host>>/api/mail.clear.json/<<Email_Address>>


Response:

{"message":"success"}

