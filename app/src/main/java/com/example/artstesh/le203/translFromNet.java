package com.example.artstesh.le203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by artstesh on 06.06.2016.
 */
public class translFromNet
{


    private static String modifyWord(String modifWord)
    {
        char[] arChar = modifWord.toCharArray();
        String tmp = "";
       for (char aChar : arChar)
       {
          if (Pattern.matches ("[A-Za-z]", Character.toString (aChar)))
          {
             tmp += aChar;
          }
       }
        return tmp.toLowerCase();
    }


    public static String translate (String word) throws UnsupportedEncodingException
    {
        String newString;

            String html1 = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=&text=";
            String html2 = LEActivity.translationLang;

            if(html2.isEmpty()) html2= "en";

            word = modifyWord(word);

            String html3 = "&lang=";
            String tempXML = getHTML (html1 + word + html3 + html2 + "-ru");

            String[] massXML = tempXML.split("\"", 11);
       if(massXML.length > 9)
       {
          System.out.println(massXML[9]);
          newString = massXML[9];
       }
       else
       {
          newString = "----";
       }
            //String[] itog = massXML[8].split(">");

       /*

            String html1 = "https://dictionary.yandex.net/api/v1/dicservice/lookup?key=dict.1.1.20150516T072346Z.2278191af0df2944.bc289dd5bb8596e86a10cf77eb777b66733a05b3&lang=";
            String html2 = null;


            if (bEN.isSelected()) {html2 = "en";}
            if (bDE.isSelected()) {html2 = "de";}
            if (bPL.isSelected()) {html2 = "pl";}

            String html3 = "-ru&text=";



            String tempXML = getHTML (html1 + html2 + html3 + word);
            String[] massXML = tempXML.split("<", 10);
            System.out.println (massXML[8]);
            String[] itog = massXML[8].split(">");
            newString = new String(itog[1].getBytes("Cp1251"), "UTF-8");
            //newString = itog[1];
*/

       System.out.println("Variant of Net is " + newString);
        return newString;
    }


    private static String getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = rd.readLine()) != null) {
                result += line;

            }

            System.out.println(result);
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
