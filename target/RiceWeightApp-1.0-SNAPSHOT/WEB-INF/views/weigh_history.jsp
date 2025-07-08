<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.*" %>
<html>
<head><title>Lịch sử phiên cân</title></head>
<body>
<h2>📦 Lịch sử phiên cân</h2>

<%
    List<WeighingSession> list = (List<WeighingSession>) request.getAttribute("sessionList");
    if (list == null) out.print("Không có dữ liệu!");
    double totalKg = 0;
    int totalBags = 0;
    for (WeighingSession s : list) {
        double sumKg = 0;
        int bags = s.getDetails() != null ? s.getDetails().size() : 0;
        if (s.getDetails() != null)
            for (WeighingDetail d : s.getDetails()) sumKg += d.getWeightKg();
        totalKg += sumKg;
        totalBags += bags;
%>
    <div style="border:1px solid #ccc;margin:10px;padding:10px;">
        <b><%= s.getCustomerName() %></b> — <%= s.getWeighingDate() %><br/>
        KL: <%= String.format("%.3f", sumKg) %> Kg — Bao: <%= bags %> cái<br/>
        Ghi chú: <%= s.getNote() == null ? "" : s.getNote() %><br/>
        <a href="SessionDetailServlet?sessionID=<%= s.getSessionID() %>">Xem chi tiết</a>
    </div>
<% } %>

<hr/>
<b>Tổng KL:</b> <%= String.format("%.3f", totalKg) %> Kg<br/>
<b>Tổng Bao:</b> <%= totalBags %> cái
</body>
</html>
