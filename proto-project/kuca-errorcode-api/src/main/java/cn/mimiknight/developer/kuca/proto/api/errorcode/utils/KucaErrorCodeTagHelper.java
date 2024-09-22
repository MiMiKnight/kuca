package cn.mimiknight.developer.kuca.proto.api.errorcode.utils;

public final class KucaErrorCodeTagHelper {

    private KucaErrorCodeTagHelper() {
    }

    /**
     * tag name
     */
    public interface TagName {
        String TYPES = "types";
        String TYPE = "type";
        String MODULES = "modules";
        String MODULE = "module";
        String ERROR = "error";
    }

    /**
     * tag attribute
     */
    public interface TagAttribute {

        interface Type {
            String ID = "id";
            String STATUS = "status";
        }

        interface Module {
            String ID = "id";
        }

        interface Error {
            String ID = "id";
            String TYPE = "type";
        }
    }
}
