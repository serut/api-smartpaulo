package models;

import play.db.jpa.Model;

/**
 * Created by julien on 21/01/16.
 */
public class PointOfInterest extends Model{

    public Double lat;
    public Double lng;

    public String tag;

    public String pseudo;

    public String url;

    public Double zone_latitude1;
    public Double zone_longitude1;
    public Double zone_latitude2;
    public Double zone_longitude2;

}
