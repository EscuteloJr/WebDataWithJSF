/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projecto.apiRequestTools;

import com.google.gson.Gson;
import com.projecto.api.model.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author escutelojr
 */
public class TeslaNews {

    public static List<Doc> retornarListaDeNews() {

        List<Doc> lista = new ArrayList<>();

        try {

            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            //API URL AQUI
            String url = "https://newsapi.org/v2/everything?q=apple&from=2022-04-28&to=2022-04-28&sortBy=popularity&apiKey=5e947ea1e0574f1d855e74b70d9955da";
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            } else {
                System.out.println("Conectado Com a API");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;
            Gson gson = new Gson();
            int count = 0;
            while ((line = br.readLine()) != null) {
                output = line;
            }
            Doc conteudos = gson.fromJson(new String(output.getBytes()), Doc.class);
            //List<Doc> conteudos = Arrays.asList(gson.fromJson(new String(output.getBytes()), Doc[].class));
            lista.add(conteudos);
            conn.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
        } catch (KeyManagementException ex) {
        }

        return lista;
    }

}
