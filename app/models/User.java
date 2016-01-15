package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 15/01/16.
 */

@Entity
@Table(name = "utilisateur")
public class User extends Model {
    @Column
    public String ip;

    @Column
    public String email;

    @Column
    public String hashedPassword;

    @Column
    public String nom;

    @Column
    public String prenom;
}
