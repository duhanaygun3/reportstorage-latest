package com.example.Laborant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.Laborant.business.abstracts.ReportService;
import com.example.Laborant.business.responses.GetReportByIdResponse;

@SpringBootTest
@Transactional // Hibernate'in lazy loading mekanizmasından dolayı test sırasında bir
				// oturum (session) açık olmadığı için ilişkili Patient nesnesinin verileri
				// yüklenemiyor.

class LaborantApplicationTests {

	@Autowired
	ReportService reportService;

	@Test
	public void TestGetReportById(int id) {
		GetReportByIdResponse reportById = reportService.getReportById(id);

		assertNotNull(reportById);

	}

	@Test
	public void test1() {
		System.out.println("sa");
	}
}
