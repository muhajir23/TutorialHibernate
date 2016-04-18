/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;

import DAO.CustomerDAO;
import DAO.OrdersDAO;
import DAO.OrdersDetailDAO;
import DAO.ProductDAO;
import MODEL.CustomerModel;
import MODEL.OrdersDetailModel;
import MODEL.OrdersModel;
import MODEL.ProductModel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cyber23
 */
public class Main {
    Scanner sc = new Scanner(System.in);
    
    CustomerDAO custdao = new CustomerDAO();
    ProductDAO proddao = new ProductDAO();
    OrdersDetailDAO orddetdao =  new OrdersDetailDAO();
    OrdersDAO orddao = new OrdersDAO();
    
    Calendar c = Calendar.getInstance();
    
    private Main(){
        int pil = menu();
        
        while (pil>0 && pil <10){
            switch (pil){
                case 1 : 
                    tambahProduct();
                    break;
                case 2 :
                    lihatProduct();
                    break;
                case 3 :
                    lihatCustomer();
                    break;
                case 4 :
                    System.out.println("Id Customer = ");
                    ubahCustomer(sc.nextInt());
                    break;
                case 5 :
                    tambahOrder();
                    break;
                case 6 :
                    lihatOrder();
                    break;
                case 7 :
                    System.out.println("ID Order = ");
                    hapusOrder(sc.nextInt());
                    break;
            }
            
            System.out.println("Apakah kamu ingin keluar Y/N ?");
            if (sc.next().equalsIgnoreCase("y")){
                break;                
            } else {
                pil = menu();
            }
        }
    }
    
    private int menu(){
        System.out.println("::MENU::");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Lihat Produk");
        System.out.println("3. Lihat Customer");
        System.out.println("4. Ubah Customer");
        System.out.println("5. Tambah Order");
        System.out.println("6. Lihat Order");
        System.out.println("7. Hapus Order");
        System.out.println("8. Keluar");
        System.out.print("Pilihan  = ");
        return sc.nextInt();
    }
    
    public void tambahProduct(){
        ProductModel mod = new ProductModel();
        System.out.print("Nama Product = ");
        mod.setProduct_name(sc.next());
        
        System.out.print("Kategori Product = ");
        mod.setCategory(sc.next());
        
        System.out.print("Harga = ");
        mod.setPrice(sc.nextInt());
        
        proddao.save(mod);
        
        System.out.println("Data Product Telah Tersimpan.");
    }
    
    public void lihatProduct(){
        System.out.println("Daftar Produk ");
        List<ProductModel> data = proddao.getAll();
        Iterator it = data.iterator();
        while (it.hasNext()){
            ProductModel x = (ProductModel) it.next();
            System.out.println("ID = "+ x.getProduct_id());
            System.out.println("Nama Product = "+x.getProduct_name());
            System.out.println("Kategori Product = "+x.getCategory());
            System.out.println("Harga = "+ x.getPrice());
            System.out.println("");
        }
    }
    
    public void lihatCustomer(){
        System.out.println("Daftar Costomer");
        List<CustomerModel> data = custdao.getAll();
        Iterator it = data.iterator();
        while (it.hasNext()){
            CustomerModel x = (CustomerModel) it.next();
            System.out.println("Customer Id  = "+x.getCustomer_id());
            System.out.println("Last name = "+x.getLast_name());
            System.out.println("First name = "+x.getFirst_name());
            System.out.println("Address = "+x.getAddress());
            System.out.println("Email = "+x.getEmail());
        }
    }
    
    public void ubahCustomer(int id){
        CustomerModel custmod = new CustomerModel();
        CustomerModel p = (CustomerModel) custdao.takeId(id);
        
        System.out.println("ID  = "+p.getCustomer_id());
        System.out.println("Nama = "+p.getFirst_name()+ " "+p.getLast_name());
        System.out.println("Alamat = "+p.getAddress());
        System.out.println("Email = "+p.getEmail());
        
        custmod.setCustomer_id(id);
        System.out.println("Nama Depan Customer = ");
        String namaDepan = sc.next();
        System.out.println("Nama Belakang Customer = ");
        String namaBelakang = sc.next();
        System.out.println("Alamat Customer = ");
        String address = sc.next();
        System.out.println("Email Customer = ");
        String email = sc.next();
        
        custmod.setLast_name(namaBelakang);
        custmod.setFirst_name(namaDepan);
        custmod.setAddress(address);
        custmod.setEmail(email);
        
        custdao.saveOrUpdate(custmod);
    }
    
    public void tambahOrder(){
        ProductModel prodmod = new ProductModel();
        CustomerModel custmod = new CustomerModel();
        OrdersModel ordmod = new OrdersModel();
        OrdersDetailModel orddetmod = new OrdersDetailModel();
        
        System.out.println("Nama Depan Customer = ");
        String namaDepan = sc.next();
        System.out.println("Nama Belakang Customer = ");
        String namaBelakang = sc.next();
        System.out.println("Alamat Customer = ");
        String address = sc.next();
        System.out.println("Email Customer = ");
        String email = sc.next();
        
        custmod.setLast_name(namaBelakang);
        custmod.setFirst_name(namaDepan);
        custmod.setAddress(address);
        custmod.setEmail(email);
        
        custdao.save(custmod);
        
        System.out.println("Keterangan Order = ");
        String ket = sc.next();
        ordmod.setDescription(ket);
        ordmod.setCustomer_id(custmod.getCustomer_id());
        ordmod.setDate(tanggal());
        
        orddao.save(ordmod);
        
        int pil = 1;
        while (pil!=0){
            System.out.println("ID Barang = ");
            int idBarang = sc.nextInt();
            System.out.println("Banyak = ");
            int banyak = sc.nextInt();
            
            orddetmod.setOrder_id(ordmod.getOrder_id());
            orddetmod.setProduct_id(idBarang);
            orddetmod.setBanyak(banyak);
            
            orddetdao.save(orddetmod);
            System.out.println("Tambah Barang (Y/N) ? ");
            if (sc.next().equalsIgnoreCase("n")){
                break;
            } else {
                pil = 1;
            }
    }
    }
    
    public String tanggal(){
        String tanggal = String.valueOf(c.get(Calendar.DATE));
        String bulan = String.valueOf(c.get(Calendar.MONTH));
        String tahun = String.valueOf(c.get(Calendar.YEAR));
        return tanggal+"/"+bulan+"/"+tahun.substring(2,4);
    }
    
    public void lihatOrder(){
        System.out.println("Daftar Order");
        List<OrdersModel> data = orddao.getAll();
        Iterator it = data.iterator();
        
        while (it.hasNext()){
            OrdersModel x = (OrdersModel) it.next();
            CustomerModel c = (CustomerModel) custdao.takeId(x.getCustomer_id()); 
            System.out.println("ID Order "+ x.getOrder_id());
            System.out.println("Nama Customer "+c.getFirst_name()+ " "+c.getLast_name());
            System.out.println("Tanggal Order "+x.getDate());
            System.out.println("Deskripsi "+x.getDescription());
            System.out.println("List Produk");
            listProductOrder(x.getOrder_id());
        }
    }
    
    public void listProductOrder(int id){
        List<OrdersDetailModel> data = orddetdao.getAllObj(id);
        Iterator it = data.iterator();
        
        while (it.hasNext()){
            OrdersDetailModel x = (OrdersDetailModel) it.next();
            ProductModel p = (ProductModel) proddao.takeId(x.getProduct_id());
            System.out.println("-"+p.getProduct_name()+"\nBanyak "+x.getBanyak());
        }
    }
    
    public void hapusOrder(int id){
        OrdersModel o = (OrdersModel) orddao.takeId(id);
        hapusOrderDetail(id);
        custdao.deleteById(o.getCustomer_id());
        orddao.deleteById(id);
    }
    
    public void hapusOrderDetail(int id){
        List<OrdersDetailModel>  data = orddetdao.getAll(id);
        Iterator it = data.iterator();
        
        while (it.hasNext()){
            OrdersDetailModel x = (OrdersDetailModel) it.next();
            orddetdao.deleteById(x.getProduct_id());
        }
    }
    
    public static void main(String[] args){
        new Main();
    }
}
