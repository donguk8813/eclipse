package com.gachidata.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapping<T> {
	public  T mapResultSet(ResultSet res) throws SQLException ;

}
