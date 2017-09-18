package com.company;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;


public class Connection {


    public static void main(String[] args) throws Exception {
        Connection http = new Connection();


        http.sendGet();
        http.myPostMethod("http://demo.edument.se/api/highscore", "{\"Name\":\"Jon\",\"Score\":95}");
        http.retrieveList("http://demo.edument.se/api/highscores");

    }

    //REQUEST LIST

    private void retrieveList(String targetURL) throws IOException {

        System.out.println("Sending http GET request for List");
        URL url = new URL(targetURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.println(response.toString());
        System.out.println();

    }

    // REQUEST HEADER

    private void sendGet() throws Exception {

        String url = "http://demo.edument.se";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // request header
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Sending GET request to URL :" + url);
        System.out.println("Response code :" + responseCode);

    }

    // POST

    public void myPostMethod(String targetURL, String urlParameters) throws IOException {


        URL url = new URL(targetURL);

        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        System.out.println("Sending http POST request");
        System.out.println("Post Data : " + urlParameters);
        System.out.println("Response Code: " + connection.getResponseCode());
    }


}


