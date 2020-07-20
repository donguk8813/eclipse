package com.gachidata.dong;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(value= {"com.gachidata.dong.board.mapper"})	//Mapper 인터페이스를 인식할 수 있도록 설정
public class SpringBootBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoardApplication.class, args);
	}

	//SqlSessionFactory 설정
	@Bean 
	public SqlSessionFactory sqlSessionfactory(DataSource dataSource) throws Exception{
		 SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
//		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("src/main/java:com.gachidata.dong/board/mapper/*.Mapper.xml"));
		return sessionFactory.getObject();
	}
	
}
