package br.com.restassuredapitest.testes.auth.requests.payload;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthPayloads {
        public JSONObject jsonAuthLogin(String username, String password) throws JSONException {
            JSONObject payloadLogin = new JSONObject();

            payloadLogin.put("username",username);//"admin"
            payloadLogin.put("password",password);//"password123"

        return payloadLogin;
        }

}
