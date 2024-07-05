package mg.itu.controller; 
import java.time.LocalDate;
import java.util.Date;
import mg.itu.annotation.*; 

@AnnotationController()
public class BackController {

    public BackController(){}

    
    // @Get(value = "/")
    // public String hello(){
    //     return "Sprint";
    // }
    @Get(value = "/date")
    public Date day(){
        return new Date(LocalDate.now().toEpochDay());
    }

    
}