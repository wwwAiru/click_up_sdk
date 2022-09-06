package ru.egartech.taskmapper.dto.task.customfield.update;

import lombok.Data;

import java.util.List;

@Data
public class RelationShip {

    private List<String> add;
    private List<String> rem;

    public static RelationShip add(String... args) {
        RelationShip relationShip = new RelationShip();
        relationShip.setAdd(List.of(args));
        return relationShip;
    }

    public static RelationShip remove(String... args) {
        RelationShip relationShip = new RelationShip();
        relationShip.setRem(List.of(args));
        return relationShip;
    }
}
