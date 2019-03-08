package com.xiaonan.learning.microserivce.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xiaonan.learning.microserivce.moviecatalogservice.models.CatalogItem;
import com.xiaonan.learning.microserivce.moviecatalogservice.models.Movie;
import com.xiaonan.learning.microserivce.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
	
		
		
		//get all rated movie IDs
		/*List<Rating> ratings = Arrays.asList(
				new Rating("1234", 5),
				new Rating("5678", 4));*/
		UserRating userRating = restTemplate.getForObject("http://RATING-SERIVCE/ratingsdata/users/foo", UserRating.class);
		
		
		
		return userRating.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://MOVIE-SERVICE/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "testDesc", rating.getRating());
		})
			.collect(Collectors.toList());
		
		
		
		//For each movie ID, call get movie info to get detail
		
		//Put them all together
		/*return Collections.singletonList(
				new CatalogItem("Transformers", "Test", 4)
		);*/
	}
}
