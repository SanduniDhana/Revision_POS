package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class OrderDetailsDaoImpl implements OrderDetailsDAO{

    @Override
    public boolean addOrderDetails(OrderDetails oDetails) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetails VALUES (?,?,?,?)",
                oDetails.getOrderId(),oDetails.getItemCode(),oDetails.getQty(),oDetails.getUnitPrice());
        
    }
}
