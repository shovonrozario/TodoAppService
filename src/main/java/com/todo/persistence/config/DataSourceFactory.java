package com.todo.persistence.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.todo.constants.DatabaseConstants;

public class DataSourceFactory {
	private static ComboPooledDataSource dataSource;
	
	public static DataSource getDataSource() {
		if(dataSource == null) {
			dataSource = new ComboPooledDataSource();
			try {
				dataSource.setDriverClass(DatabaseConstants.DRIVER);
				dataSource.setJdbcUrl(DatabaseConstants.URL);
				dataSource.setUser(DatabaseConstants.USER);
				dataSource.setPassword(DatabaseConstants.PASSWORD);
				// todo next - configure min-max pool size, acquireIncrement, maxIdleTime and other datasource properties if necessary
			} catch (PropertyVetoException e) {
				e.printStackTrace();
				return null;
			}
		}
		return dataSource;
	}
}
