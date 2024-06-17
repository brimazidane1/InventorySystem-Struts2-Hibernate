/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dane.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.dane.dao.InventoryDAO;
import com.dane.dao.ItemDAO;
import com.dane.entity.Inventory;
import com.dane.entity.Item;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;

public class InventoryController extends ActionSupport {

	private Inventory inventory = new Inventory();
	private InventoryDAO dao = new InventoryDAO();
	private List<Inventory> inventoryList;
	private String sm = "";
	private String em = "";

	private Item item = new Item();
	private ItemDAO itemDao = new ItemDAO();
	private List<Item> itemList;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public InventoryDAO getDao() {
		return dao;
	}

	public void setDao(InventoryDAO dao) {
		this.dao = dao;
	}

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
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

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String listInventory() {
		this.inventoryList = dao.getAllInventory();
		return SUCCESS;
	}

	public String addInventory() {
		// get for droplist item
		this.itemList = itemDao.getAllItem();
		return SUCCESS;
	}

	public String insertInventory() {
		// get for droplist item
		this.itemList = itemDao.getAllItem();

		int itemId = inventory.getItemId();
		if (itemId == 0) {
			setEm("Item is required");
			return "input";
		} else {
			inventory.setItemId(inventory.getItemId());
		}
		inventory.setQty(inventory.getQty());

		String type = inventory.getType();
		if (type == null || type.trim().isEmpty()) {
			setEm("Inventory Type is required");
			return "input";
		} else {
			inventory.setType(type);

			// check if top up or withdrawal
			int id = inventory.getItemId();
			int stock = 0;
			if (itemDao.getItem(id) != null) {
				Item p = itemDao.getItem(id);
				item.setId(p.getId());
				item.setName(p.getName());
				item.setPrice(p.getPrice());

				if ("T".equalsIgnoreCase(type)) {
					stock = p.getStock() + inventory.getQty();
				} else {
					stock = p.getStock() - inventory.getQty();
				}

				// update stock
				item.setStock(stock);
				itemDao.updateItem(item);
			}
		}

		boolean status = dao.saveInventory(inventory);
		if (status) {
			setSm("Inventory Saved successfully");
			return SUCCESS;
		} else {
			setEm("Inventory not saved successfully");
			return "input";
		}

	}

	public String updateInventory() {
		inventory.setId(inventory.getId());
		inventory.setItemId(inventory.getItemId());
		inventory.setQty(inventory.getQty());

		String type = inventory.getType();
		if (type == null || type.trim().isEmpty()) {
			setEm("Inventory Type is required");
			return "input";
		} else {
			inventory.setType(type);

			// check if top up or withdrawal
			int id = inventory.getItemId();
			int stock = 0;
			int preStock = 0;
			if (dao.getInventory(inventory.getId()) != null) {
				Inventory pi = dao.getInventory(inventory.getId());
				preStock = pi.getQty();
			}

			if (itemDao.getItem(id) != null) {
				Item p = itemDao.getItem(id);
				item.setId(p.getId());
				item.setName(p.getName());
				item.setPrice(p.getPrice());

				if ("T".equalsIgnoreCase(type)) {
					stock = (p.getStock() - preStock) + inventory.getQty();
				} else {
					stock = (p.getStock() + preStock) - inventory.getQty();
				}

				// update stock
				item.setStock(stock);
				itemDao.updateItem(item);
			}
		}

		boolean status = dao.updateInventory(inventory);
		if (status) {
			setSm("Inventory Update successfully");
			return SUCCESS;
		} else {
			setEm("Inventory not Update");
			return "input";
		}

	}

	public String editInventory() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		int id = Integer.parseInt(request.getParameter("id"));

		if (dao.getInventory(id) != null) {
			Inventory p = dao.getInventory(id);
			inventory.setId(p.getId());
			inventory.setItemId(p.getItemId());
			inventory.setQty(p.getQty());
			inventory.setType(p.getType());
			return SUCCESS;
		}

		return "input";

	}

	public String deleteInventory() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		int id = Integer.parseInt(request.getParameter("id"));

		if (dao.getInventory(id) != null) {
			Inventory p = dao.getInventory(id);

			// check if top up or withdrawal
			String type = p.getType();
			int itemId = p.getItemId();
			int stock = 0;
			if (itemDao.getItem(itemId) != null) {
				Item pi = itemDao.getItem(itemId);
				item.setId(pi.getId());
				item.setName(pi.getName());
				item.setPrice(pi.getPrice());

				if ("T".equalsIgnoreCase(type)) {
					stock = pi.getStock() - p.getQty();
				} else {
					stock = pi.getStock() + p.getQty();
				}

				// update stock
				item.setStock(stock);
				itemDao.updateItem(item);
			}
		}

		boolean status = dao.deleteInventory(id);

		if (status) {
			setSm("Inventory deleted successfully");
			this.inventoryList = dao.getAllInventory();
			return SUCCESS;
		} else {
			setEm("Inventory not delete");
			return "input";
		}
	}
}
