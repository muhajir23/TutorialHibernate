/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.OrdersModel;
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
public class OrdersDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();
    
    public Session openSession(){
        return session.openSession();
    }
    
    public List<OrdersModel> getAll(){
        Session sess = this.openSession();
        Criteria c = sess.createCriteria(OrdersModel.class).addOrder(Order.asc("order_id"));
        List<OrdersModel> data = c.list();
        return data;
    }
    
    public void save(OrdersModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.save(x);
        t.commit();
    }
    
    public void saveOrUpdate(OrdersModel x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        sess.saveOrUpdate(x);
        t.commit();
    }
    
    public OrdersModel takeId(int x){
        Session sess = this.openSession();
        return (OrdersModel) sess.load(OrdersModel.class, x);
    }
    
    public void deleteById(int x){
        Session sess = this.openSession();
        Transaction t = sess.beginTransaction();
        String sql = "delete from OrdersModel where order_id= '"+x+"'";
        Query query = sess.createQuery(sql);
        query.executeUpdate();
        t.commit();
    }
}
