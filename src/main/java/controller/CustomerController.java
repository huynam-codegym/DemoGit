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
        String action = req.getParameter("page");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                ShowFromCreate(req,resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "detail":
                ShowCustomerById(req,resp);
                break;
            default:
                ShowAllcustomer(req, resp);
                break;
        }

    }

    private void ShowFromCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher  dispatcher = req.getRequestDispatcher("customer/create.jsp");
    dispatcher.forward(req,resp);
    }

    private void ShowCustomerById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int id1 =Integer.parseInt(id);
        Customer customer = service.findById(id1);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/detail.jsp");
        req.setAttribute("c",customer);
        dispatcher.forward(req,resp);
    }

    private void ShowAllcustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher req1 = req.getRequestDispatcher("customer/list.jsp");
        List<Customer> customers = service.findAll();
        req.setAttribute("customers",customers);
        req1.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("page");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                CreateNewCustomer(req,resp);
                break;
//            case "edit":
//                break;
//            case "delete":
//                break;
//            case "detail":
//                ShowCustomerById(req,resp);
//                break;
//            default:
//                ShowAllcustomer(req, resp);
//                break;
        }

    }

    private void CreateNewCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("add");
        int id = (int)(Math.random() * 10000);

        Customer customer = new Customer(id, name, email, address);
        service.save(customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        req.setAttribute("message", "New customer was created");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
