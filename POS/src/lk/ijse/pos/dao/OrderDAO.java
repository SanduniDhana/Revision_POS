package lk.ijse.pos.dao;

import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Orders;

import java.util.ArrayList;

public interface OrderDAO {
    public boolean addOrder(Orders orders) throws Exception;

    public boolean updateOrder(Orders orders) throws Exception;


    public boolean deleteOrder(String id) throws Exception;


    public Orders searchOrder(String id) throws Exception;


    public ArrayList<Orders> getAllOrders() throws Exception;
}
