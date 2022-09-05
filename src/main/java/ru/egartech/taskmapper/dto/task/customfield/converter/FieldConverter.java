package ru.egartech.taskmapper.dto.task.customfield.converter;

import com.fasterxml.jackson.core.TreeNode;
import ru.egartech.taskmapper.dto.task.customfield.FieldType;

public interface FieldConverter<T> {

    T convert(TreeNode treeNode);

    FieldType getType();

}
