/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dane.controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.dane.dao.ItemDAO;
import com.dane.entity.Item;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;

public class ItemController extends ActionSupport {

    private Item item = new Item();
    private ItemDAO dao = new ItemDAO();
    private List<Item> itemList;
    private String sm = "";
    private String em = "";

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemDAO getDao() {
        return dao;
    }

    public void setDao(ItemDAO dao) {
        this.dao = dao;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String listItem() {
        this.itemList = dao.getAllItem();
        return SUCCESS;
    }

    public String insertItem() {
    	String name = item.getName();
    	if(name==null || name.trim().isEmpty()){
    		setEm("Item Name is required");
            return "input";
    	}else {
    		item.setName(name);
    	}
        item.setPrice(item.getPrice());
        
        boolean status = dao.saveItem(item);
        if (status) {
            setSm("Item Saved successfully");
            return SUCCESS;
        } else {
            setEm("Item not saved successfully");
            return "input";
        }

    }

    public String updateItem() {
        item.setId(item.getId());
        String name = item.getName();
    	if(name==null || name.trim().isEmpty()){
    		setEm("Item Name is required");
            return "input";
    	}else {
    		item.setName(name);
    	}
        item.setPrice(item.getPrice());
        
        boolean status = dao.updateItem(item);
        if (status) {
            setSm("Item Update successfully");
            return SUCCESS;
        } else {
            setEm("Item not Update");
            return "input";
        }

    }

    public String editItem() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("id"));

        if (dao.getItem(id) != null) {
            Item p = dao.getItem(id);
            item.setId(p.getId());
            item.setName(p.getName());
            item.setPrice(p.getPrice());
            return SUCCESS;
        }

        return "input";

    }

    public String deleteItem() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("id"));

        boolean status = dao.deleteItem(id);

        if (status) {
            setSm("Item deleted successfully");
            this.itemList = dao.getAllItem();
            return SUCCESS;
        } else {
            setEm("Item not delete");
            return "input";
        }
    }
}
