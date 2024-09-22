package cn.mimiknight.developer.kuca.proto.errorcode.processor;

import cn.mimiknight.developer.kuca.proto.api.errorcode.AbstractKucaErrorTypeFactory;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorType;
import cn.mimiknight.developer.kuca.proto.api.errorcode.utils.KucaErrorCodeXmlUtils;
import org.jsoup.nodes.Document;

/**
 * general kuca error type factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 19:03:39
 */
public final class GeneralKucaErrorTypeFactory extends AbstractKucaErrorTypeFactory {

    private static GeneralKucaErrorTypeFactory factory;

    /**
     * xml document
     */
    private Document document;

    private GeneralKucaErrorTypeFactory() {
    }

    @Override
    public IKucaErrorType getErrorType(String id) {
        return KucaErrorCodeXmlUtils.getErrorTypeById(id, document);
    }

    /**
     * create
     *
     * @param document document
     * @return {@link GeneralKucaErrorTypeFactory }
     */
    public static synchronized GeneralKucaErrorTypeFactory create(Document document) {
        if (null == factory) {
            factory = new GeneralKucaErrorTypeFactory();
            factory.document = document;
        }
        return factory;
    }

}
