public class ManageCustomerFormController implements Initializable {

    boolean addnew = true;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerAddress;
    @FXML
    private TableView<CustomerTM> tblCustomers;

    private void loadAllCustomers() {

        try {

            CustomerDAOImpl customerDAO= new CustomerDAOImpl();
            ArrayList<Customer> allCustomers=customerDAO.getAllCustomers();
            ArrayList<CustomerTM>allCustomerForTable=new ArrayList<>();

            for (Customer customer : allCustomers) {
                allCustomerForTable.add(new CustomerTM(customer.getcID(), customer.getName(), customer.getAddress()));
            }

            ObservableList<CustomerTM> olCustomers = FXCollections.observableArrayList(allCustomerForTable);

            tblCustomers.setItems(olCustomers);

        } catch (Exception ex) {

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCustomers.getColumns().get(0).setStyle("-fx-alignment:center");
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {

                if (newValue == null) {
                    clearTextFields();
                    addnew = true;
                    return;
                }

                txtCustomerId.setText(newValue.getId());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());

                addnew = false;

            }
        });

        loadAllCustomers();
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {

        Alert confirmAlert = new Alert(Alert.AlertType.WARNING, "Are you sure whether you want to delete the customer?", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.get() == ButtonType.YES) {

            String customerID = tblCustomers.getSelectionModel().getSelectedItem().getId();

            try {
                /Delete operation/
                CustomerDAOImpl customerDAO = new CustomerDAOImpl();
                boolean b = customerDAO.deleteCustomer(customerID);

                if (b) {
                    loadAllCustomers();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Failed to delete the customer", ButtonType.OK);
                    a.show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void clearTextFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
    }

    @FXML
    private void btnAddNewCustomer_OnAction(ActionEvent event) {
        txtCustomerId.requestFocus();
        tblCustomers.getSelectionModel().clearSelection();

        addnew = true;
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {

        if (addnew) {
            try {
                /* Add Operation*/
                CustomerDAOImpl dao = new CustomerDAOImpl();
                boolean b = dao.addCustomer(new Customer(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText()));
                if (b) {
                    loadAllCustomers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to add new customer", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                //Update Operation
                CustomerDAOImpl dao = new CustomerDAOImpl();
                boolean b = dao.updateCustomer(new Customer(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText()));
                if (b) {
                    loadAllCustomers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to update the customer", ButtonType.OK).show();
                }


            } catch (Exception ex) {
                Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}