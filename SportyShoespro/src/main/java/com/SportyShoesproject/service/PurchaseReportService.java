package com.SportyShoesproject.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SportyShoesproject.model.PurchaseReport;
import com.SportyShoesproject.repository.PurchaseReportRepository;

@Service
public class PurchaseReportService {

	@Autowired
	private PurchaseReportRepository purchaseReportRepository;

	
	 
	public List<PurchaseReport> getAllPurchaseReport() {
		List<PurchaseReport> purchaseReports = purchaseReportRepository.findAll();
		return purchaseReports;
	}

	public List<PurchaseReport> getPurchaseReportBasedOnCategory(String category) {
		List<PurchaseReport> purchaseReports = purchaseReportRepository.findAllByCategory(category);
		return purchaseReports;
	}

	public List<PurchaseReport> getPurchaseReportBasedOnDate(String date) throws ParseException {
		List<PurchaseReport> purchaseReports = purchaseReportRepository.findAllByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		return purchaseReports;
	}

	

	
}
