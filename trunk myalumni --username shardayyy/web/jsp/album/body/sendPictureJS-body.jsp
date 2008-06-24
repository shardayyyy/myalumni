<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>


<form method="POST">
        <center>
          <p>
            <input TYPE="button" class="button" onClick="alert('\nSending Pictures \n Electronic Copies: Send email to <bean:message key="email.webmaster"/> and include the file as an attachment. \n\rHard Copies: Send email to <bean:message key="email.webmaster"/> Subject of email: \n\rPictures:Need Mailing Address you should get the mailing address within 48hrs. \n\rWe are continously looking for pictures, old and new, individual, group or of our \n\rschool for the album. Please include the details for each picture: Names, When and Where.')" VALUE="Picture Details">
          </p>
        </center>
</form>