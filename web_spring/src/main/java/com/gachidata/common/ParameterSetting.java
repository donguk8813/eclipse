package com.gachidata.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetting {
	public void setParameter(PreparedStatement pstmt) throws SQLException;
}
