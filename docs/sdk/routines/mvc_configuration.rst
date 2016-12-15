Mvc configuration
=================

To use services direct in handler methods we need following:

.. code-block:: java

    @Bean
    public HandlerMethodArgumentResolver ivisServiceArgumentResolver() {
        return new IvisServiceArgumentResolver();
    }

    @Bean
    public IvisIdToDomainClassConverter ivisIdToDomainClassConverter() {
        return new IvisIdToDomainClassConverter(conversionServiceFactoryBean().getObject());
    }

    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        return new ConversionServiceFactoryBean();
    }

