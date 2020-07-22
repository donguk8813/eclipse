package com.gachidata.dong;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value = { "com.gachidata.dong.board.mapper" }) // Mapper 인터페이스를 인식할 수 있도록 설정
public class DatabaseConfig {
	

	// SqlSessionFactory 설정
	@Bean
	public SqlSessionFactory sqlSessionfactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("com/gachidata/dong/board/mapper/*.xml"));
		sessionFactory.setVfs(SpringBootVFS.class);
		sessionFactory.setTypeAliasesPackage("com.gachidata.dong.board.domain");
		return sessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	
}
