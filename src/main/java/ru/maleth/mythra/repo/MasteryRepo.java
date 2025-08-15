package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maleth.mythra.enums.MasteryEnum;
import ru.maleth.mythra.model.characters.Mastery;
import ru.maleth.mythra.model.characters.Proficiency;

@Repository
public interface MasteryRepo extends JpaRepository<Mastery, Long> {

    Mastery findByName(MasteryEnum name);

}
