package com.gui.api_gameslib;

import jdk.jfr.DataAmount;
import org.hibernate.dialect.Database;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class ApiGameslibApplicationTests {

	@Autowired
	private DataSource dataSource;

	public String email;
	public String password;
	@Test
	void TestPostreSQLConnected() {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("Connection successful: " + !connection.isClosed());
		} catch (SQLException e) {
			System.err.println("Failed to connect to the database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void CreatingOneUser() {

	}
}
