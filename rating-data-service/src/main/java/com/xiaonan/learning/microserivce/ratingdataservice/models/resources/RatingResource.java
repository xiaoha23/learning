package com.xiaonan.learning.microserivce.ratingdataservice.models.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaonan.learning.microserivce.ratingdataservice.models.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	
	@RequestMapping("{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

}
