package com.gucar.ecommerce.configuration;

import com.gucar.ecommerce.model.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import com.gucar.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig( EntityManager entityManager ) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration( RepositoryRestConfiguration config, CorsRegistry cors ) {

        HttpMethod[] theUnsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };

        config.getExposureConfiguration()
                .forDomainType( ProductCategory.class )
                .withItemExposure( ( metdata, httpMethods ) -> httpMethods.disable( theUnsupportedActions ) )
                .withCollectionExposure( ( metdata, httpMethods ) -> httpMethods.disable( theUnsupportedActions ) );

        config.getExposureConfiguration()
                .forDomainType( ProductCategory.class )
                .withItemExposure( ( metdata, httpMethods ) -> httpMethods.disable( theUnsupportedActions ) )
                .withCollectionExposure( ( metdata, httpMethods ) -> httpMethods.disable( theUnsupportedActions ) );

        exposeIds( config );
    }

    private void exposeIds( RepositoryRestConfiguration config ) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        
        List<Class> entityClasses = new ArrayList<>();

        for ( EntityType entityType : entities ) {
            entityClasses.add( entityType.getJavaType() );
        }

        Class[] domainTypes = entityClasses.toArray(  new Class[0] );
        config.exposeIdsFor( domainTypes );
    }
}
