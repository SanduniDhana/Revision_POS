package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerDaoImpl implements CustomerDAO{
    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        return CrudUtil.execute("Insert into Customer values(?,?,?)",
                customer.getName(),customer.getAddress(),customer.getcID());

    }

    @Override
    public boolean updateCustomer(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                customer.getName(),customer.getAddress(),customer.getcID());
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public Customer searchCustomer(String id) throws Exception {


        return null;
    }



    @Override
    public ArrayList<Customer> getAllCustomer() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(
                    new Customer(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3))
            );
        }
        return customerList;
    }


}