<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Thanh toán</title></head>
<body>
<h2>Thanh toán phiên cân</h2>
<p>Mã phiên: ${sessionID}</p>
<p>Tổng bao: ${totalBags}</p>
<p>Tổng khối lượng: ${totalWeight} Kg</p>
<p>Thành tiền: ${totalAmount} đ</p>

<form action="PaymentServlet" method="post">
    <input type="hidden" name="sessionID" value="${sessionID}" />
    Thành tiền: <input type="number" name="total" value="${totalAmount}" readonly><br/>
    Đã trả: <input type="number" name="paid" value="0"><br/>
    Còn lại: <input type="number" name="remain" value="${totalAmount}"><br/>
    Trừ số bao: <input type="number" name="deductBags" value="0"><br/>
    <input type="submit" value="Xác nhận thanh toán">
</form>
</body>
</html>B