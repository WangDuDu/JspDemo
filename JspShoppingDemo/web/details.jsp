<%@ page import="dao.ItemsDAO" %>
<%@ page import="entity.Items" %>
<%@ page import="java.util.AbstractCollection" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wangshuyang
  Date: 2017/3/16
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>goods detailes</title>
    <style type="text/css">
        div{
            float:left;
            margin-left: 30px;
            margin-right:30px;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        div dd{
            margin:0px;
            font-size:10pt;
        }
        div dd.dd_name
        {
            color:blue;
        }
        div dd.dd_city
        {
            color:#000;
        }
    </style>
</head>
<body>
    <h1>商品详情</h1>
    <hr>

    <center>
    <table width="750" height="60" cellpadding="0" border="0">
        <tr>
                <!-- 商品循环开始 -->
            <%
                ItemsDAO itemsDao = new ItemsDAO();
                Items item = itemsDao.getItemsByID(Integer.parseInt(request.getParameter("id")));
                if (item != null) {
            %>
            <td width="70%" valign="top">
                <table>
                    <tr>
                        <td rowspan="4"><img src="images/<%=item.getPicture()%>" width="200" height="160"></td>
                    </tr>
                    <tr>
                        <td><B><%=item.getName()%></B></td>
                    </tr>
                    <tr>
                        <td>产地:<%=item.getCity()%></td>
                    </tr>
                    <tr>
                        <td>价格:<%=item.getPrice()%>元</td>
                    </tr>
                </table>
            </td>
            <%
                }
            %>
            <!-- 商品循环结束 -->

            <%
                String list = "";
                // 从客户端获取cookie集合
                Cookie[] cookies = request.getCookies();
                if (cookies != null && cookies.length > 0) {
                    for (Cookie c:cookies) {
                        if (c.getName().equals("ListViewCookie")) {
                            list = c.getValue();
                        }
                    }
                }

                list += request.getParameter("id") + "@";
                // 如果浏览记录超过一千条,清零
                String[] arr = list.split("@");
                if (arr != null && arr.length > 0) {
                    if (arr.length >= 1000) {
                        list = "";
                    }
                }
                Cookie cookie = new Cookie("ListViewCookie", list);
                response.addCookie(cookie);

            %>
            <!-- 浏览过的商品 -->
            <td width="30%" bgcolor="#EEE" align="center">
                <br>
                <b>您浏览过的商品</b>
                <!-- 循环开始 -->
                <%
                    ArrayList<Items> itemlist = itemsDao.getViewList(list);
                    if (itemlist != null && itemlist.size() > 0) {
                        for (Items i : itemlist) {

                %>
                <div>
                    <dl>
                        <dt>
                            <a href="details.jsp?id=<%=i.getId()%>"><img src="images/<%=i.getPicture()%>" width="120" height="90" border="1"></a>
                        </dt>
                        <dd class="dd_name"><%=i.getName()%></dd>
                        <dd class="dd_city">产地:<%=i.getCity()%>&nbsp;&nbsp;价格:<%=i.getPrice()%>元</dd>
                    </dl>
                </div>
                <%
                        }
                    }
                %>
                <!-- 循环结束 -->
        </tr>
    </table>
    </center>
</body>
</html>
