package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDAO{
    @Override
    public boolean addItem(Item item) throws Exception {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)",
                item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice());

    }
    @Override
    public boolean deleteItem(String code) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);

    }

    @Override
    public boolean updateItem(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice());

    }

    @Override
    public Item searchItem(String code) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Item where code=?");
        stm.setObject(1, code);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4));
        }
        return null;
    }


    @Override
    public ArrayList<Item> getAllItems() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4));
            allItems.add(item);
        }
        return allItems;
    }

    public boolean updateItemQtyOnHand(String itemCode, int qty) throws Exception {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");
            pstm.setObject(1, qtyOnHand);
            pstm.setObject(2, code);
            return (pstm.executeUpdate() > 0);
    }
}
