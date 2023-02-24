package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    private static final String REGISTER_JSP = "/WEB-INF/register.jsp";
    private static final String USERNAME = "username";
    private static final String ITEMS = "items";
    private static final String ACTION = "action";
    private static final String LOGIN_ACTION = "login";
    private static final String ADD_ACTION = "add";
    private static final String DELETE_ACTION = "delete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(USERNAME);
        String action = request.getParameter(ACTION);

        if (action != null && action.equals("logout")) {
            session.invalidate();
            request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
            return;
        }

        if(username == null || username.equals("")) {
            request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
            return;
        }

        ArrayList<String> items = (ArrayList<String>) session.getAttribute(ITEMS);
        if(items == null) {
            items = new ArrayList<String>();
        }
        session.setAttribute(ITEMS, items);

        request.getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter(ACTION);

        switch(action) {
            case LOGIN_ACTION:
                session.setAttribute(USERNAME, request.getParameter(USERNAME));
                System.out.println(request.getParameter(USERNAME));
                break;
            case ADD_ACTION:
                ArrayList<String> items = (ArrayList<String>) session.getAttribute(ITEMS);
                items.add(request.getParameter("item"));
                session.setAttribute(ITEMS, items);
                break;
            case DELETE_ACTION:
                items = (ArrayList<String>) session.getAttribute(ITEMS);
                items.remove(Integer.parseInt(request.getParameter("item")));
                session.setAttribute(ITEMS, items);
                System.out.println(request.getParameter("item"));
                break;
        }

        response.sendRedirect("");
    }
}
