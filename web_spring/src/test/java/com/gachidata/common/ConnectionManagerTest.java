package com.gachidata.common;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

	@Test
	void 디비연결테스트() {
		Connection conn = ConnectionManager.getConnection();
		assertNotNull(conn);
	}

}
