package com.example.demo.init;

import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(ProductRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(Product.builder().name("Car BMW").price(24000).date(LocalDate.now()).build()));
      log.info("Preloading " + repository.save(Product.builder().name("Beer Pilsner").price(5).date(LocalDate.now()).build()));
    };
  }
}