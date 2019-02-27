package com.xiaonan.learning.microserivce.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xiaonan.learning.microserivce.moviecatalogservice.models.CatalogItem;
import com.xiaonan.learning.microserivce.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@RequestMapping("{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		RestTemplate restTemplate = new RestTemplate();
		
		//get all rated movie IDs
		List<Rating> ratings = Arrays.asList(
				new Rating("124", 5));
		
		return ratings.stream().map(rating -> 
			new CatalogItem("Transformer", "test", 4)
		)
			.collect(Collectors.toList());
		
		
		
		//For each movie ID, call get movie info to get detail
		
		//Put them all together
		/*return Collections.singletonList(
				new CatalogItem("Transformers", "Test", 4)
		);*/
	}
}
