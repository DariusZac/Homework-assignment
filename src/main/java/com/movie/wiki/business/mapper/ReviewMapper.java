package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.model.ReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    @Mapping(target = "movieReview", source = "review")
    @Mapping(target = "date", source = "date", qualifiedByName = "getNewDate")
    Review dtoToEntity(ReviewDto dto);

    @Mapping(target = "review", source = "movieReview")
    ReviewDto entityToDto(Review review);

    @Named("getNewDate")
    default Date getNewDate(Date date) {
        return new Date();
    }
}
