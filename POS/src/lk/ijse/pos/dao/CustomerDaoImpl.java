package lk.ijse.pos.dao;

import lk.ijse.pos.model.Customer;

public class CustomerDaoImpl {
    public boolean addCustomer(Customer customer){
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");

        pstm.setObject(1, txtCustomerId.getText());
        pstm.setObject(2, txtCustomerName.getText());
        pstm.setObject(3, txtCustomerAddress.getText());
        pstm.setObject(4, 0);
    }

    public boolean updateCustomer(Customer customer){
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setObject(1, txtCustomerName.getText());
        pstm.setObject(2, txtCustomerAddress.getText());
        pstm.setObject(3, txtCustomerId.getText());
    }

    public boolean deleteCustomer(String id){
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setObject(1, customerID);
    }

    public Customer searchCustomer(String id){
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer where id=?");
    }

    public ArrayList<Customer> getAllCustomers(){
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
    }
}
