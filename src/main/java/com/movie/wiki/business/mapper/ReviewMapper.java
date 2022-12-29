package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.model.ReviewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review dtoToEntity(ReviewDto dto);
    ReviewDto entityToDto(Review review);
}
