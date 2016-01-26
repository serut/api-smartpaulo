package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by julien on 21/01/16.
 */

@Entity
@Table(name = "pointofinterest")
public class PointOfInterest extends Model{

    @Column
    public Double lat;

    @Column
    public Double lng;

    @Column
    public String tag;

    @Column
    public String pseudo;

    @Column
    public String url;

    @Column
    public Double x1;

    @Column
    public Double x2;

    @Column
    public Double y1;

    @Column
    public Double y2;

}
