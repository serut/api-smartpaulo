package controllers;

import com.amazonaws.util.Base64;
import models.PointOfInterest;
import org.apache.commons.codec.binary.Base64OutputStream;
import play.mvc.Controller;
import services.FileUploader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Leo on 15/01/16.
 */
public class API  extends Controller {


    public static void interest() {
        List<PointOfInterest> points = PointOfInterest.all().fetch();
        renderJSON(points);
    }
    public static void addInterest(String tags,
                                    Double latitude, Double longitude,
                                    String photo,
                                    Double zone_latitude1, Double zone_longitude1, Double zone_latitude2, Double zone_longitude2,
                                    String username) {
        Map result = new HashMap<String, Object>();
        if(tags == null
                || latitude == null  || longitude == null
                || photo == null
                || username == null) {
            System.out.println(tags);//, latitude, longitude, photo, username
            System.out.println(latitude);//, , longitude, photo, username
            System.out.println(longitude);//, latitude, , photo, username
            System.out.println(photo);//, latitude, longitude, , username
            System.out.println(username);//, latitude, longitude, photo,
            result.put("status", "failed : missing parameters");
        } else {
            try {
                byte[] bytes = new byte[0];
                try {
                    bytes = (photo.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String img = DatatypeConverter.printBase64Binary(bytes);
                InputStream photoStream = new ByteArrayInputStream(Base64.decode(photo.getBytes()));

                String imageURL = FileUploader.uploadMediaFile(photoStream);
                PointOfInterest point = new PointOfInterest();
                point.lat = latitude;
                point.lng = longitude;
                point.pseudo = username;
                point.url = imageURL;
                point.save();

                result.put("status", "ok");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "failed to save");
            }
        }
        renderJSON(result);
    }
    private static String convertToBase64(String s) {
        byte[] bytes = new byte[0];
        try {
            bytes = (s.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DatatypeConverter.printBase64Binary(bytes);
    }
}