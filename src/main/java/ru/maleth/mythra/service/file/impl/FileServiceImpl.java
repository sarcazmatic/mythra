package ru.maleth.mythra.service.file.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.dto.mapper.FileMapper;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.File;
import ru.maleth.mythra.repo.FileRepo;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.file.FileService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final CharacterService characterService;
    private final FileMapper fileMapper;
    private final FileRepo fileRepo;

    @Override
    public void save(MultipartFile mpFile, String userName, String charName) throws IOException {
        File file = fileMapper.fromMPFile(mpFile);
        try {
            File fileToChange = fileRepo.findByCharacterCharNameAnd(userName, charName);
            file.setId(fileToChange.getId());
        } catch (NullPointerException e) {
            log.info("У персонажа юзера {} не было найдено аватара – ставим впервые", userName);
        }
        Character character = characterService.findByUserNameAndCharName(userName, charName);
        file.setCharacter(character);
        fileRepo.save(file);
    }

    @Override
    public FileDTO findByUserNameAndCharName(String userName, String charName) throws IOException {
        log.info("Ищем файл по имени юзера '{}' и персонажа '{}'", userName, charName);
        File file = fileRepo.findByCharacterCharNameAnd(userName, charName);
        try {
            file.getName();
        } catch (NullPointerException e) {
            log.info("У персонажа юзера {} не было найдено аватара – выводим дефолт", userName);
            file = File.builder()
                    .id(1L)
                    .name("placeholder")
                    .content(Files.readAllBytes(Paths.get("src/main/resources/images/portait_placeholder.png")))
                    .contentType("image/jpeg")
                    .build();;
        }
        return fileMapper.fromFile(file);
    }

}
