package net.naijatek.myalumni.util.encryption;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Encoder {

    private static Log logger = LogFactory.getLog(Encoder.class);

    // Please note that 2 below methods are used in #getMD5_Base64 only
    // use them in other methods will make it not thread-safe
    private static MessageDigest digest = null;
    private static boolean isInited = false;

    private Encoder() {
    }

    public static synchronized String getMD5_Base64(final String input) {
        // please note that we dont use digest, because if we
        // cannot get digest, then the second time we have to call it
        // again, which will fail again
        if (isInited == false) {
            isInited = true;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (Exception ex) {
                logger.error("Cannot get MessageDigest. Application may fail to run correctly.", ex);
            }
        }
        if (digest == null) {
          return input;
        }
        // now everything is ok, go ahead
        try {
            digest.update(input.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException ex) {
            logger.error("Assertion: This should never occur.");
        }
        byte[] rawData = digest.digest();
        byte[] encoded = Base64.encode(rawData);
        String retValue = new String(encoded);
        return retValue;
    }

//    public static String encodeURL(String input) {
//       return URLEncoder.encode(input);
//    }

 /*   public static void main(final String[] args) {
        //test data should be
        //a1            iou3zTQ6oq2Zt9diAwhXog==
        //Hello World   sQqNsWTgdUEFt6mb5y4/5Q==
        String testString[] = {"$john","$jack","$mary","$steve"};

        for (String element : testString) {
            String encrypted = getMD5_Base64(element);
            logger.debug("encrypted = " + encrypted);
            logger.debug("length = " + encrypted.length());
        }
    }*/
      
}
