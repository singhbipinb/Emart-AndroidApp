//package com.emart.emartindia.Controllers;
//
//import com.emart.emartindia.apiclient.ProductInterface;
//import com.emart.emartindia.apiclient.apiClient;
//import com.emart.emartindia.models.Products;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class ProductController {
//
//    final ProductInterface apiser = apiClient.getClient().create(ProductInterface.class);
//    Gson gson = new Gson();
//
//    public ArrayList<Products> GetProducts(){
//
////        ArrayList<Products> userArray = null;
//
//        Thread thread= new Thread(){
//
//            ArrayList<Products> userArray = null;
//
//            @Override
//            public void run() {
//
//                StringBuilder res = new StringBuilder();
//                URL url = null;
//                try {
//                    url = new URL("http://10.0.2.2:5000/api/products/");
//
//                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                    httpURLConnection.setRequestMethod("GET");
//                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        BufferedReader input = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//                        String s;
//                        while ((s = input.readLine()) != null) {
//                            res.append(s);
//                        }
//                    }
//
//                    JSONObject object = new JSONObject(res.toString());
//
//                    Type userListType = new TypeToken<ArrayList<Products>>(){}.getType();
//
//                     userArray = gson.fromJson(object.get("products").toString(), userListType);
//
////                    System.out.println("Get State"+userArray.get(0).getBrand());
//
//
//
//                } catch (MalformedURLException | ProtocolException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            public ArrayList<Products> getUserArray(){
//
//                return userArray;
//            }
//
//        };
//
////        thread.start();
////        thread.join();
////        return thread.getUserArray();
//
//    }
//
////
////    public static void AllProduct(){
////        List<Products> products= new ArrayList<>();
////
////        Call<List<Products>> call = apiser.GetAllProduct();
////        call.enqueue(new Callback<List<Products>>() {
////            @Override
////            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
////                System.out.println(response.body().get(0).getBrand());
////            }
////
////            @Override
////            public void onFailure(Call<List<Products>> call, Throwable t) {
////                System.out.println("Nhi ho rha");
////                System.out.println(""+t);
////
////            }
////        });
////    }
//
//}
