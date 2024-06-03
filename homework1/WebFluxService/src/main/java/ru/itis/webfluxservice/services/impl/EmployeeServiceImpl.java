package ru.itis.webfluxservice.services.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.webfluxservice.entities.Employee;
import ru.itis.webfluxservice.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final WebClient newbieServiceClient;
    private final WebClient proServiceClient;

    public EmployeeServiceImpl(WebClient.Builder webClientBuilder) {
        this.newbieServiceClient = webClientBuilder.baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
                .build();
        this.proServiceClient = webClientBuilder.baseUrl("http://localhost:8082")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
                .build();
    }

    public Flux<Employee> getAllEmployees() {
        Flux<Employee> newbieEmployees = newbieServiceClient.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);

        Flux<Employee> proEmployees = proServiceClient.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);

        return Flux.merge(newbieEmployees, proEmployees);
    }
}
