package ru.egartech.sdk.dto.task.deserialization.customfield.field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.attachment.AttachmentFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.defaultf.DefaultFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelsFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.number.NumberFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.relationship.RelationshipFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.text.TextFieldDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = DefaultFieldDto.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttachmentFieldDto.class, name = "attachment"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "date"),
        @JsonSubTypes.Type(value = DropdownFieldDto.class, name = "drop_down"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "email"),
        @JsonSubTypes.Type(value = LabelsFieldDto.class, name = "labels"),
        @JsonSubTypes.Type(value = NumberFieldDto.class, name = "number"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "phone"),
        @JsonSubTypes.Type(value = RelationshipFieldDto.class, name = "list_relationship"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "short_text"),
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "url"),
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CustomField<T> {
    protected String id;
    protected String name;
    protected T value;
}
