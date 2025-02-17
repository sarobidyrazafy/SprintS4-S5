package mg.itu.model;

import javax.servlet.http.HttpSession;

public class CustomSession {
    private HttpSession session;

    public void add(String key, Object value) {
        if (session.getAttribute(key) == null) {
            session.setAttribute(key, value);
        }
    }

    public Object get(String key) {
        return session.getAttribute(key);
    }

    public void delete(String key) {
        session.removeAttribute(key);
    }

    public void update(String key, Object value) {
        if (session.getAttribute(key) != null) {
            session.setAttribute(key, value);
        }
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
