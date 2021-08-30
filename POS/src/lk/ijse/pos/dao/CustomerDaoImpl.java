package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerDaoImpl implements CustomerDAO{
    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        return CrudUtil.execute("Insert into Customer values(?,?,?)",
                customer.getcID(),customer.getName(),customer.getAddress());

    }

    @Override
    public boolean updateCustomer(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                customer.getcID(),customer.getName(),customer.getAddress());
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public Customer searchCustomer(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer where id=?");
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer where id=?");
        pstm.setObject(1, id);
        rst = pstm.executeQuery();
        if (rst.next()) {
            return new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        }
        return null;
    }



    @Override
    public ArrayList<Customer> getAllCustomer() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> alCustomers = new ArrayList<>();
        while (rst.next()) {
            Customer customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3));
            alCustomers.add(customer);
        }
        return alCustomers;
    }


}