package in.ashokit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import in.ashokit.entity.Product;
import in.ashokit.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		HttpMethod[] unsupportedMethods = { HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.DELETE };
		
		config.getExposureConfiguration()
			  .forDomainType(Product.class)
			  .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods))
			  .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(unsupportedMethods));
		
		config.getExposureConfiguration()
			  .forDomainType(ProductCategory.class)
			  .withItemExposure((metadata, http) -> http.disable(unsupportedMethods))
			  .withItemExposure((metadata, http) -> http.disable(unsupportedMethods));

		config.exposeIdsFor(Product.class, ProductCategory.class);
	}
}
