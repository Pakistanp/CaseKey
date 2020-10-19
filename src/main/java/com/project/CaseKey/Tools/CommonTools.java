package com.project.CaseKey.Tools;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CommonTools {

   public static JSONObject readJsonFromUrl (String urlString) {
      JSONObject json = new JSONObject();
      try {
         URL url = new URL(urlString);
         URLConnection urlConnection = url.openConnection();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
         String jsonText = readAll(bufferedReader);
         json = new JSONObject(jsonText);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return json;
   }

   private static String readAll(Reader rd) throws IOException {
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != -1) {
         sb.append((char) cp);
      }
      return sb.toString();
   }
}
