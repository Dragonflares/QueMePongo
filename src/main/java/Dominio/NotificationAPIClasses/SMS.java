package Dominio.NotificationAPIClasses;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
	private static final String ACCOUNT_SID = "AC5572b96e9cc6ea9b54e166d120c8b6fd";
	private static final String AUTH_TOKEN = "bc90088058057beea12c938e616dd923";

	public void enviarSMS(String emisor, String destinatario, String mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(destinatario), // to
                        new PhoneNumber(emisor), // from
                        mensaje)
                .create();
	}
}
