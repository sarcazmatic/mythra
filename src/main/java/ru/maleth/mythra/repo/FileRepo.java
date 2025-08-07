package ru.maleth.mythra.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.model.File;

public interface FileRepo extends JpaRepository<File, Long> {

    @Transactional
    @Query("SELECT f from File f " +
            "JOIN FETCH Character c ON f.character.id=c.id " +
            "JOIN FETCH User u ON c.creator.id=u.id " +
            "WHERE (:userName = u.name) " +
            "AND (c.charName = :charName)")
    File findByCharacterCharNameAnd(String userName, String charName);

}
