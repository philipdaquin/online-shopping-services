package com.example.auth_service.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class StaticResourcesWebConfigurerTest {
    
    private MockServletContext mockServletContext;
    private WebApplicationContext webApplicationContext;
    private ResourceHandlerRegistry resourceHandlerRegistry;

    @BeforeEach
    void init() { 
        mockServletContext = spy(new MockServletContext());
        webApplicationContext = mock(WebApplicationContext.class);
        resourceHandlerRegistry = spy(new ResourceHandlerRegistry(webApplicationContext, mockServletContext));
    } 
}
