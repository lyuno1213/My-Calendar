package com.example.mycalendar.service;

import com.example.mycalendar.domain.Contents;
import com.example.mycalendar.domain.Users;
import com.example.mycalendar.model.UsersDTO;
import com.example.mycalendar.repos.ContentsRepository;
import com.example.mycalendar.repos.UsersRepository;
import com.example.mycalendar.util.NotFoundException;
import com.example.mycalendar.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final ContentsRepository contentsRepository;

    public UsersService(final UsersRepository usersRepository,
            final ContentsRepository contentsRepository) {
        this.usersRepository = usersRepository;
        this.contentsRepository = contentsRepository;
    }

    public List<UsersDTO> findAll() {
        final List<Users> userses = usersRepository.findAll(Sort.by("id"));
        return userses.stream()
                .map(users -> mapToDTO(users, new UsersDTO()))
                .toList();
    }

    public UsersDTO get(final String id) {
        return usersRepository.findById(id)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        users.setId(usersDTO.getId());
        return usersRepository.save(users).getId();
    }

    public void update(final String id, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    public void delete(final String id) {
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {
        usersDTO.setId(users.getId());
        usersDTO.setPasswd(users.getPasswd());
        usersDTO.setName(users.getName());
        return usersDTO;
    }

    private Users mapToEntity(final UsersDTO usersDTO, final Users users) {
        users.setPasswd(usersDTO.getPasswd());
        users.setName(usersDTO.getName());
        return users;
    }

    public boolean idExists(final String id) {
        return usersRepository.existsByIdIgnoreCase(id);
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Users users = usersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Contents usersIdContents = contentsRepository.findFirstByUsersId(users);
        if (usersIdContents != null) {
            referencedWarning.setKey("users.contents.usersId.referenced");
            referencedWarning.addParam(usersIdContents.getId());
            return referencedWarning;
        }
        return null;
    }

}
