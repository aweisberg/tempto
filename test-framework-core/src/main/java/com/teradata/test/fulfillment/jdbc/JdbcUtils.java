/*
 * Copyright 2013-2015, Teradata, Inc. All rights reserved.
 */

package com.teradata.test.fulfillment.jdbc;

import com.teradata.test.configuration.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import static java.sql.DriverManager.getConnection;

public final class JdbcUtils
{

    static final String DATABASES_CONFIGURATION_SECTION = "databases";

    public static void registerDriver(JdbcConnectivityParamsState jdbcParamsState)
    {
        try {
            Class.forName(jdbcParamsState.driverClass);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load jdbc driver class: " + jdbcParamsState.driverClass, e);
        }
    }

    public static Connection connection(JdbcConnectivityParamsState jdbcParamsState)
            throws SQLException
    {
        return getConnection(
                jdbcParamsState.url,
                jdbcParamsState.user,
                jdbcParamsState.password);
    }

    private JdbcUtils()
    {
    }

    public static Set<String> getDefinedJdcbConnectionNames(Configuration configuration)
    {
        return configuration.getSubconfiguration(DATABASES_CONFIGURATION_SECTION).listKeyPrefixes(1);
    }
}
