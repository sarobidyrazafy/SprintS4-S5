package mg.itu.controller;

import java.util.ArrayList;
import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.Get;
import mg.itu.annotation.Parametre;
import mg.itu.model.CustomSession;
import mg.itu.model.Employe;
import mg.itu.model.Note;
import mg.itu.prom16.ModelAndView;

@AnnotationController
public class EmpController {

    CustomSession session;

    public CustomSession getSession() {
        return session;
    }

    public void setSession(CustomSession session) {
        this.session = session;
    }

    @Get(value = "/emp/list")
    public ModelAndView list(@Parametre(name = "id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/liste.jsp");
        mv.addObject("list", generateListEmp());
        return mv;
    }

    @Get(value = "/emp/formulaire")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/formulaire.jsp");
        mv.addObject("action", "traitement");
        return mv;
    }

    @Get(value = "/emp/traitement")
    public ModelAndView treatment(@Parametre(name = "nom") String nom, @Parametre(name = "prenom") String prenom) {
        ModelAndView mv = new ModelAndView();
        Employe employe = new Employe(nom, prenom, 0); // Ajustez l'âge si nécessaire
        mv.setUrl("../views/emp/affichage.jsp");
        mv.addObject("employe", employe);
        return mv;
    }

    @Get(value = "/emp/testparam")
    public ModelAndView testparam() {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/affichage.jsp");
        return mv;
    }

    @Get(value = "/emp/login")
    public ModelAndView login(@Parametre(name = "id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/login.jsp");
        mv.addObject("action", "traitlogin");
        return mv;
    }

    @Get(value = "/emp/traitlogin")
    public ModelAndView traitlogin(@Parametre(name = "email") String email, @Parametre(name = "mdp") String mdp) {
        ModelAndView mv = new ModelAndView();
        ArrayList<Note> list_notes = Note.getNotesForUser(email, mdp);
        if (!list_notes.isEmpty()) {
            session.add("userEmail", email);
            session.add("userMdp", mdp);
        }
        mv.setUrl("../views/emp/listNotesLogin.jsp");
        mv.addObject("list_notes", list_notes);
        return mv;
    }

    @Get(value = "/emp/listeNotes")
    public ModelAndView listAllNote(@Parametre(name = "id") int id) {
        ModelAndView mv = new ModelAndView();
        ArrayList<Note> list_notes = Note.generateNotes();
        mv.setUrl("../views/emp/listNotes.jsp");
        mv.addObject("list_notes", list_notes);
        return mv;
    }

    @Get(value = "/emp/logout")
    public ModelAndView logout(@Parametre(name = "id") int id) {
        session.delete("userEmail");
        session.delete("userMdp");
        return listAllNote(0);
    }


    private ArrayList<Employe> generateListEmp() {
        ArrayList<Employe> listemp = new ArrayList<>();
        listemp.add(new Employe("Aina", "Sarobidy", 19));
        listemp.add(new Employe("Razafy", "Mahandry", 20));
        listemp.add(new Employe("Rabe", "Ryan", 20));
        listemp.add(new Employe("Andry", "Fihobiana", 20));
        return listemp;
    }
}
