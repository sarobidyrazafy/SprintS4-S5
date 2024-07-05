package mg.itu.controller;
import java.util.ArrayList;
import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.Get;
import mg.itu.annotation.RequestBody;
import mg.itu.model.Employe;
import mg.itu.prom16.ModelAndView;

@AnnotationController()
public class EmpController {
    
    @Get(value = "/emp/listemp")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/liste.jsp");
        mv.addObject("list", generateListEmp());
        return mv;
    }

    @Get(value="/emp/formulaire")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/formulaire.jsp");
        mv.addObject("action", "traitement");
        return mv;
    }

    @Get(value="/emp/traitement")
    public ModelAndView treatment(@RequestBody Employe employe ){
        ModelAndView mv = new ModelAndView();
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

    private ArrayList<Employe> generateListEmp(){
        ArrayList<Employe> listemp = new ArrayList<>();
        listemp.add(new Employe("Aina", "Sarobidy", 19));
        listemp.add(new Employe("Rakoto", "Thomis", 19));
        return listemp;
    }
}
