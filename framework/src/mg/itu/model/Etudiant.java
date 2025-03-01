package mg.itu.model;

import java.util.ArrayList;
import mg.itu.annotation.MySession;
import mg.itu.annotation.parametre.FormParametre;
import mg.itu.annotation.parametre.Required;
import mg.itu.annotation.type.Entier;
import mg.itu.annotation.type.Numeric;

public class Etudiant {
    @FormParametre("nom")
    @Required
    public String nom;

    @FormParametre("prenom")
    @Required
    public String prenom;

    @Required
    @FormParametre("user")
    @Entier(min = 1, max = 3)    
    public int user;

    @Required
    @FormParametre("age")
    @Numeric
    public double age;

    private MySession session;

    public String getNom() {
        return nom;
    }

    public String UrlNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String UrlPrenom() {
        return prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public MySession UrlSession() {
        return session;
    }

    public void setSession(MySession session) {
        this.session = session;
    }

    public MySession getSession() {
        return session;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Etudiant() {
    }

    public Etudiant(int user,String nom, String prenom) {
        setUser(user);
        setPrenom(prenom);
        setNom(nom);
    }

    public ArrayList<Etudiant> listeEtudiants(){
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1,"Ravao", "Maria"));
        etudiants.add(new Etudiant(1,"Rakoto", "Edgard"));
        etudiants.add(new Etudiant(2,"Andrianaivo", "Toky"));
        etudiants.add(new Etudiant(2,"Razafy", "Tsinjo"));
        etudiants.add(new Etudiant(3,"Randria", "Lalaina"));
        etudiants.add(new Etudiant(3,"Aina", "Tsifoy"));
        return etudiants;
    }
    
    public ArrayList<Etudiant> UrlListById(int id){
        ArrayList<Etudiant> liste = listeEtudiants();
        ArrayList<Etudiant> filteredListFor = new ArrayList<>();
        for (Etudiant etudiant : liste) {
            if (etudiant.getUser() == id) {
                filteredListFor.add(etudiant);
            }
        }
        return filteredListFor;
    }
}
