package jdbc_servlet_product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductCRUD {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "root");
		return connection;
	}
	public int saveProduct(Product product) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?,?)");
		preparedStatement.setInt(1, product.getId());
		preparedStatement.setString(2, product.getName());
		preparedStatement.setDouble(3, product.getPrice());
		preparedStatement.setString(4, product.getState());
		preparedStatement.setString(5, product.getBrand());
		

		int count = preparedStatement.executeUpdate();

		connection.close();
		return count;
	}

}
