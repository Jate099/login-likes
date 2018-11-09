package icesi.movil.login.model;

import java.util.HashMap;

public class Comentario {

    public String id;
    public String texto;
    private HashMap<String, String> likes;


    public Comentario(){

    }

    public HashMap<String, String> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String, String> likes) {
        this.likes = likes;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public boolean equals(Object obj){
        if(obj instanceof  Comentario){
            Comentario c =(Comentario) obj;
           return this.getId().equals(c.getId());
        }
        return false;
    }
}
