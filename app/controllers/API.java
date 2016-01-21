package controllers;

import org.apache.commons.codec.binary.Base64OutputStream;
import play.mvc.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Leo on 15/01/16.
 */
public class API  extends Controller {


    public static void interest() {
        Calendar calendar = Calendar.getInstance();
        List<Map> messages = new ArrayList<Map>();
        Map map = new HashMap<String, Object>();
        map.put("photo_url", "http://lorempixel.com/1000/1000/");
        List tags = new ArrayList();
        tags.add("troll");
        tags.add("dechet");
        tags.add("casse");
        map.put("tags", tags);

        Map zone = new HashMap();
        zone.put("latitude1", 43.561712);
        zone.put("longitude1", 1.469089);
        zone.put("latitude2", 43.559997);
        zone.put("longitude2", 1.468638);
        map.put("zone", zone);

        map.put("latitude", 43.562659);
        map.put("created_at", calendar.getTimeInMillis());
        map.put("longitude", 1.469137);
        map.put("username", "jamon");
        messages.add(map);
        renderJSON(messages);
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
            try {

                BufferedImage image = ImageIO.read(photo);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                OutputStream b64 = new Base64OutputStream(os);
                ImageIO.write(image, "png", b64);
                String r = os.toString("UTF-8");
                result.put("status", "ok");
            } catch(Exception e) {

                result.put("status", "error");
            }
        }
        renderJSON(result);
    }
}