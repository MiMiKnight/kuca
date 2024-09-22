package cn.mimiknight.developer.kuca.proto.api.errorcode.utils;

import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.impl.KucaErrorReturn;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.impl.KucaErrorType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;

public final class KucaErrorCodeXmlUtils {

    private KucaErrorCodeXmlUtils() {
    }

    /**
     * get error type by id
     *
     * @param id       type id
     * @param document document
     * @return {@link KucaErrorType }
     */
    public static KucaErrorType getErrorTypeById(String id, Document document) {
        Element element = document.getElementById(id);
        if (Objects.isNull(element)) {
            throw new KucaErrorCodeException("No element found by id and id = %1$s", id);
        }
        String tagName = element.tagName();
        if (!KucaErrorCodeTagHelper.TagName.TYPE.equals(tagName)) {
            throw new KucaErrorCodeException("Element tag name mismatch and id = %1$s", id);
        }
        String status = element.attr(KucaErrorCodeTagHelper.TagAttribute.Type.STATUS);
        if (StringUtils.isBlank(status)) {
            throw new KucaErrorCodeException("Element 'status' attribute value is invalid and id = %1$s", id);
        }
        String desc = element.text();
        KucaErrorType type = new KucaErrorType();
        type.setId(id);
        type.setStatus(Integer.parseInt(status));
        type.setDesc(desc);
        return type;
    }

    /**
     * get error return by id
     *
     * @param id       code id
     * @param document document
     * @return {@link KucaErrorReturn }
     */
    public static KucaErrorReturn getErrorReturnById(String id, Document document) {
        Element element = document.getElementById(id);
        if (Objects.isNull(element)) {
            throw new KucaErrorCodeException("No element found by id and id = %1$s", id);
        }
        String tagName = element.tagName();
        if (!KucaErrorCodeTagHelper.TagName.ERROR.equals(tagName)) {
            throw new KucaErrorCodeException("Element tag name mismatch and id = %1$s", id);
        }
        String typeAttrID = element.attr(KucaErrorCodeTagHelper.TagAttribute.Error.TYPE);
        if (StringUtils.isBlank(typeAttrID)) {
            throw new KucaErrorCodeException("Element 'type' attribute value is invalid and id = %1$s", id);
        }
        KucaErrorReturn errorReturn = new KucaErrorReturn();
        KucaErrorType errorType = getErrorTypeById(typeAttrID, document);
        String message = element.text();
        errorReturn.setCode(id);
        errorReturn.setType(errorType);
        errorReturn.setMessage(message);
        return errorReturn;
    }
}
