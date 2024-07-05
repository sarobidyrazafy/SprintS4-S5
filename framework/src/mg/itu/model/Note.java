package mg.itu.model;

import java.util.ArrayList;

public class Note {
    public Note(Utilisateur utilisateur, String matiere, int note) {
        this.utilisateur = utilisateur;
        this.matiere = matiere;
        this.note = note;
    }

    public Note(){

    }

    Utilisateur utilisateur;
    String matiere;
    int note;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public static ArrayList<Note> generateNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        Utilisateur user1 = new Utilisateur(1, "sarobidy@gmail.com", "mdp1","Rakoto");
        Utilisateur user2 = new Utilisateur(2, "mahandry@gmail.com", "mdp2","Razafy");
        Utilisateur user3 = new Utilisateur(3, "fihobiana@gmail.com", "mdp3","Ravao");

        notes.add(new Note(user1, "Math", 12));
        notes.add(new Note(user1, "Physics", 15));
        notes.add(new Note(user2, "Math", 18));
        notes.add(new Note(user2, "Physics", 9));
        notes.add(new Note(user3, "Math", 20));
        notes.add(new Note(user3, "Physics", 16));

        return notes;
    }

    public static ArrayList<Note> getNotesForUser(String email, String mdp) {
        ArrayList<Note> notes = generateNotes();
        ArrayList<Note> userNotes = new ArrayList<>();

        Utilisateur user = new Utilisateur();

        if (user.verifyLogin(email, mdp)) {
            for (Note note : notes) {
                if (note.getUtilisateur().getEmail().equals(email) && note.getUtilisateur().getMdp().equals(mdp)) {
                    userNotes.add(note);
                }
            }
        }

        return userNotes;
    }
}
