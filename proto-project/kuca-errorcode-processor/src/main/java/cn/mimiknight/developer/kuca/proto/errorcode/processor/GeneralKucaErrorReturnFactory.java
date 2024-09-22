package cn.mimiknight.developer.kuca.proto.errorcode.processor;

import cn.mimiknight.developer.kuca.proto.api.errorcode.AbstractKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;
import cn.mimiknight.developer.kuca.proto.api.errorcode.utils.KucaErrorCodeXmlUtils;
import org.jsoup.nodes.Document;

/**
 * general kuca error return factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 19:03:00
 */
public final class GeneralKucaErrorReturnFactory extends AbstractKucaErrorReturnFactory {

    private static GeneralKucaErrorReturnFactory factory;

    /**
     * xml document
     */
    private Document document;

    @Override
    public IKucaErrorReturn getErrorReturn(String code) {
        return KucaErrorCodeXmlUtils.getErrorReturnById(code, document);
    }

    /**
     * create
     *
     * @param document document
     * @return {@link GeneralKucaErrorReturnFactory }
     */
    public static synchronized GeneralKucaErrorReturnFactory create(Document document) {
        if (null == factory) {
            factory = new GeneralKucaErrorReturnFactory();
            factory.document = document;
        }
        return factory;
    }
}
