package cn.mimiknight.developer.kuca.spring.ecology;

import java.util.Objects;

/**
 * DetachManager factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:25:38
 */
public class EcologyManagerFactory {

    private static EcologyManager manager;

    private EcologyManagerFactory() {
    }

    /**
     * create DetachManager
     *
     * @return {@link EcologyManager }
     */
    public static synchronized EcologyManager create() {
        if (Objects.isNull(manager)) {
            manager = new EcologyManager();
        }
        return manager;
    }

    /**
     * get detach manager
     *
     * @return {@link EcologyManager }
     */
    public static EcologyManager getDetachManager() {
        return create();
    }

    /**
     * set config
     *
     * @param config config
     * @return {@link EcologyManager }
     */
    public EcologyManager setConfig(EcologyConfig config) {
        manager.setConfig(config);
        return manager;
    }

}
