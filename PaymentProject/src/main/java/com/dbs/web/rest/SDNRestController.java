package com.dbs.web.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbs.web.service.SdnProcessing;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sdn")
public class SDNRestController {
	
	@Autowired
	private SdnProcessing service;
	@GetMapping("/{name}")
	public ResponseEntity<String> Checksdn(@PathVariable String name)
	{
		if(this.service.checkSDNlist(name)) { 
		
			return ResponseEntity.status(HttpStatus.OK)
					.body("true");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK)
					.body("false");
		}
	}
}