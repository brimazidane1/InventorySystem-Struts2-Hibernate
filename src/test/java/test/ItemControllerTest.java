package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Test;

import com.dane.controller.ItemController;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class ItemControllerTest extends StrutsJUnit4TestCase {
	
	@Test
	public void testNoData() throws Exception {
		ActionProxy actionProxy = getActionProxy("insertItem");
		ItemController action = (ItemController) actionProxy.getAction();
		assertNotNull("The action is null but should not be.", action);
	
		String result = actionProxy.execute();
		assertEquals("The execute method did not return " + ActionSupport.INPUT + " but should have.", ActionSupport.INPUT, result);
	}
	
	@Test
	public void testExecuteValidationPasses() throws Exception {
		request.setParameter("item.name", "Wallet");
		request.setParameter("item.price", "10");
		
		ActionProxy actionProxy = getActionProxy("insertItem");
		ItemController action = (ItemController) actionProxy.getAction();
		assertNotNull("The action is null but should not be.", action);
	
		String result = actionProxy.execute();
		assertEquals("The execute method did not return " + ActionSupport.SUCCESS + " but should have.", ActionSupport.SUCCESS, result);
	}
}
