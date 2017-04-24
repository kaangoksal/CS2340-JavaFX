package sample.model;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sun.misc.BASE64Encoder;

/**
 * Created by Alex Thien An Le on 2/23/2017.
 * Modified by Kaan Goksal
 * This file has the http requests to communicate with the server.
 */

public class ServerConnector {
    /**
     * Attempts login to the server and returns the full credentials of the user
     * @param user the user
     * @return a user with many details as possible
     * @throws IOException if something goes bad
     */
    public static User attemptLogin(User user) throws IOException {

        Call.Factory client = new OkHttpClient();

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";


        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/login")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        JSONObject received;
        User retrieved_user = null;
        try {

            received = new JSONObject(responseString);
            retrieved_user = new User(received.getString("email"), received.getString("password"));
            retrieved_user.setUsername(received.getString("username"));

            received.getString("created_at");

            String title =  received.getString("account_type");
            switch (title) {
                case "User":
                case "user":
                    retrieved_user.setTitle(Title.USER);
                    break;
                case "Worker":
                case "worker":
                    retrieved_user.setTitle(Title.WORKER);
                    break;
                case "Admin":
                case "admin":
                    retrieved_user.setTitle(Title.ADMIN);
                    break;
                case "Manager":
                case "manager":
                    retrieved_user.setTitle(Title.MANAGER);
                    break;
            }

            System.out.println("ServerConnector " + " JsonObj " + received.toString());

        } catch (JSONException E){
           System.out.print("Login Error");
        }

        return retrieved_user;

    }

    /**
     * Registers user to the server
     * @param user the user
     * @return whether the user could be added or not
     * @throws IOException if the user could not be added or failure to reach server.
     */
    public static boolean addUser(User user)
            throws IOException{


        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";
        try {
            JSONObject bodyJson = new JSONObject();
            bodyJson.put("email", user.getEmail());
            bodyJson.put("password", user.getPassword());
            bodyJson.put("username", user.getUsername());
            bodyJson.put("token", "");
            bodyJson.put("address", user.getAddress());
            bodyString = bodyJson.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/register")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println("Add user server response " + responseString);
        if (responseString.indexOf("Failed") > 0) {
            return false;
        } else if (responseString.indexOf("Account") > 0) {
            return true;
        }

        return false;
    }

    /**
     * edits user on the server
     * @param user username of the user
     * @return whether the operation was successful
     * @throws IOException if something goes bad
     */
    public static boolean editUser(User user) throws
            IOException{

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";
        try {
            JSONObject bodyJson = new JSONObject();
            bodyJson.put("email", user.getEmail());
            bodyJson.put("password", user.getPassword());
            bodyJson.put("username", user.getUsername());
            bodyJson.put("token", "");
            bodyString = bodyJson.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/edit_user")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();



        if (responseString.indexOf("Failed") > 0) {
            return false;
        } else if (responseString.indexOf("Successful") > 0) {
            return true;
        }

        return false;
    }

    /**
     * Adds a report
     * @param user user who is adding the report
     * @param reportJson the report as json
     * @return whether the operation was successful or not
     * @throws IOException if something goes bad like network
     */
    public static boolean addReport(User user, JSONObject reportJson) throws IOException{

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = reportJson.toString();

        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/add_water_report")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();



        if (responseString.indexOf("Failed") > 0) {
            return false;
        } else if (responseString.indexOf("Successful") > 0) {
            return true;
        }

        return false;
    }

    /**
     * gets all the reports regardless of what they are.
     * @param user user requesting the information
     * @return a json array of the reports
     * @throws IOException if something goes bad with the network
     */
    public static JSONArray getReports(User user) throws IOException{

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";

        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/get_water_reports")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();


        JSONObject received;
        JSONArray return_array = null;
        try {
            received = new JSONObject(responseString);

            return_array = received.getJSONArray("reports");
        } catch (JSONException E){

        }


        return return_array;

    }

    /**
     * Fetches the source reports from the server
     * @param user user requesting the reports
     * @return an array list of the reports
     * @throws IOException usually a network error causes this
     */
    public static ArrayList<WaterSourceReport> getSourceReports(User user) throws IOException{

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";

        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/get_water_source_reports")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();




        ArrayList<WaterSourceReport> return_array = new ArrayList<>();
        try {
            JSONObject received = new JSONObject(responseString);

            JSONArray jsonArray  = received.getJSONArray("reports");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject reportJsonChild = jsonArray.getJSONObject(i);
                WaterSourceReport new_report = WaterSourceReport.fromJSONObject(reportJsonChild);
                return_array.add(new_report);
                assert new_report != null;
                @SuppressWarnings("LawOfDemeter") String newReportToString = new_report.toString();

            }

        } catch (JSONException E){

        }


        return return_array;

    }

    /**
     * Fetches the purity reports from the server
     * @param user user requesting the reports
     * @return and array list of reports
     * @throws IOException network error.
     */
    public static ArrayList<WaterPurityReport> getPurityReports(User user) throws IOException{

        String base64_encoded = parseBase64BasicAuth(user);

        String bodyString = "";

        Call.Factory client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, bodyString);
        Request request = new Request.Builder()
                .url("http://umb.kaangoksal.com:5235/get_water_purity_reports")
                .post(body)
                .addHeader("Authorization", base64_encoded)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();



        ArrayList<WaterPurityReport> return_array = new ArrayList<>();
        try {
            JSONObject received = new JSONObject(responseString);
            JSONArray jsonArray = received.getJSONArray("reports");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject reportJsonChild = jsonArray.getJSONObject(i);


                return_array.add(WaterPurityReport.fromJSONObject(reportJsonChild));
                WaterPurityReport report = WaterPurityReport.fromJSONObject(reportJsonChild);

                if (report != null) {
                    @SuppressWarnings("LawOfDemeter") String jsonToString = report.toString();


                }

            }

        } catch (JSONException E){

        }


        return return_array;

    }

    private static String parseBase64BasicAuth(User user)  {
        String authentication = user.getEmail() + ":" + user.getPassword();

//        byte[] authentication_bytes;
//        try {
//            authentication_bytes = authentication.getBytes("UTF-8");
//        } catch (IOException E){
//            authentication_bytes ="Error".getBytes();
//        }

//
//        Base64.Encoder aa = Base64.getEncoder();
//        byte[] b = Auth.getBytes();
//        byte[] encoded = aa.encode(b);

        String base64_encoded = "";
        try {
            Base64.Encoder base64encoder = Base64.getEncoder();
            byte[] authbytes = authentication.getBytes();
            byte[] encoded = base64encoder.encode(authbytes);
            base64_encoded = new String(encoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error Encoding authentication");
        }



//        String base64_encoded = Base64.encodeToString(authentication_bytes, Base64.DEFAULT);
        base64_encoded = "Basic " + base64_encoded;
        base64_encoded = base64_encoded.replace("\n", "");
        return base64_encoded;
    }


}