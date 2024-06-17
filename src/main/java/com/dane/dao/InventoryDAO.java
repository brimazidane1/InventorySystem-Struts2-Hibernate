/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dane.dao;

import com.dane.entity.Inventory;
import com.dane.entity.Item;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.dane.util.HibernateUtil;

public class InventoryDAO {

    public List<Inventory> getAllInventory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        String sqlQuery = "SELECT e FROM "+Inventory.class.getName() +" e";

        try {
            t = session.beginTransaction();
            List<Inventory> li = session.createQuery(sqlQuery).list();
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

    public boolean saveInventory(Inventory p) {
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

    public boolean updateInventory(Inventory p) {
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

    public boolean deleteInventory(int id) {
        String hql = "delete from t_inventory where id = :id";
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

    public Inventory getInventory(int id) {
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	Session session = factory.openSession();
        Transaction t = null;
        Inventory inventory = null;
        try {
            t = session.beginTransaction();
            inventory = (Inventory) session.get(Inventory.class, id);
            t.commit();
            return inventory;
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
