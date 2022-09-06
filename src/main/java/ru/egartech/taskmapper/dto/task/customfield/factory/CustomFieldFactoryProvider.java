package ru.egartech.taskmapper.dto.task.customfield.factory;

import ru.egartech.taskmapper.dto.task.customfield.CustomField;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentDto;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownOption;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelOptionDto;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipValueDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomFieldFactoryProvider {

    public static CustomField<?> createCustomField(FieldType fieldType, Map<String, ?> values) {
        String id = (String) values.get("id");
        String name = (String) values.get("name");
        Object value = Objects.requireNonNull(values.get("value"));

        switch (fieldType) {
            case EMPTY:
                return new EmptyFieldFactory().createField(id, name, (String) value);
            case UNKNOWN:
                return new UnknownFieldFactory().createField(id, name, (String) value);
            case LIST_RELATIONSHIP:
                return new ListRelationShipFieldFactory().createField(id, name, (List<RelationShipValueDto>) value);
            case DROP_DOWN:
                return new DropdownFieldFactory().createField(id, name, (DropdownOption) value);
            case LABELS:
                return new LabelFieldFactory().createField(id, name, (List<LabelOptionDto>) value);
            case SHORT_TEXT:
                return new TextFieldFactory().createField(id, name, (String) value);
            case PHONE:
                return new PhoneFieldFactory().createField(id, name, (String) value);
            case EMAIL:
                return new EmailFieldFactory().createField(id, name, (String) value);
            case URL:
                return new UrlFieldFactory().createField(id, name, (String) value);
            case DATE:
                return new DateFieldFactory().createField(id, name, (String) value);
            case ATTACHMENT:
                return new AttachmentFieldFactory().createField(id, name, (List<AttachmentDto>) value);
            case NUMBER:
                return new NumberFieldFactory().createField(id, name, (Number) value);
        }

        throw new RuntimeException("Type: " + fieldType + " has no factory implementation...");
    }

}
