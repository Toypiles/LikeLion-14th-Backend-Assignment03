package com.likelion.likelionassignmentcrud.movie.application;

import com.likelion.likelionassignmentcrud.director.domain.Director;
import com.likelion.likelionassignmentcrud.director.domain.repository.DirectorRepository;
import com.likelion.likelionassignmentcrud.movie.api.dto.request.MovieSaveRequestDto;
import com.likelion.likelionassignmentcrud.movie.api.dto.request.MovieUpdateRequestDto;
import com.likelion.likelionassignmentcrud.movie.api.dto.response.MovieInfoResponseDto;
import com.likelion.likelionassignmentcrud.movie.api.dto.response.MovieListResponseDto;
import com.likelion.likelionassignmentcrud.movie.domain.Movie;
import com.likelion.likelionassignmentcrud.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void movieSave(MovieSaveRequestDto movieSaveRequestDto){
        Director director = directorRepository.findById(movieSaveRequestDto.directorId()).orElseThrow(IllegalArgumentException::new);

        Movie movie = Movie.builder()
                .title(movieSaveRequestDto.title())
                .genre(movieSaveRequestDto.genre())
                .director(director)
                .build();

        movieRepository.save(movie);
    }

    public MovieListResponseDto movieFindDirector(Long directorId) {
        Director director = directorRepository.findById(directorId).orElseThrow(IllegalArgumentException::new);
        List<Movie> movies = movieRepository.findByDirector(director);
        List<MovieInfoResponseDto> movieInfoResponseDtos = movies.stream()
                .map(MovieInfoResponseDto::from)
                .toList();
        return MovieListResponseDto.from(movieInfoResponseDtos);
    }

    @Transactional
    public void movieUpdate(Long movieId, MovieUpdateRequestDto movieUpdateRequestDto) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(IllegalArgumentException::new);
        movie.update(movieUpdateRequestDto);
    }

    @Transactional
    public void movieDelete(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(IllegalArgumentException::new);
        movieRepository.delete(movie);
    }
}
