package ru.egartech.taskmapper.dto.task.customfield.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final Map<String, String> CUSTOM_FIELDS_NAME_ID;
    public static final String DATE_FORMAT_AS_STRING = "dd.MM.yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_AS_STRING);

    static {
        CUSTOM_FIELDS_NAME_ID = new HashMap<>();


        CUSTOM_FIELDS_NAME_ID.put("образование", "01b8770a-ba4a-48e4-8673-d157d110a446");
        CUSTOM_FIELDS_NAME_ID.put("пол", "067faabc-cc18-45f7-bcba-8399ba6438c3");
        CUSTOM_FIELDS_NAME_ID.put("тел. моб.", "159a46bb-b8f7-41c8-a226-2a523e9b7403");
        CUSTOM_FIELDS_NAME_ID.put("дата рождения", "15b5edd5-7b26-4a2b-9def-e0c211731265");
        CUSTOM_FIELDS_NAME_ID.put("email личный", "2a07e135-3447-4254-aac0-6676fe3b352a");
        CUSTOM_FIELDS_NAME_ID.put("имя", "4caa94ed-8bee-422f-8c35-088faf321fba");
        CUSTOM_FIELDS_NAME_ID.put("оклад - запрос", "59ac2b22-00f4-4137-9413-71ccd7e912ab");
        CUSTOM_FIELDS_NAME_ID.put("фамилия", "6efec6e2-1645-49d4-973b-8a15f93c407e");
        CUSTOM_FIELDS_NAME_ID.put("опыт технолог-ий", "948c4322-be0e-473a-9f54-52b1cb6d428b");
        CUSTOM_FIELDS_NAME_ID.put("тип задачи", "adc61b90-377a-49c0-8e14-a6450d685a81");
        CUSTOM_FIELDS_NAME_ID.put("локация", "bf015492-98e8-42f9-91a4-c802c988283a");
        CUSTOM_FIELDS_NAME_ID.put("гражданство", "e200f52c-087a-4419-ab83-59c1ba5a11a9");
        CUSTOM_FIELDS_NAME_ID.put("отчество", "f73b08e3-03f2-4c8b-9d27-84347daa716a");
        CUSTOM_FIELDS_NAME_ID.put("заказчик", "89c9786d-840a-4392-b4fb-525dafe62762");
        CUSTOM_FIELDS_NAME_ID.put("собеседование дата", "112b2a67-26c5-4e56-aee8-2f28bb462f6f");
        CUSTOM_FIELDS_NAME_ID.put("email to", "f88dbdd5-0522-49c1-ba0c-9cbae957ca99");
        CUSTOM_FIELDS_NAME_ID.put("email cc", "895e3f9a-29f4-4dfa-9892-37a043362905");
        CUSTOM_FIELDS_NAME_ID.put("роль, менеджер", "19de86e2-b324-4df9-a1fc-aa9405098f06");
        CUSTOM_FIELDS_NAME_ID.put("роль, рук. цк", "06bd608e-d6b6-42bb-b6b5-58f62d293dde");
        CUSTOM_FIELDS_NAME_ID.put("email рабочий", "5c6128d2-1c1f-420d-890a-e85afd61b123");
        CUSTOM_FIELDS_NAME_ID.put("приглашение,статусы", "9fb4bc31-311c-46ac-b994-f9102138071f");
        CUSTOM_FIELDS_NAME_ID.put("собеседование,статусы", "e9b3e919-b0c3-48f4-8151-1e6f241f0089");
        CUSTOM_FIELDS_NAME_ID.put("телегерам url", "519a1eaa-5e3f-4017-9bbf-67be5cbd6255");
        CUSTOM_FIELDS_NAME_ID.put("контакт, телеграм", "7dcb1177-6071-40c0-83dc-5c1b45cc7a3c");
        CUSTOM_FIELDS_NAME_ID.put("телеграм-чат", "bf123b34-c1ee-4fe1-9a96-1b9dce072e26");
        CUSTOM_FIELDS_NAME_ID.put("запрос", "1207512b-e234-48e5-8506-1728be7ff0d7");
        CUSTOM_FIELDS_NAME_ID.put("whatsup", "eb239dbb-41eb-4589-b3b6-2af86edb5895");
        CUSTOM_FIELDS_NAME_ID.put("неделя", "30ad6bbd-a8cf-4b9c-af7d-af37ee2e7681");
        CUSTOM_FIELDS_NAME_ID.put("дата начала", "58004edd-d04c-4edb-bed8-f6337397fd72");
        CUSTOM_FIELDS_NAME_ID.put("дата окончания", "879e67e3-eb65-45eb-9a82-94ac94470918");
    }

}
