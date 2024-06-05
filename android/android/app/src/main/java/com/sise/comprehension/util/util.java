package com.sise.comprehension.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import com.sise.comprehension.pojo.Classed;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class util {
    static String ip = "http://172.17.11.165:8080/android2_war_exploded/";

    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> Info toInfo(String Json, Class<T> T1) {
        Info info = gson.fromJson(Json, Info.class);
        if (info != null && "200".equals(info.getCode())) {
            Object o = gson.fromJson(gson.toJson(info.getPojo()), (Type) T1);
            info.setPojo(o);
        }
        return info;
    }

    public static String sendJson(String urlSuffix, String value) {
        String result = "";
        try {
            URL url = new URL(ip + urlSuffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/json;charset=UTF-8");
            OutputStream outputStream = connection.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream, "utf-8");
            writer.write(value);
            writer.flush();
            writer.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String text = "";
                while ((text = bufferedReader.readLine()) != null) {
                    result += text;
                }
                inputStreamReader.close();
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap getPicture(String pictureUrl) {
        try {
            URL url = new URL(pictureUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer mapToCustomer(Map<String, String> map) {
        try {
            Customer customer = new Customer();
            customer.setName(map.get("name"));
            customer.setRole(map.get("role"));
            customer.setSex(map.get("sex"));
            customer.setAvatar(map.get("avatar"));
            customer.setPassword(map.get("password"));
            customer.setNo(map.get("no"));
            customer.setId(map.get("id"));
            customer.setPhone(map.get("phone"));
            if (map.get("birthday") != null) {
                customer.setBirthday(map.get("birthday"));
            }
            if (map.get("enrollmentDate") != null) {
                customer.setEnrollmentDate(map.get("enrollmentDate"));
            }
            Classed classed = new Classed(map.get("id"), map.get("className"), map.get("teacher"), map.get("counselor"));
            customer.setClassed(classed);
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }

    public static Gson gson() {
        return gson;
    }

    public static String sendPicture(String urlSuffix, File file) {
        String result = "";
        try {
            URL url = new URL(ip + urlSuffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data;charset=UTF-8");
            OutputStream outputStream = connection.getOutputStream();
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte buffer[] = new byte[10240];
            while ((bytes = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytes);
            }
            outputStream.flush();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String text = "";
                while ((text = bufferedReader.readLine()) != null) {
                    result += text;
                }
                inputStreamReader.close();
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
