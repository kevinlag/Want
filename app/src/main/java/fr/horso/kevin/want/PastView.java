package fr.horso.kevin.want;

import java.sql.Timestamp;

public class PastView {

    public  String titre , prix , image_uri , user_id  ;
    public Timestamp temps;

    public PastView(){

    }

    public PastView(String titre, String prix, String image_uri, String user_id, Timestamp temps) {
        this.titre = titre;
        this.prix = prix;
        this.image_uri = image_uri;
        this.user_id = user_id;
        this.temps = temps;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTemps() {
        return temps;
    }

    public void setTemps(Timestamp temps) {
        this.temps = temps;
    }


}
