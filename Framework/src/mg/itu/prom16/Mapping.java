package mg.itu.prom16;

import java.lang.reflect.Method;
import java.util.ArrayList;

import mg.itu.model.VerbAction;

public class Mapping {
    private ArrayList<VerbAction> verb;

    public Mapping() {
        verb = new ArrayList<>();
    }

    public ArrayList<VerbAction> getVerb() {
        return verb;
    }

    public void setVerb(ArrayList<VerbAction> verb) {
        this.verb = verb;
    }

    public void addVerbAction(VerbAction verbaction) throws Exception {
        for (VerbAction action : verb) {
            if (action.getHttpMethod().equalsIgnoreCase(verbaction.getHttpMethod())) {
                throw new Exception("Cet url à déja cette méthode");
            }
        }
        verb.add(verbaction);
    }

    public VerbAction getActionByVerb(String httpVerb) {
        for (VerbAction verbAction : verb) {
            if (httpVerb.equals(verbAction.getHttpMethod())) {
                return verbAction;
            }
        }
        return null;
    }
}
