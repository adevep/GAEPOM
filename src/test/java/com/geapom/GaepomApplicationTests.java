//package com.geapom;
//
//import java.net.URI;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import com.gaepom.domain.Portfolio;
//
////@SpringBootTest
//class GaepomApplicationTests {
//
//	public static final String REST_SERVICE_URI = "http://localhost:80/";
//
//	/* POST */
//	private static void createPortfolio() {
//		System.out.println("Testing create Portfolio API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		Portfolio portfolio = new Portfolio(777L, "포폴1제목", "포폴1기간", "포폴1설명", "포폴1참여도", "포폴1언어, 언어, 언어", "포폴1툴, 툴", "포폴1DBMS", "포폴1링크","포폴1카테고리", false, null);
//		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/portfolio/", portfolio, Portfolio.class);
//		System.out.println("Location : " + uri.toASCIIString());
//	}
//
//	/* GET */
//	@SuppressWarnings("unchecked")
//	private static void listAllPortfolios() {
//		System.out.println("Testing listAllPortfolios API-----------");
//
//		RestTemplate restTemplate = new RestTemplate();
//		List<LinkedHashMap<String, Object>> portfoliosMap = restTemplate.getForObject(REST_SERVICE_URI + "/portfolio/",
//				List.class);
//
//		if (portfoliosMap != null) {
//			for (LinkedHashMap<String, Object> map : portfoliosMap) {
//			}
//		} else {
//			System.out.println("No portfolio exist----------");
//		}
//	}
//
//	/* GET */
//	private static void getPortfolio() {
//		System.out.println("Testing getPortfolio API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		Portfolio portfolio = restTemplate.getForObject(REST_SERVICE_URI + "/portfolio/1", Portfolio.class);
//		System.out.println(portfolio);
//	}
//
//	/* PUT */
//	private static void updatePortfolio() {
//		System.out.println("Testing update Portfolio API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		Portfolio portfolio = new Portfolio(777L, "포폴1제목=================", "포폴1기간", "포폴1설명", "포폴1참여도", "포폴1언어, 언어, 언어", "포폴1툴, 툴", "포폴1DBMS", "포폴1링크","포폴1카테고리", false, null);
//		restTemplate.put(REST_SERVICE_URI + "/portfolio/1", portfolio);
//		System.out.println(portfolio);
//	}
//
//	/* DELETE */
//	private static void deletePortfolio() {
//		System.out.println("Testing delete Portfolio API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(REST_SERVICE_URI + "/portfolio/1");
//	}
//
//	/* DELETE */
//	private static void deleteAllPortfolios() {
//		System.out.println("Testing all delete Portfolios API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(REST_SERVICE_URI + "/portfolio/");
//	}
//
//	public static void main(String args[]) {
//		listAllPortfolios();
//		getPortfolio();
//		createPortfolio();
//		listAllPortfolios();
//		updatePortfolio();
//		listAllPortfolios();
//		deletePortfolio();
//		listAllPortfolios();
//		deleteAllPortfolios();
//		listAllPortfolios();
//	}
//
//}
