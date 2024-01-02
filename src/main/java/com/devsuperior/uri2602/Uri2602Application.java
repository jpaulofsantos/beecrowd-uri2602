package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		List<CustomerMinProjection> listRS = customerRepository.searchSQL("rs");
		List<CustomerMinProjection> listMG = customerRepository.searchSQL("Mg");
		List<CustomerMinDTO> result = listRS.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());

		System.out.println("");
		System.out.println("---RS SQL---");

		for (CustomerMinProjection item : listRS) {
			System.out.println(item.getName());
		}

		System.out.println("");
		System.out.println("---MG SQL---");

		for (CustomerMinProjection item : listMG) {
			System.out.println(item.getName());
		}

		System.out.println("");
		System.out.println("---RS SQL DTO---");

		for (CustomerMinDTO item : result) {
			System.out.println(item.getName());
		}


		List<CustomerMinDTO> listRSjpql = customerRepository.searchJPQL("rs");
		List<CustomerMinDTO> listMGjpql = customerRepository.searchJPQL("Mg");

		System.out.println("");
		System.out.println("---RS JPQL---");

		for (CustomerMinDTO item : listRSjpql) {
			System.out.println(item.getName());
		}


	}
}
