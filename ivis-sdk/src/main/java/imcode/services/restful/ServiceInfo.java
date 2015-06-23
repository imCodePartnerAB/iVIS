package imcode.services.restful;

import com.imcode.services.GenericService;

/**
 * Created by vitaly on 19.06.15.
 */
public class ServiceInfo<T, ID> {
    GenericService<T, ID> service;
    Class<T> entityClass;
    Class<ID> idClass;

    public ServiceInfo(Class<T> entityClass, Class<ID> idClass, GenericService<T, ID> service) {
        this.entityClass = entityClass;
        this.idClass = idClass;
        this.service = service;
    }

    public GenericService<T, ID> getService() {
        return service;
    }

    public void setService(GenericService<T, ID> service) {
        this.service = service;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<ID> getIdClass() {
        return idClass;
    }

    public void setIdClass(Class<ID> idClass) {
        this.idClass = idClass;
    }
}
