package com.likelion.likelionassignmentcrud.director.application;

import com.likelion.likelionassignmentcrud.director.api.dto.request.DirectorSaveRequestDto;
import com.likelion.likelionassignmentcrud.director.api.dto.request.DirectorUpdateRequestDto;
import com.likelion.likelionassignmentcrud.director.api.dto.response.DirectorInfoResponseDto;
import com.likelion.likelionassignmentcrud.director.api.dto.response.DirectorListResponseDto;
import com.likelion.likelionassignmentcrud.director.domain.Director;
import com.likelion.likelionassignmentcrud.director.domain.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectorService {
    private final DirectorRepository directorRepository;

    @Transactional
    public void directorSave(DirectorSaveRequestDto directorSaveRequestDto) {
        Director director = Director.builder()
                .name(directorSaveRequestDto.name())
                .age(directorSaveRequestDto.age())
                .debutYear(directorSaveRequestDto.debutYear())
                .part(directorSaveRequestDto.part())
                .build();
        directorRepository.save(director);
    }

    public DirectorInfoResponseDto directorFindOne(Long directorId) {
        Director director = directorRepository
                .findById(directorId)
                .orElseThrow(IllegalArgumentException::new);
        return DirectorInfoResponseDto.from(director);
    }

    public DirectorListResponseDto directorFindAll() {

        List<Director> directors = directorRepository.findAll();
        List<DirectorInfoResponseDto> directorInfoResponseDtoList = directors.stream()
                .map(DirectorInfoResponseDto::from)
                .toList();
        return DirectorListResponseDto.from(directorInfoResponseDtoList);
    }

    @Transactional
    public void directorUpdate(Long directorId, DirectorUpdateRequestDto directorUpdateRequestDto) {
        Director director = directorRepository.findById(directorId).orElseThrow(IllegalArgumentException::new);
        director.update(directorUpdateRequestDto);
    }

    @Transactional
    public void directorDelete(Long directorId) {
        Director director = directorRepository.findById(directorId).orElseThrow(IllegalArgumentException::new);
        directorRepository.delete(director);
    }
}
