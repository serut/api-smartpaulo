package controllers;

import models.PointOfInterest;
import org.apache.commons.codec.binary.Base64OutputStream;
import play.mvc.Controller;
import services.FileUploader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
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
    public static void addIntereset(String tags,
                                    Double latitude, Double longitude,
                                    File photo,
                                    Double zone_latitude1, Double zone_longitude1, Double zone_latitude2, Double zone_longitude2,
                                    String username) {
        Map result = new HashMap<String, Object>();
        if(tags == null
                || latitude == null  || longitude == null
                || photo == null
                || username == null) {
            result.put("status", "failed");
        } else {
            String imageURL = FileUploader.uploadMediaFile(photo);
            PointOfInterest point = new PointOfInterest();
            point.lat = latitude;
            point.lng = longitude;
            point.pseudo = username;
            point.url = imageURL;
            point.save();

            result.put("status", "ok");
        }
        renderJSON(result);
    }
}