package com.rick.Bookland;

import com.rick.Bookland.Models.AutoresRepository;
import com.rick.Bookland.Models.LibrosRepository;
import com.rick.Bookland.Principal.principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BooklandApplication implements CommandLineRunner {
	@Autowired
	AutoresRepository arepository;
	@Autowired
	LibrosRepository lrepository;

	public static void main(String[] args) {
		SpringApplication.run(BooklandApplication.class, args);}

	@Override
	public void run(String...args) throws Exception {
		principal UI = new principal(lrepository,arepository);
		UI.IniciarUI();
	}
}
