package br.gov.sead.auth.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DecodeToken {
    public String preferred_username;
    public String[] groups;

    public static DecodeToken getDecoded(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String stringToParse = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);

        return new Gson().fromJson(stringToParse, DecodeToken.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
