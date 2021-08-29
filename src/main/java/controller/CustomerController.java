package controller;

import model.Customer;
import service.CustomerServiceImpl;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="CustomerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    private CustomerServiceImpl service = new CustomerServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher req1 =req.getRequestDispatcher("customers/list.jsp");

        List<Customer> customers = service.findAll();
        req.setAttribute("customers",customers);


        req1.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
