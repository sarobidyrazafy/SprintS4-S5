package mg.itu.controller;

import java.util.List;
import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.RestAPI;
import mg.itu.annotation.Url;
import mg.itu.annotation.parametre.RequestBody;
import mg.itu.annotation.verbaction.Get;
import mg.itu.annotation.verbaction.Post;
import mg.itu.model.Etudiant;
import mg.itu.prom16.ModelAndView;

@AnnotationController()
public class EtudiantController {
    Etudiant etudiant = new Etudiant();

    @Url(value = "/etudiant/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/list.jsp");
        mv.addObject("list", etudiant.listeEtudiants());
        return mv;
    }

    @Url(value = "/etudiant/table")
    @RestAPI
    public List<Etudiant> liste() {
        return etudiant.listeEtudiants();
    }

    @Url(value = "/etudiant/tables")
    @RestAPI
    public ModelAndView table(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/list.jsp");
        mv.addObject("list", etudiant.listeEtudiants());
        return mv;
    }

    @Get
    @Url(value="/etudiant/form")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/form.jsp");
        mv.addObject("action", "treatment");
        return mv;
    }

    @Post
    @Url(value="/etudiant/formout")
    public ModelAndView formol(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/form.jsp");
        mv.addObject("action", "treatment");
        return mv;
    }

    // @Authenticated(roles = {1}) 
    @Post
    @Url(value="/etudiant/treatment")
    public ModelAndView treatment(@RequestBody Etudiant etu) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/result.jsp");

        mv.addObject("nom", etu.nom);
        mv.addObject("prnenom", etu.prenom);
        mv.addObject("user", etu.user);
        mv.addObject("age", etu.age);
        return mv;
    }    

    @Url(value="/etudiant/treat")
    public ModelAndView treat(String name,String job){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/etudiant/result.jsp");
        mv.addObject("name", name);
        mv.addObject("job", job);
        return mv;
    }
}
