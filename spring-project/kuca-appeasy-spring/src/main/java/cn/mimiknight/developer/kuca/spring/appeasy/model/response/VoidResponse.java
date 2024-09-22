package cn.mimiknight.developer.kuca.spring.appeasy.model.response;

/**
 * void response
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 11:47:41
 */
public final class VoidResponse {

    private VoidResponse() {
    }

    public static VoidResponse create() {
        return new VoidResponse();
    }
}
