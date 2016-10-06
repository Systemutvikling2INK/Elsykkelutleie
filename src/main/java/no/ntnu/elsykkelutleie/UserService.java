package no.ntnu.elsykkelutleie;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ingunn on 28.09.2016.
 */

@Path("/User/")
public class UserService {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void regUser(User user) {
        JSONObject obj = new JSONObject();
        obj.put("username", user.getUsername());
        obj.put("password", user.getPassword());

        try  {
            FileWriter file = new FileWriter("C:/Users/Ingunn/Systemutvikling 2/Case1/src/main/webapp/Login/Login.json");
            file.write(obj.toJSONString());
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    @POST
    @Path("/{username}/{passord}")
    public void regUser2(@PathParam("username") String username, @PathParam("passord") String passord) {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", passord);

        try  {
            FileWriter file = new FileWriter("C:/Users/Ingunn/Systemutvikling 2/Case1/src/main/webapp/Login/Login.json");
            file.write(obj.toJSONString());
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
