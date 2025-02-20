/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import sample.user.UserGGDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author ANPHUOC
 */
public class Iconstant {

    private static final String GOOGLE_CLIENT_ID = ${oauth2.google.client_id};

    private static final String GOOGLE_CLIENT_SECRET = ${oauth2.google.client_secret};

    private static final String GOOGLE_REDIRECT_URI = ${url.base} + "/PRJ-T4S3-JSP/LoginGoogleController";

    private static final String GOOGLE_GRANT_TYPE = "authorization_code";

    private static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    private static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

   //getToken from requestCode to GG
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                        .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString(); //get response as String

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
     //use authToken from GG to getUserInfo
    public static UserGGDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGGDTO userInfomation = new Gson().fromJson(response, UserGGDTO.class);

        return userInfomation;
    }
}
