package edu.acc.java3.address;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Sessaly implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        AddressDao dao = new AddressDao();
        se.getSession().setAttribute("dao", dao);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
