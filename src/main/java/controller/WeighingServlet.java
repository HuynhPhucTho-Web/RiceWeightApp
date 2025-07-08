// src/controller/WeighingServlet.java
package controller;

import dao.WeighingSessionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "WeighingServlet", urlPatterns = {"/WeighingServlet"})
public class WeighingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] weights = req.getParameterValues("weightKg");
            double pricePerKg = Double.parseDouble(req.getParameter("pricePerKg"));
            int customerID = Integer.parseInt(req.getParameter("customerID"));
            String note = req.getParameter("note");

            List<WeighingDetail> detailList = new ArrayList<>();
            double totalWeight = 0;
            int index = 1;
            for (String w : weights) {
                if (w != null && !w.trim().isEmpty()) {
                    double kg = Double.parseDouble(w);
                    WeighingDetail d = new WeighingDetail();
                    d.setBagIndex(index++);
                    d.setWeightKg(kg);
                    detailList.add(d);
                    totalWeight += kg;
                }
            }

            WeighingSession session = new WeighingSession();
            session.setCustomerID(customerID);
            session.setPricePerKg(pricePerKg);
            session.setNote(note);
            session.setDetails(detailList);

            int sessionId = new WeighingSessionDAO().insertSession(session);

            req.setAttribute("sessionID", sessionId);
            req.setAttribute("totalWeight", totalWeight);
            req.setAttribute("totalAmount", totalWeight * pricePerKg);
            req.setAttribute("totalBags", detailList.size());
            req.getRequestDispatcher("/WEB-INF/views/weigh_payment.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi xử lý dữ liệu: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/weigh_form.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/weigh_form.jsp").forward(req, resp);
    }
}
