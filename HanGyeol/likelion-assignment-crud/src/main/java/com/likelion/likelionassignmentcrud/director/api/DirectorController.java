package com.likelion.likelionassignmentcrud.director.api;

import com.likelion.likelionassignmentcrud.director.api.dto.request.DirectorSaveRequestDto;
import com.likelion.likelionassignmentcrud.director.api.dto.request.DirectorUpdateRequestDto;
import com.likelion.likelionassignmentcrud.director.api.dto.response.DirectorInfoResponseDto;
import com.likelion.likelionassignmentcrud.director.api.dto.response.DirectorListResponseDto;
import com.likelion.likelionassignmentcrud.director.application.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/director")
@Tag(name = "감독 API", description = "감독 정보를 관리하는 API입니다.")
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    @Operation(summary = "감독 저장", description = "새로운 감독 정보를 저장합니다.")
    public ResponseEntity<String> directorSave(@RequestBody DirectorSaveRequestDto directorSaveRequestDto) {
        directorService.directorSave(directorSaveRequestDto);
        return new ResponseEntity<>("감독 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "감독 전체 조회", description = "등록된 모든 감독의 목록을 조회합니다.")
    public ResponseEntity<DirectorListResponseDto> directorFindAll() {
        DirectorListResponseDto directorListResponseDto = directorService.directorFindAll();
        return new ResponseEntity<>(directorListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{directorId}")
    @Operation(summary = "감독 단건 조회", description = "감독 ID를 통해 특정 감독의 상세 정보를 조회합니다.")
    public ResponseEntity<DirectorInfoResponseDto> directorFindOne(@PathVariable("directorId") Long directorId) {
        DirectorInfoResponseDto directorInfoResponseDto = directorService.directorFindOne(directorId);
        return new ResponseEntity<>(directorInfoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{directorId}")
    @Operation(summary = "감독 정보 수정", description = "감독 ID를 통해 기존 감독의 정보를 수정합니다.")
    public ResponseEntity<String> directorUpdate(@PathVariable("directorId") Long directorId, @RequestBody DirectorUpdateRequestDto directorUpdateRequestDto){
        directorService.directorUpdate(directorId, directorUpdateRequestDto);
        return new ResponseEntity<>("감독 수정 성공!", HttpStatus.OK);
    }

    @DeleteMapping("/{directorId}")
    @Operation(summary = "감독 삭제", description = "감독 ID를 통해 감독 정보를 삭제합니다. 연관된 영화도 함께 삭제될 수 있습니다.")
    public ResponseEntity<String> directorDelete(@PathVariable("directorId") Long directorId) {
        directorService.directorDelete(directorId);
        return new ResponseEntity<>("감독 삭제 성공!", HttpStatus.OK);
    }
}