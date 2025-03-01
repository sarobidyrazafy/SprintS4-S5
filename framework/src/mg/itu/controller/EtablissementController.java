package mg.itu.controller;

import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.parametre.FormParametre;

@AnnotationController()
public class EtablissementController {

    @FormParametre("id")
    String id;

    String nom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public EtablissementController(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
