package dao;

import entity.Items;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wangshuyang on 2017/3/16.
 */
public class ItemsDAO {

    public ArrayList<Items> getAllItems(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from items;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                list.add(item);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Items getItemsByID(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from items WHERE id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                return item;
            } else {
              return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Items> getViewList(String list) {
        System.out.println("list:"+list);
        ArrayList<Items> itemlist = new ArrayList<Items>();
        int iCount = 5;
        if (list != null && list.length() > 0) {
            String[] arr = list.split("@");
            System.out.println("arr.length="+arr.length);
            if (arr.length >= 5) {
                for (int i = arr.length-1; i >= arr.length-iCount; i--) {
                    itemlist.add(getItemsByID(Integer.parseInt(arr[i])));
                }
            } else {
                for (int i = arr.length-1; i >=0; i--) {
                    itemlist.add(getItemsByID(Integer.parseInt(arr[i])));
                }
            }
            return itemlist;
        } else {
            return null;
        }
    }
}
