/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dane.dao;

import com.dane.entity.Item;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.dane.util.HibernateUtil;

public class ItemDAO {

    public List<Item> getAllItem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        String sqlQuery = "SELECT e FROM "+Item.class.getName() +" e";

        try {
            t = session.beginTransaction();
            List<Item> li = session.createQuery(sqlQuery).list();
            t.commit();
            return li;
        } catch (Exception e) {
            if (!(t == null)) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

    public boolean saveItem(Item p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();
            session.save(p);
            t.commit();
            return true;
        } catch (Exception e) {
            if (!(t == null)) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean updateItem(Item p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();
            session.update(p);
            t.commit();
            return true;
        } catch (Exception e) {
            if (!(t == null)) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteItem(int id) {
        String hql = "delete from t_item where id = :id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();
            Query query = session.createSQLQuery(hql);
            query.setInteger("id", id);
            query.executeUpdate();
            t.commit();
            return true;
        } catch (Exception e) {
            if (!(t == null)) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public Item getItem(int id) {
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	Session session = factory.openSession();
        Transaction t = null;
        Item item = null;
        try {
            t = session.beginTransaction();
            item = (Item) session.get(Item.class, id);
            t.commit();
            return item;
        } catch (Exception e) {
            if (!(t == null)) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

}
