package com.avivas.web.config;



import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.WildcardType;
import java.time.LocalDate;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan("com.avivas.web.rest")
@Configuration
public class SwaggerConfiguration 
{
	/** Mensaje de error general */
	private static final String MENSAJE_ERROR_GENERAL_500 = "Ocurre cuando falla el sistema al realizar una operacion";
	
	/** Objeto utilizado para configurar swagger */
	@Autowired
    private TypeResolver typeResolver;

	@Bean
	public Docket crearApi() 
	{
	    Docket docket = new Docket(DocumentationType.SWAGGER_2);
	    docket.select()
	          .apis( RequestHandlerSelectors.basePackage( "com.avivas" ) )
	          .paths(PathSelectors.any())
	          .build()
	        .pathMapping("/")
	        .directModelSubstitute(LocalDate.class, String.class)
	        .genericModelSubstitutes(ResponseEntity.class)
	        .alternateTypeRules(
	            newRule(typeResolver.resolve(DeferredResult.class,
	                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                typeResolver.resolve(WildcardType.class)))
	        .useDefaultResponseMessages(false)
	        
	        .globalResponseMessage(RequestMethod.GET,
	            newArrayList(new ResponseMessageBuilder()
	                .code(500)
	                .message(MENSAJE_ERROR_GENERAL_500)
	                .build()))
	        .globalResponseMessage(RequestMethod.POST,
		            newArrayList(new ResponseMessageBuilder()
		                .code(500)
		                .message(MENSAJE_ERROR_GENERAL_500)
		                .build()))
	        .globalResponseMessage(RequestMethod.PUT,
		            newArrayList(new ResponseMessageBuilder()
		                .code(500)
		                .message(MENSAJE_ERROR_GENERAL_500)
		                .build()))
	        .globalResponseMessage(RequestMethod.DELETE,
		            newArrayList(new ResponseMessageBuilder()
		                .code(500)
		                .message(MENSAJE_ERROR_GENERAL_500)
		                .build()))
	        .globalResponseMessage(RequestMethod.PATCH,
		            newArrayList(new ResponseMessageBuilder()
		                .code(500)
		                .message(MENSAJE_ERROR_GENERAL_500)
		                .build()))
	        .enableUrlTemplating(false)	        
	        .tags(new Tag("Servicios Contacts", "API Servicios Contacts"));
	    
	    docket.apiInfo(apiInfo());
	    
	    return docket;
	}
	
	@Bean
	UiConfiguration uiConfig() 
	{
	    return UiConfigurationBuilder.builder()
	        .deepLinking(true)
	        .displayOperationId(false)
	        .defaultModelsExpandDepth(2)
	        .defaultModelExpandDepth(2)
	        .defaultModelRendering(ModelRendering.EXAMPLE)
	        .displayRequestDuration(true)
	        .docExpansion(DocExpansion.FULL)
	        .filter(false)
	        .maxDisplayedTags(null)
	        .operationsSorter(OperationsSorter.ALPHA)
	        .showExtensions(false)
	        .tagsSorter(TagsSorter.ALPHA)
	        .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
	        .validatorUrl(null)
	        .build();
	}
	  
	private ApiInfo apiInfo() 
	{	      
	    return new ApiInfo(
	        "API Servicios Contacts", 
	        "API desarrollada para construir el test de cyxtera", 
	        "0.0.1",
	        "", 
	        new Contact("Desarrollado por Alejandro Vivas", "http://www.avivas.com/", ""), 
	        "", "", Collections.emptyList());
	}

    public void setTypeResolver(TypeResolver typeResolver)
    {
        this.typeResolver = typeResolver;
    }
}
