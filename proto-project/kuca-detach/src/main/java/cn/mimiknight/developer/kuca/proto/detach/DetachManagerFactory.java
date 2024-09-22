package cn.mimiknight.developer.kuca.proto.detach;

import java.util.Objects;

/**
 * DetachManager factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:25:38
 */
public class DetachManagerFactory {

    private static DetachManager manager;

    private DetachManagerFactory() {
    }

    /**
     * create DetachManager
     *
     * @return {@link DetachManager }
     */
    public static synchronized DetachManager create() {
        if (Objects.isNull(manager)) {
            manager = new DetachManager();
        }
        return manager;
    }

    /**
     * get detach manager
     *
     * @return {@link DetachManager }
     */
    public static DetachManager getDetachManager() {
        return create();
    }

}
