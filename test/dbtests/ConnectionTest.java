package dbtests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import data.DAOStaticData;
import data.DBHelper;
import order.Extra;
import order.Pizza;

public class ConnectionTest {
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException{
		Connection conn = DBHelper.getInstance().connect();
		assertTrue(conn!=null);
		conn.close();
	}
	@Test
	public void testPizzas() throws ClassNotFoundException, SQLException{
		Connection conn = DBHelper.getInstance().connect();
		assertTrue(conn!=null);
		List<Pizza> pizzas = DAOStaticData.getAll();
		assertTrue(pizzas != null);
		assertTrue(pizzas.size()==14);
		conn.close();
	}
	
	@Test
	public void testExtra() throws ClassNotFoundException, SQLException{
		Connection conn = DBHelper.getInstance().connect();
		assertTrue(conn!=null);
		List<Extra> extras = DAOStaticData.getAllExtras();
		assertTrue(extras != null);
		assertTrue(extras.size()==7);
		conn.close();
	}
}
