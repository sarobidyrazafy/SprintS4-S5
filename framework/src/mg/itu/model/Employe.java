package mg.itu.model;

import mg.itu.annotation.FormParametre;

public class Employe {
    @FormParametre("nom")
    String nom;

    @FormParametre("prenom")
    String prenom;

    @FormParametre("age")
    int age;
    public Employe() {
    }
    public Employe(String nom, String prenom, int age) {
        setPrenom(prenom);
        setNom(nom);
        setAge(age);
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
