/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.CustomerModel;
import UTIL.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author cyber23
 */
public class CustomerDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();
    
    public Session openSession(){
        return session.openSession();
    }
    
    public List<CustomerModel> getAll(){
        Session sess = this.openSession();
        Criteria c = sess.createCriteria(CustomerModel.class).addOrder(Order.asc("customer_id"));
        List<CustomerModel> data = c.list();
        return data;
    }
    
    public void save(CustomerModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.save(x);
        t.commit();
    }
    
    public void saveOrUpdate(CustomerModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.saveOrUpdate(x);
        t.commit();
    }
    
    public CustomerModel takeId(int x){
        Session sess = this.openSession();
        return (CustomerModel) sess.load(CustomerModel.class, x);
    }
    
    public void deleteById(int x){
        Session sess =  this.openSession();
        Transaction t = sess.beginTransaction();
        
        String sql = "delete from CustomerModel where order_id= '"+x+"'";
        Query query = sess.createQuery(sql);
        query.executeUpdate();
        t.commit();
    }
}
