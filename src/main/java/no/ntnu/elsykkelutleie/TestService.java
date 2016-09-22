package no.ntnu.elsykkelutleie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Knut on 22.09.2016.
 */
@Path("/test")
public class TestService {

    private static String testMessage = "test1234";

    @GET
    @Path("/testmessage")
    public String getMessageTest() {
        return testMessage;
    }
}
