package controllers;

import models.PointOfInterest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import play.data.Upload;
import play.mvc.Controller;
import services.FileUploader;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Leo on 15/01/16.
 */
public class API  extends Controller {


    public static void interest() {
        List<PointOfInterest> points = PointOfInterest.all().fetch();
        renderJSON(points);
    }
    public static void addInterest(String tag,
                                    Double latitude, Double longitude,
                                    String photo,
                                    Double x1, Double x2, Double y1, Double y2,
                                    String username) {

        Map result = new HashMap<String, Object>();
        if(latitude == null  || longitude == null
                || photo == null
                || tag == null
                || x1 == null
                || x2 == null
                || y1 == null
                || y2 == null
                || username == null) {
            System.out.println(tag);//, latitude, longitude, photo, username
            System.out.println(latitude);//, , longitude, photo, username
            System.out.println(longitude);//, latitude, , photo, username
            System.out.println(photo);//, latitude, longitude, , username
            System.out.println(username);//, latitude, longitude, photo,
            result.put("status", "failed : missing parameters");
        } else {
            try {
                InputStream photoStream = new ByteArrayInputStream(Base64.decodeBase64(photo.getBytes("UTF-8")));

                String imageURL = FileUploader.uploadMediaFile(photoStream);
                PointOfInterest point = new PointOfInterest();
                point.lat = latitude;
                point.lng = longitude;
                point.tag = tag;
                point.pseudo = username;
                point.x1 = x1;
                point.x2 = x2;
                point.y1 = y1;
                point.y2 = y2;
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
}