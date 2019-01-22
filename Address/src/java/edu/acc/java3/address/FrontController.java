package edu.acc.java3.address;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0)
            action = "add";
        String destination = action;
        switch (action) {
            case "add": destination = add(request); break;
            case "list": destination = list(request); break;
            case "alter": destination = alter(request); break;
            case "logout": destination = logout(request); break;
        }
        if (destination.startsWith("redirect")) {
            response.sendRedirect("main");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/" + destination + ".jsp")
                .forward(request, response);
    }

    private String add(HttpServletRequest request) {
        AddressDao dao = this.getDaoFrom(request);
        List<Address> addrs = dao.findAll();
        request.setAttribute("count", addrs.size());
        if (request.getMethod().equalsIgnoreCase("GET"))
            return "add";
        Address addr = marshalAddressBean("", request.getParameterMap());
        try {
            this.getDaoFrom(request).add(addr);
        } catch (IllegalArgumentException iae) {
            request.setAttribute("flash", iae.getMessage());
            return "add";
        }
        return list(request);
    }

    private String list(HttpServletRequest request) {
        request.setAttribute("addrs", this.getDaoFrom(request).findAll());
        return "list";
    }
    
    private String alter(HttpServletRequest request) {
        if (request.getParameter("logout") != null) return logout(request);
        if (request.getParameter("add") != null) return "redirect:add";
        String[] indices = request.getParameterValues("alterme");
        if (indices == null || indices.length == 0) return list(request);
        if (request.getParameter("delete") != null) {
            removeAddresses(indices, this.getDaoFrom(request));
        }
        else {
            try {
                updateAddresses(indices, request.getParameterMap(), this.getDaoFrom(request));
            } catch (IllegalArgumentException iae) {
                request.setAttribute("flash", iae.getMessage());
            }
        }
        return list(request);
    }
    
    private String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return add(request);
    }

    private Address marshalAddressBean(String fieldFlag, Map<String,String[]> params) {
        return new Address(
            params.get("firstName" + fieldFlag)[0],
            params.get("lastName" + fieldFlag)[0],
            params.get("street" + fieldFlag)[0],
            params.get("city" + fieldFlag)[0],
            params.get("state" + fieldFlag)[0],
            params.get("zip" + fieldFlag)[0]
        );
    }
    
    private void removeAddresses(String[] indices, AddressDao dao) {
        dao.remove(Arrays.stream(indices)
                .mapToInt(Integer::parseInt)
                .toArray());
    }

    private void updateAddresses(String[] indices, Map<String, String[]> params, AddressDao dao) {
        for (String index : indices) {
            int idx = Integer.parseInt(index);
            Address replacement = this.marshalAddressBean(index, params);
            dao.update(idx, replacement);
        }
    }
    
    private AddressDao getDaoFrom(HttpServletRequest request) {
        return (AddressDao)request.getSession().getAttribute("dao");
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
