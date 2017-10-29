package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Extra;
import pojos.Pizza;

public class DAOStaticData {
	public DAOStaticData(){
		
	}
	
	public static List<Pizza> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DBHelper.getInstance().connect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM pizza order by name;");
		ResultSet rs = ps.executeQuery();
		List<Pizza> pizzas = new ArrayList<>();
		while(rs.next()){
			Pizza pizza = new Pizza();
			pizza.setId(rs.getInt("id"));
			pizza.setName(rs.getString("name"));
			pizza.setDescription(rs.getString("description"));
			pizza.setPrice(rs.getDouble("price"));
			pizzas.add(pizza);
		}
		conn.close();
		return pizzas;
	}
	
	public static List<Extra> getAllExtras() throws ClassNotFoundException, SQLException{
		Connection conn = DBHelper.getInstance().connect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM extras order by name;");
		ResultSet rs = ps.executeQuery();
		List<Extra> extras = new ArrayList<>();
		while(rs.next()){
			Extra extra = new Extra();
			extra.setId(rs.getInt("id"));
			extra.setName(rs.getString("name"));
			extra.setDescription(rs.getString("description"));
			extra.setPrice(rs.getDouble("price"));
			extras.add(extra);
		}
		conn.close();
		return extras;
	}
}
