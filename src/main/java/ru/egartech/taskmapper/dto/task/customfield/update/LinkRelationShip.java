package ru.egartech.taskmapper.dto.task.customfield.update;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LinkRelationShip {

    private List<String> add = new ArrayList<>();
    private List<String> rem = new ArrayList<>();

    public static LinkRelationShip add(String... args) {
        LinkRelationShip relationShip = new LinkRelationShip();
        relationShip.setAdd(List.of(args));
        return relationShip;
    }

    public static LinkRelationShip remove(String... args) {
        LinkRelationShip relationShip = new LinkRelationShip();
        relationShip.setRem(List.of(args));
        return relationShip;
    }
}
