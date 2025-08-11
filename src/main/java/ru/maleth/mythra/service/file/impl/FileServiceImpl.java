package ru.maleth.mythra.service.file.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.dto.mapper.FileMapper;
import ru.maleth.mythra.model.characters.Character;
import ru.maleth.mythra.model.File;
import ru.maleth.mythra.repo.FileRepo;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.file.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final CharacterService characterService;
    private final FileRepo fileRepo;

    @Override
    public void save(MultipartFile mpFile, String userName, String charName) throws IOException {
        log.info("Сохраняем за юзером '{}' и персонажем '{}' аватар", userName, charName);
        File file = FileMapper.fromMPFile(mpFile);
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
            log.info("У персонажа {} не было найдено аватара – выводим дефолт", charName);
            file = File.builder()
                    .id(1L)
                    .name("placeholder")
                    .content(Files.readAllBytes(Paths.get("src/main/resources/images/portait_placeholder.png")))
                    .contentType("image/jpeg")
                    .build();;
        }
        return FileMapper.fromFile(file);
    }

}
