package com.example.mycalendar.service;

import com.example.mycalendar.domain.Contents;
import com.example.mycalendar.domain.Users;
import com.example.mycalendar.model.ContentsDTO;
import com.example.mycalendar.repos.ContentsRepository;
import com.example.mycalendar.repos.UsersRepository;
import com.example.mycalendar.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final UsersRepository usersRepository;

    public ContentsService(final ContentsRepository contentsRepository,
            final UsersRepository usersRepository) {
        this.contentsRepository = contentsRepository;
        this.usersRepository = usersRepository;
    }

    public List<ContentsDTO> findAll() {
        final List<Contents> contentses = contentsRepository.findAll(Sort.by("id"));
        return contentses.stream()
                .map(contents -> mapToDTO(contents, new ContentsDTO()))
                .toList();
    }

    public ContentsDTO get(final String id) {
        return contentsRepository.findById(id)
                .map(contents -> mapToDTO(contents, new ContentsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ContentsDTO contentsDTO) {
        final Contents contents = new Contents();
        mapToEntity(contentsDTO, contents);
        contents.setId(contentsDTO.getId());
        return contentsRepository.save(contents).getId();
    }

    public void update(final String id, final ContentsDTO contentsDTO) {
        final Contents contents = contentsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(contentsDTO, contents);
        contentsRepository.save(contents);
    }

    public void delete(final String id) {
        contentsRepository.deleteById(id);
    }

    private ContentsDTO mapToDTO(final Contents contents, final ContentsDTO contentsDTO) {
        contentsDTO.setId(contents.getId());
        contentsDTO.setContents(contents.getContents());
        contentsDTO.setUsersId(contents.getUsersId() == null ? null : contents.getUsersId().getId());
        return contentsDTO;
    }

    private Contents mapToEntity(final ContentsDTO contentsDTO, final Contents contents) {
        contents.setContents(contentsDTO.getContents());
        final Users usersId = contentsDTO.getUsersId() == null ? null : usersRepository.findById(contentsDTO.getUsersId())
                .orElseThrow(() -> new NotFoundException("usersId not found"));
        contents.setUsersId(usersId);
        return contents;
    }

    public boolean idExists(final String id) {
        return contentsRepository.existsByIdIgnoreCase(id);
    }

}
