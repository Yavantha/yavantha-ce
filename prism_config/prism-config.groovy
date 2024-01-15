import yavantha.plugin.security.util.SecurityUtils

yavantha.plugin.core.i18n.default = [en: 'English']
yavantha.plugin.core.i18n.languages = [:]
yavantha.plugin.core.i18n.update = true

// allow cors
grails.cors.enabled = true
grails.cors.allowedOrigins = ['*']

Map securityParams = [saml: false, ldap: false]


// ----------------------  POSTGRES  ----------------------------

dataSource {
    driverClassName = 'org.postgresql.Driver'
    dialect = 'yavantha.plugin.core.data.hibernate.dialect.PostgreSQL82Dialect'
    url= "jdbc:postgresql://prismdb:5432/prism"
    username = 'prism'
    password = 'prism'

    properties 
            {     
                defaultAutoCommit = false 
            } 

}

hibernate {
    jdbc.batch_size = 100 
    order_inserts = true
}
    
// allow csrf
grails.plugin.springsecurity.csrf.active = false

// spring security provider names and chainmap
grails {
    plugin {
        springsecurity {
            providerNames = SecurityUtils.getProviderNames(securityParams)
            filterChain {
                chainMap = SecurityUtils.getChainMap(securityParams)
            }
        }
    }
}

elasticSearch {
    enable = true
    disableAutoIndex = false
    bulkIndexOnStartup = true
    hosts = [[host: 'elasticsearch', port: 9200]]
}
