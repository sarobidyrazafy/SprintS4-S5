package mg.itu.model;

import java.util.ArrayList;

public class Utilisateur {
    int id;
    String nom;
    String email;
    String mdp;

    public Utilisateur(int id, String email, String mdp, String nom){
        this.setEmail(email);
        this.setId(id);
        this.setMdp(mdp);
        this.setNom(nom);
    }
    public Utilisateur(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public ArrayList<Utilisateur> generateLogin(){
        ArrayList<Utilisateur> login = new ArrayList<>();
        login.add(new Utilisateur(1,"sarobidy@gmail.com","mdp1","Ravao"));
        login.add(new Utilisateur(2,"mahandry@gmail.com","mdp2","Razafy"));
        login.add(new Utilisateur(3,"fihobiana@gmail.com","mdp3","Rakoto"));
        return login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean verifyLogin(String email, String mdp){
        ArrayList<Utilisateur> login = this.generateLogin();
        for(Utilisateur user : login){
            if(user.getEmail().equals(email) && user.getMdp().equals(mdp)){
                return true;
            }
        }
        return false;
    }

}
