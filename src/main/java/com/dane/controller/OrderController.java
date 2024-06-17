/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dane.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.dane.dao.ItemDAO;
import com.dane.dao.OrderDAO;
import com.dane.entity.Inventory;
import com.dane.entity.Item;
import com.dane.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;

public class OrderController extends ActionSupport {

	private Order order = new Order();
	private OrderDAO dao = new OrderDAO();
	private List<Order> orderList;
	private String sm = "";
	private String em = "";

	private Item item = new Item();
	private ItemDAO itemDao = new ItemDAO();
	private List<Item> itemList;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
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

	public String listOrder() {
		this.orderList = dao.getAllOrder();
		return SUCCESS;
	}

	public String addOrder() {
		// get for droplist item
		this.itemList = itemDao.getAllItem();
		return SUCCESS;
	}

	public String insertOrder() {
		// get for droplist item
		this.itemList = itemDao.getAllItem();

		int itemId = order.getItemId();
		if (itemId == 0) {
			setEm("Item is required");
			return "input";
		} else {
			order.setItemId(order.getItemId());
		}
		String orderNo = order.getOrderNo();
		if (orderNo == null || orderNo.trim().isEmpty()) {
			setEm("Order No is required");
			return "input";
		} else {
			order.setOrderNo(orderNo);
		}
		order.setQty(order.getQty());

		int stock = 0;
		if (itemDao.getItem(itemId) != null) {
			Item p = itemDao.getItem(itemId);
			item.setId(p.getId());
			item.setName(p.getName());
			item.setPrice(p.getPrice());

			// update stock
			stock = p.getStock() - order.getQty();
			if (stock <= 0) {
				setEm("Insufficient stock");
				return "input";
			} else {
				item.setStock(stock);
			}
			itemDao.updateItem(item);
		}

		boolean status = dao.saveOrder(order);
		if (status) {
			setSm("Order Saved successfully");
			return SUCCESS;
		} else {
			setEm("Order not saved successfully");
			return "input";
		}
	}

	public String updateOrder() {
		order.setId(order.getId());
		order.setItemId(order.getItemId());
		String orderNo = order.getOrderNo();
		if (orderNo == null || orderNo.trim().isEmpty()) {
			setEm("Order No is required");
			return "input";
		} else {
			order.setOrderNo(orderNo);
		}
		order.setQty(order.getQty());

		int stock = 0;
		int preStock = 0;
		if (dao.getOrder(order.getId()) != null) {
			Order p = dao.getOrder(order.getId());
			preStock = p.getQty();

			if (itemDao.getItem(order.getItemId()) != null) {
				Item pi = itemDao.getItem(order.getItemId());
				item.setId(pi.getId());
				item.setName(pi.getName());
				item.setPrice(pi.getPrice());

				// update stock
				stock = (pi.getStock() + preStock) - order.getQty();
				if (stock <= 0) {
					setEm("Insufficient stock");
					return "input";
				} else {
					item.setStock(stock);
				}
				itemDao.updateItem(item);
			}
		}

		boolean status = dao.updateOrder(order);
		if (status) {
			setSm("Order Update successfully");
			return SUCCESS;
		} else {
			setEm("Order not Update");
			return "input";
		}
	}

	public String editOrder() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		int id = Integer.parseInt(request.getParameter("id"));

		if (dao.getOrder(id) != null) {
			Order p = dao.getOrder(id);
			order.setId(p.getId());
			order.setItemId(p.getItemId());
			order.setOrderNo(p.getOrderNo());
			order.setQty(p.getQty());
			return SUCCESS;
		}

		return "input";

	}

	public String deleteOrder() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		int id = Integer.parseInt(request.getParameter("id"));

		int stock = 0;
		if (dao.getOrder(id) != null) {
			Order p = dao.getOrder(id);

			if (itemDao.getItem(p.getItemId()) != null) {
				Item pi = itemDao.getItem(p.getItemId());
				item.setId(pi.getId());
				item.setName(pi.getName());
				item.setPrice(pi.getPrice());

				// update stock
				stock = pi.getStock() + p.getQty();
				item.setStock(stock);
				itemDao.updateItem(item);
			}
		}

		boolean status = dao.deleteOrder(id);

		if (status) {
			setSm("Order deleted successfully");
			this.orderList = dao.getAllOrder();
			return SUCCESS;
		} else {
			setEm("Order not delete");
			return "input";
		}
	}
}
