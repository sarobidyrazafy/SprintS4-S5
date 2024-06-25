package mg.itu.controller;
import java.util.ArrayList;
import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.FormParametre;
import mg.itu.annotation.Get;
import mg.itu.annotation.RequestBody;
import mg.itu.prom16.ModelAndView;

@AnnotationController()
public class EmpController {
    @FormParametre("nom")
    String nom;

    @FormParametre("prenom")
    String prenom;

    @FormParametre("age")
    String age;
    public EmpController() {
    }
    public EmpController(String nom, String prenom, String age) {
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
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
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
        mv.addObject("action", "treatment");
        return mv;
    }

    // @Get(value="/emp/treatment")
    // public ModelAndView treatment(@Parametre(name = "name")String name, @Parametre(name = "prenom")String prenom){
    //     ModelAndView mv = new ModelAndView();
    //     mv.setUrl("../views/emp/result.jsp");
    //     mv.addObject("name", name);
    //     mv.addObject("prenom", prenom);
    //     return mv;
    // }

    @Get(value="/emp/traitement")
    public ModelAndView treatment(@RequestBody EmpController employe ){
        ModelAndView mv = new ModelAndView();
        mv.setUrl("../views/emp/affichage.jsp");
        mv.addObject("employe", employe);
        return mv;
    }

    private ArrayList<EmpController> generateListEmp(){
        ArrayList<EmpController> listemp = new ArrayList<>();
        listemp.add(new EmpController("Aina", "Sarobidy", "19"));
        listemp.add(new EmpController("Rakoto", "Thomis", "19"));
        return listemp;
    }
}
