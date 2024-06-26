package com.sistema.Gestion.controller;

import com.sistema.Gestion.model.Customer;
import com.sistema.Gestion.model.Product;
import com.sistema.Gestion.model.Supplier;
import com.sistema.Gestion.model.User;
import com.sistema.Gestion.service.CustomerService;
import com.sistema.Gestion.service.ProductService;
import com.sistema.Gestion.service.SupplierService;
import com.sistema.Gestion.service.UserService;
import com.sistema.Gestion.view.LoginPage;
import com.sistema.Gestion.view.ManagementPage;
import com.sistema.Gestion.view.NewUserPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lucas
 */
@Component
public class Controller implements ActionListener{
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private ProductService productService;
    

    private ManagementPage managementPage;
    private LoginPage loginPage;
    private NewUserPage newUserPage;
    
    private Integer idCustomer;
    private Integer idSupplier;
    private Integer idProduct;
    
    
    @Autowired
    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
        
        this.loginPage.getBtnExit().addActionListener(this);
        this.loginPage.getBtnLogin().addActionListener(this);
        this.loginPage.getBtnNewUser().addActionListener(this);   
    }
    
    @Autowired
    public void setNewUserPage(NewUserPage newUserPage) {
        this.newUserPage = newUserPage;
        
        this.newUserPage.getBtnExit().addActionListener(this);
        this.newUserPage.getBtnAddUser().addActionListener(this);
    }
    
    @Autowired
    public void setManagementPage(ManagementPage managementPage) {
        this.managementPage = managementPage;
        
        // Acciones Customer
        this.managementPage.getBtnAddCustomer().addActionListener(this);
        this.managementPage.getBtnModifyCustomer().addActionListener(this);
        this.managementPage.getBtnDeleteCustomer().addActionListener(this);
        
        this.managementPage.getTableCustomer().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    
                    
                    int id = (int) target.getValueAt(row, 0);
                    String name = (String) target.getValueAt(row, 1);
                    String phone = (String) target.getValueAt(row, 2);
                    String address = (String) target.getValueAt(row, 3);
                    String email = (String) target.getValueAt(row, 4);
                    
                    
                    idCustomer = id;
                    managementPage.setEdtNameCustomer(name);
                    managementPage.setEdtPhoneCustomer(phone);
                    managementPage.setEdtAddressCustomer(address);
                    managementPage.setEdtEmailCustomer(email);
                }
            }
            
        });
        
        // Acciones Supplier
        this.managementPage.getBtnAddSupplier().addActionListener(this);
        this.managementPage.getBtnDeleteSupplier().addActionListener(this);
        this.managementPage.getBtnModifySupplier().addActionListener(this);
        
        this.managementPage.getTableSupplier().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    
                    
                    int id = (int) target.getValueAt(row, 0);
                    String name = (String) target.getValueAt(row, 1);
                    String phone = (String) target.getValueAt(row, 2);
                    String address = (String) target.getValueAt(row, 3);
                    String cif = (String) target.getValueAt(row, 4);
                    String email = (String) target.getValueAt(row, 5);
                    
                    
                    idSupplier = id;
                    managementPage.setEdtNameSupplier(name);
                    managementPage.setEdtPhoneSupplier(phone);
                    managementPage.setEdtAddressSupplier(address);
                    managementPage.setEdtCifSupplier(cif);
                    managementPage.setEdtEmailSupplier(email);
                }
            }
            
        });
        
        
        this.managementPage.getJTabbedPanel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                String title = sourceTabbedPane.getTitleAt(index);
                managementPage.getLavelTitulo().setText(title);
                fillCustomerTable();
                fillSupplierTable();
                fillPruductTable();
            }
            
        });
        
        
        // Acciones Product
        this.managementPage.getBtnAddProduct().addActionListener(this);
        this.managementPage.getBtnDeleteProduct().addActionListener(this);
        this.managementPage.getBtnModifyProduct().addActionListener(this);
        
        this.managementPage.getTableProduct().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    
                    
                    int id = (int) target.getValueAt(row, 0);
                    String description = (String) target.getValueAt(row, 1);
                    Double price = (Double) target.getValueAt(row, 2);
                    String familyProduct = (String) target.getValueAt(row, 3);
                    Integer stock = (Integer) target.getValueAt(row, 4);
                    
                    
                    idProduct = id;
                    managementPage.setEdtNameProduct(description);
                    managementPage.setEdtPriceProduct(price.toString());
                    managementPage.setEdtStckProduct(stock.toString());
                    managementPage.setCmbFamilyProduct(familyProduct);
                    
                }
            }
            
        });
        
        
        
    }
    
    
    public void viewLogin() {
        loginPage.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Eventos LoginPage
        if (e.getSource() == loginPage.getBtnExit()) {
            loginPage.dispose();
        }
        
        if (e.getSource() == loginPage.getBtnLogin()) {
            String name = loginPage.getName();
            String pass = loginPage.getPass();
            AccessControl(name, pass);
            
            
        }
        
        if (e.getSource() == loginPage.getBtnNewUser()) {
            newUserPage.setLocationRelativeTo(null);
            newUserPage.setModal(true);
            newUserPage.setVisible(true);
           
        }
        
        // Eventos NewUserPage
        if (e.getSource() == newUserPage.getBtnExit()) {
            newUserPage.dispose();
            newUserPage.cleanAll();
        }
        
        if (e.getSource() == newUserPage.getBtnAddUser()) {
            String pass = newUserPage.getAdminPass();
            addUser(pass);
        }
        
        // Eventos ManagementPage
        // clientes
        if (e.getSource() == managementPage.getBtnAddCustomer()) {
            addCustomer();
        }
        
        if (e.getSource() == managementPage.getBtnModifyCustomer()) {
            modifyCustomer();
        }
        
        if (e.getSource() == managementPage.getBtnDeleteCustomer()) {
            deleteCustomer();
        }
        
        // suppliers
        if (e.getSource() == managementPage.getBtnAddSupplier()) {
            addSupplier();
        }
        
        if(e.getSource() == managementPage.getBtnDeleteSupplier()) {
            deleteSupplier();
        }
        
        if (e.getSource() == managementPage.getBtnModifySupplier()) {
            modifySupplier();
        }
        
        // productos
        if (e.getSource() == managementPage.getBtnAddProduct()) {
            addProduct();
        }
        
        if (e.getSource() == managementPage.getBtnModifyProduct()) {
            modifyProduct();
        }
        
        if (e.getSource() == managementPage.getBtnDeleteProduct()) {
            deleteProduct();
        }
         
        
    }

    private void addUser(String pass) {
        
        User userAdmin = userService.findUserById(1);
        String AdminPass = userAdmin.getPassword();
        if (AdminPass.equals(pass)) {
            String fullName = newUserPage.getFullName();
            String userName = newUserPage.getUserName();
            String phone = newUserPage.getPhone();
            String email = newUserPage.getEmail();
            String passwd = newUserPage.getPass();
            
            if (fullName.isEmpty() || userName.isEmpty() || phone.isEmpty() || passwd.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(newUserPage, "Todos los campos son obligatorios");
            } else {
                User newUser = new User(null, passwd, fullName, userName, phone, email);
                userService.addModifyUser(newUser);
                JOptionPane.showMessageDialog(newUserPage, "Usuario añadido correctamente");
                newUserPage.clean();
            }
             
        } else {
            JOptionPane.showMessageDialog(newUserPage, "Sólo puede añadir usuarios el Administrador");
        }
        
        
    }

    private void AccessControl(String name, String pass) {
        
        List<User> listUsers = userService.getAllUsers();
        boolean access = listUsers.stream().anyMatch(u -> u.getUserName().equals(name) && u.getPassword().equals(pass));
        
        
        if (access) {
            managementPage.setModal(true);
            managementPage.setLocationRelativeTo(null);
            managementPage.getJTabbedPanel().setSelectedIndex(4);
            managementPage.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(loginPage, "Acceso denegado"); 
        }
        
    }

    private void addCustomer() {
        String name = managementPage.getNameCustomer();
        String addres = managementPage.getAddressCustomer();
        String phone = managementPage.getPhoneCustomer();
        String email = managementPage.getEmailCustomer();
        
        if (name.isEmpty() || addres.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            Customer customer = new Customer(null, name, phone, addres, email);
            customerService.addModifyCustomer(customer); 
            JOptionPane.showMessageDialog(managementPage, "Cliente añadido correctamente");
            managementPage.cleanCustomer();
            fillCustomerTable();
        }
            
        
    }

    private void fillCustomerTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] cabeceras = {"ID", "Nombre", "Teléfono", "Dirección", "E-Mail"};
        model.setColumnIdentifiers(cabeceras);
        managementPage.getTableCustomer().setModel(model);
        List<Customer> listCustomer = customerService.getAllCustomers();
        
        listCustomer.forEach((customer) ->{
            
            Object[] customerLine = {
                customer.getIdCustomer(),
                customer.getName(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getEmail()
                
            };
            model.addRow(customerLine);
        });  
    }
    
    private void fillSupplierTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] cabeceras = {"ID", "Nombre", "Teléfono", "Dirección", "CIF", "E-Mail"};
        model.setColumnIdentifiers(cabeceras);
        managementPage.getTableSupplier().setModel(model);
        List<Supplier> listSupplier = supplierService.getAllSuppliers();
        
        listSupplier.forEach((supplier) ->{
            
            Object[] supplierLine = {
                supplier.getIdSupplier(),
                supplier.getName(),
                supplier.getPhone(),
                supplier.getAddres(),
                supplier.getCif(),
                supplier.getEmail()
            };
            model.addRow(supplierLine);
        });  
    }
    
    private void fillPruductTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] cabeceras = {"ID", "Descripción", "Precio", "Sección", "Stock"};
        model.setColumnIdentifiers(cabeceras);
        managementPage.getTableProduct().setModel(model);
        List<Product> listProduct = productService.getAllProducts();
        
        listProduct.forEach((product) -> {
            Object[] productLine = {
                product.getIdProduct(),
                product.getDescription(),
                product.getPrice(),
                product.getFamily(),
                product.getStock()
            };
            model.addRow(productLine);
        });
    }
    

    private void modifyCustomer() {
        Integer customerId = idCustomer;
        String address = managementPage.getAddressCustomer();
        String name = managementPage.getNameCustomer();
        String phone = managementPage.getPhoneCustomer();
        String email = managementPage.getEmailCustomer();
        if (address.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            if (customerId != null) {
                Customer customer = new Customer(customerId, name, phone, address, email);
                customerService.addModifyCustomer(customer);
                JOptionPane.showMessageDialog(managementPage, "Datos del cliente modificado correctamente");
                managementPage.cleanCustomer();
                idCustomer = null;
                fillCustomerTable();
            } else {
                JOptionPane.showMessageDialog(managementPage, "Seleccione un cliente para modificar sus datos");
            }       
        }
        
    }

    private void deleteCustomer() {
        Integer customerId = idCustomer;
        String address = managementPage.getAddressCustomer();
        String name = managementPage.getNameCustomer();
        String phone = managementPage.getPhoneCustomer();
        String email = managementPage.getEmailCustomer();
        if (customerId != null) {
            Customer customer = new Customer(customerId, name, phone, address, email);
            customerService.deleteCustomer(customer);
            JOptionPane.showMessageDialog(managementPage, "Cliente eliminado correctamente");
            managementPage.cleanCustomer();
            idCustomer = null;
            fillCustomerTable();
        } else {
            JOptionPane.showMessageDialog(managementPage, "Seleccione un cliente para eliminar");
        }
    }

    // hay que comprobar que no se añaden dos proveedores o clientes con el mismos dni, telefono, cif para evitar duplicados en una base de datos grande
    private void addSupplier() {
        String name = managementPage.getEdtNameSupplier();
        String address = managementPage.getAddresSupplier();
        String phone = managementPage.getEdtPhoneSupplier();
        String email = managementPage.getEdtEmailSupplier();
        String cif = managementPage.getEdtCifSupplier();
        
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || cif.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            Supplier supplier = new Supplier(null, name, cif, phone, address, email);
            supplierService.addModifySupplier(supplier); 
            JOptionPane.showMessageDialog(managementPage, "Proveedor añadido correctamente");
            managementPage.cleanSupplier();
            fillSupplierTable();
        }
    }

    private void deleteSupplier() {
        Integer supplierId = idSupplier;
        String address = managementPage.getAddresSupplier();
        String name = managementPage.getEdtNameSupplier();
        String phone = managementPage.getEdtPhoneSupplier();
        String email = managementPage.getEdtEmailSupplier();
        String cif = managementPage.getEdtCifSupplier();
        if (supplierId != null) {
            Supplier supplier = new Supplier(supplierId, name, cif, phone, address, email);
            supplierService.deleteSupplier(supplier);
            JOptionPane.showMessageDialog(managementPage, "Proveedor eliminado correctamente");
            managementPage.cleanSupplier();
            idSupplier = null;
            fillSupplierTable();
        } else {
            JOptionPane.showMessageDialog(managementPage, "Seleccione un proveedor para eliminar");
        }
    }

    private void modifySupplier() {
        Integer supplierId = idSupplier;
        String address = managementPage.getAddresSupplier();
        String name = managementPage.getEdtNameSupplier();
        String phone = managementPage.getEdtPhoneSupplier();
        String email = managementPage.getEdtEmailSupplier();
        String cif = managementPage.getEdtCifSupplier();
        if (address.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty() || cif.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            if (supplierId != null) {
                Supplier supplier = new Supplier(supplierId, name, cif, phone, address, email);
                supplierService.addModifySupplier(supplier);
                JOptionPane.showMessageDialog(managementPage, "Proveedor modificado correctamente");
                managementPage.cleanSupplier();
                idSupplier = null;
                fillSupplierTable();
            } else {
                JOptionPane.showMessageDialog(managementPage, "Seleccione un proveedor para modificar sus datos");
            }       
        }
    }

    private void addProduct() {
        String description = managementPage.getEdtNameProduct();
        String seccion = managementPage.getCmbFamilyProduct();
        String price = managementPage.getEdtPriceProduct();
        String stock = managementPage.getEdtStckProduct();
        
        if (description.isEmpty() || seccion.equals("Seleccione sección") || price.isEmpty() || stock.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            try {
                Double priceDouble = Double.valueOf(price);
                Integer stockInt = Integer.valueOf(stock);
                Product product = new Product(null, description, priceDouble, seccion, stockInt);
                productService.addModifyProduct(product);
                JOptionPane.showMessageDialog(managementPage, "Producto añadido correctamente");
                managementPage.cleanProduct();
                fillPruductTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(managementPage, "Los campos stock y precio tienen que ser números");
            }
        }
    }

    private void modifyProduct() {
        Integer id = idProduct;
        String description = managementPage.getEdtNameProduct();
        String seccion = managementPage.getCmbFamilyProduct();
        String price = managementPage.getEdtPriceProduct();
        String stock = managementPage.getEdtStckProduct();
        
        if (description.isEmpty() || seccion.equals("Seleccione sección") || price.isEmpty() || stock.isEmpty()) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            try {
                Double priceDouble = Double.valueOf(price);
                Integer stockInt = Integer.valueOf(stock);
                Product product = new Product(id, description, priceDouble, seccion, stockInt);
                productService.addModifyProduct(product);
                JOptionPane.showMessageDialog(managementPage, "Producto modificado correctamente");
                idProduct = null;
                managementPage.cleanProduct();
                fillPruductTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(managementPage, "Los campos stock y precio tienen que ser números");
            }
        }
        
    }

    private void deleteProduct() {
        Integer id = idProduct;
        String description = managementPage.getEdtNameProduct();
        String seccion = managementPage.getCmbFamilyProduct();
        String price = managementPage.getEdtPriceProduct();
        String stock = managementPage.getEdtStckProduct();
        
        if (description.isEmpty() || seccion.equals("Seleccione sección") || price.isEmpty() || stock.isEmpty() || id == null) {
            JOptionPane.showMessageDialog(managementPage, "Todos los campos son obligatorios");
        } else {
            try {
                Double priceDouble = Double.valueOf(price);
                Integer stockInt = Integer.valueOf(stock);
                Product product = new Product(id, description, priceDouble, seccion, stockInt);
                productService.deleteProduct(product);
                JOptionPane.showMessageDialog(managementPage, "Producto eliminado correctamente");
                idProduct = null;
                managementPage.cleanProduct();
                fillPruductTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(managementPage, "Los campos stock y precio tienen que ser números");
            }
        }
        
    }

    

    
    
    
}
