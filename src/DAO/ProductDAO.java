/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.ProductModel;
import UTIL.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;


public class ProductDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();
    
    public Session openSession(){
        return session.openSession();
    }
    
    public List<ProductModel> getAll(){
        Session sess = this.openSession();
        Criteria c = sess.createCriteria(ProductModel.class).addOrder(Order.asc("product_id"));
        List<ProductModel> data = c.list();
        return data;
    }
    
    public void save(ProductModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.save(x);
        t.commit();
    }
    
    public void saveOrUpdate(ProductModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.saveOrUpdate(x);
        t.commit();
    }
    
    public ProductModel takeId(int x){
        Session sess = this.openSession();
        return (ProductModel) sess.load(ProductModel.class, x);     
    }
    
    public void deleteById(int x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        String sql = "delete from ProductModel where order_id= '"+x+"'";
        Query query = sess.createQuery(sql);
        query.executeUpdate();
        t.commit();
    }
    
}
