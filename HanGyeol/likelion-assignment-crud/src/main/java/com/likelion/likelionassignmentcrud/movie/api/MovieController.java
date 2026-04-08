package com.likelion.likelionassignmentcrud.movie.api;

import com.likelion.likelionassignmentcrud.movie.api.dto.request.MovieSaveRequestDto;
import com.likelion.likelionassignmentcrud.movie.api.dto.request.MovieUpdateRequestDto;
import com.likelion.likelionassignmentcrud.movie.api.dto.response.MovieListResponseDto;
import com.likelion.likelionassignmentcrud.movie.application.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
@Tag(name = "MOVIE API", description = "영화 관리하는 api ")
public class MovieController {
    private final MovieService movieService;

    // 영화 저장
    @PostMapping()
    @Operation(summary = "영화 저장", description = "영화 저장.")
    public ResponseEntity<String> movieSave(@RequestBody MovieSaveRequestDto movieSaveRequestDto) {
        movieService.movieSave(movieSaveRequestDto);
        return new ResponseEntity<>("영화 저장!", HttpStatus.CREATED);
    }

    // 감독 id를 기준으로 해당 감독이 연출한 영화 목록 조회
    @GetMapping("/{directorId}")
    @Operation(summary = "영화 directorId로 조회", description = "영화 directorId로 조회")
    public ResponseEntity<MovieListResponseDto> myMovieAll(@PathVariable("directorId") Long directorId) {
        MovieListResponseDto movieListResponseDto = movieService.movieFindDirector(directorId);
        return new ResponseEntity<>(movieListResponseDto, HttpStatus.OK);
    }

    // 영화 id를 기준으로 영화 정보 수정
    @PatchMapping("/{movieId}")
    @Operation(summary = "영화 Id로 수정", description = "영화 제목, 장르 수정")
    public ResponseEntity<String> movieUpdate(@PathVariable("movieId") Long movieId,
                                              @RequestBody MovieUpdateRequestDto movieUpdateRequestDto) {
        movieService.movieUpdate(movieId, movieUpdateRequestDto);
        return new ResponseEntity<>("영화 수정", HttpStatus.OK);
    }

    // 영화 id를 기준으로 영화 삭제
    @DeleteMapping("/{movieId}")
    @Operation(summary = "영화 삭제", description = "영화 Id로 삭제")
    public ResponseEntity<String> movieDelete(@PathVariable("movieId") Long movieId) {
        movieService.movieDelete(movieId);
        return new ResponseEntity<>("영화 삭제", HttpStatus.OK);
    }
}