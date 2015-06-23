package imcode.services.restful;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by vitaly on 18.06.15.
 */
public class ClassPathComponentScaner extends ClassPathScanningCandidateComponentProvider {
    public ClassPathComponentScaner() {
        super(false, new StandardEnvironment());
    }


    @Override
    protected void registerDefaultFilters() {

    }

    public static void main(String[] args) {
        ClassPathComponentScaner loader = new ClassPathComponentScaner();
        loader.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        Set<BeanDefinition> resources = loader.findCandidateComponents("com.imcode.entities");
        System.out.println(resources);
    }
}
