/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.OrdersDetailModel;
import UTIL.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;


public class OrdersDetailDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();
    
    public Session openSession(){
        return session.openSession();
    }
    
    public List<OrdersDetailModel> getAll(int id){
        Session sess = this.openSession();
        Query c = sess.createQuery("from OrdersDetailModel where id = '"+id+"'");
        List<OrdersDetailModel> data = c.list();
        return data;
    }
    
    public List<OrdersDetailModel> getAllObj(int id){
        Session sess = this.openSession();
        Query c = sess.createQuery("from OrdersDetailModel where order_id = '"+id+"'");
        List<OrdersDetailModel> data = c.list();
        return data;
    }
    
    public void save(OrdersDetailModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.save(x);
        t.commit();
    }
    
    public void saveOrUpdate(OrdersDetailModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.saveOrUpdate(x);
        t.commit();
    }
    
    public OrdersDetailModel takeId(int x){
        Session sess = this.openSession();
        return (OrdersDetailModel) sess.load(OrdersDetailModel.class, x);
    }
    
    public void deleteById(int x){
        Session sess = this.openSession();        
        Transaction t = sess.beginTransaction();
        String sql = "delete from OrdersDetailModel where order_id= '"+x+"'";
        Query q = sess.createQuery(sql);
        q.executeUpdate();
        t.commit();
    }
}
