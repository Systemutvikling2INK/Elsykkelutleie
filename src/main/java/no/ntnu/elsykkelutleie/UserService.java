package no.ntnu.elsykkelutleie;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ingunn on 28.09.2016.
 */

@Path("/User/")
public class UserService {

    String fileLocation = "C:\\Users\\Knut\\Documents\\GitHub\\Elsykkelutleie\\src\\main\\webapp\\Login\\Login.json";

    //[{"username": "heia", "password": "hehh"}, {"username": "admin", "password": "admin"}]

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void regUser(User user) {
        JSONArray jsonArray = getUserList();

        JSONObject obj = new JSONObject();
        obj.put("username", user.getUsername());
        obj.put("password", user.getPassword());

        jsonArray.add(obj);

        try  {
            FileWriter file = new FileWriter(fileLocation);//C:/Users/Ingunn/Systemutvikling 2/Case1/src/main/webapp/Login/Login.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    private JSONArray getUserList() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            Object object = parser.parse(new FileReader(fileLocation));
            jsonArray = (JSONArray)object;
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(jsonArray.toString());
        return jsonArray;
    }

    @POST
    @Path("/{username}/{passord}")
    public void regUser2(@PathParam("username") String username, @PathParam("passord") String passord) {
        JSONArray jsonArray = getUserList();

        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", passord);

        jsonArray.add(obj);

        try  {
            FileWriter file = new FileWriter(fileLocation);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
            System.out.println(obj);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
