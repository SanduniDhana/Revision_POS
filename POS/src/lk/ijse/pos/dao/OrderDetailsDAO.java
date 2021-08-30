package lk.ijse.pos.dao;


import lk.ijse.pos.model.OrderDetails;

import java.util.ArrayList;

public interface OrderDetailsDAO {
    public boolean addOrderDetails(OrderDetails orderDetails) throws Exception;

}
