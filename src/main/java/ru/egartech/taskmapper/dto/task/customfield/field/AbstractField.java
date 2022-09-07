package ru.egartech.taskmapper.dto.task.customfield.field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.date.DateFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.email.EmailFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelsFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.number.NumberFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.phone.PhoneFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.relationship.RelationShipFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.text.TextFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.url.UrlFieldDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttachmentFieldDto.class, name = "attachment"),
        @JsonSubTypes.Type(value = DateFieldDto.class, name = "date"),
        @JsonSubTypes.Type(value = DropdownFieldDto.class, name = "drop_down"),
        @JsonSubTypes.Type(value = EmailFieldDto.class, name = "email"),
        @JsonSubTypes.Type(value = LabelsFieldDto.class, name = "labels"),
        @JsonSubTypes.Type(value = NumberFieldDto.class, name = "number"),
        @JsonSubTypes.Type(value = PhoneFieldDto.class, name = "phone"),
        @JsonSubTypes.Type(value = RelationShipFieldDto.class, name = "list_relationship"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "short_text"),
        @JsonSubTypes.Type(value = UrlFieldDto.class, name = "url"),
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractField<T> {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String id;

    protected String name;

    protected T value;

}
