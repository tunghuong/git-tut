package network;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tungb on 12/29/2016.
 */
public class UserConnection {
    private static final String API_URL =  "http://gentle-stream-41670.herokuapp.com/users";
    private HttpURLConnection con;
    private int statusCode;
    private StringBuilder data;

    public static final UserConnection instance = new UserConnection();

    public UserConnection() {
        data = new StringBuilder();
    }

    public void connect(){
        URL url = null;
        try {
            url = new URL(API_URL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            statusCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasConnection() {
        if(statusCode == 200)
            return true;
        return false;
    }

    public User[] getTopUsers(){
        BufferedReader input = null;
        try {
            URL url = null;
            url = new URL(API_URL);
            con = (HttpURLConnection) url.openConnection();
            input = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while((line = input.readLine()) != null){
                data.append(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(data.toString()));
        reader.setLenient(true);
        User[] topUsers = gson.fromJson(reader, User[].class);
        return topUsers;
    }

    public void postUser(User user){
        System.out.println("post User");
        try {
            URL url = null;
            url = new URL(API_URL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String params = "name=" + user.getName() +
                    "&score=" + user.getScore();
            con.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
            outputStream.writeBytes(params);
            outputStream.flush();
            outputStream.close();
            //get respone
            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String line = null;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            System.out.println(response);
            in.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
