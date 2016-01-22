package controllers;

import com.amazonaws.util.Base64;
import models.PointOfInterest;
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

                InputStream photoStream = new ByteArrayInputStream(
                        Base64.decode(
                                URLDecoder.decode(photo)
                        )
                );

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

    public static byte[] decodeUrlSafe(byte[] data) {
        byte[] encode = Arrays.copyOf(data, data.length);
        for (int i = 0; i < encode.length; i++) {
            if (encode[i] == '-') {
                encode[i] = '+';
            } else if (encode[i] == '_') {
                encode[i] = '/';
            }
        }
        return Base64.decode(encode);
    }
}