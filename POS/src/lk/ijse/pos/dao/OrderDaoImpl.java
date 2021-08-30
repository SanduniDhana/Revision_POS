package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;



public class OrderDaoImpl implements OrderDAO{

    @Override
    public boolean addOrder(Orders orders) throws Exception {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO Orders VALUES (?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, orders.getId());
            pstm.setObject(2, orders.getDate());
            pstm.setObject(3, orders.getCustomerId());
            return (pstm.executeUpdate() > 0);
        }

    @Override
    public boolean updateOrder(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return false;
    }

    @Override
    public Orders searchOrder(String id) throws Exception {
        return null;
    }


      @Override
       public ArrayList<Orders> getAllOrders() {
            throw new UnsupportedOperationException("This feature is not supported yet");
        }

    }
