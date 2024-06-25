package mg.prom16;

import java.util.HashMap;

public class ModelView {
    private String url;
    private HashMap<String, Object> data;

    // Constructeur qui initialise la map des données
    public ModelView() {
        data = new HashMap<>();
    }

    // Setter pour l'URL de la vue
    public void setUrl(String url) {
        this.url = url;
    }

    // Getter pour l'URL de la vue
    public String getUrl() {
        return url;
    }

    // Méthode pour ajouter un objet aux données
    public void addObject(String key, Object value) {
        data.put(key, value);
    }

    // Getter pour obtenir la map des données
    public HashMap<String, Object> getData() {
        return data;
    }
}
