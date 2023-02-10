package com.workshop23.ws23.repo;

public class Queries {
    public static final String  SQL_NW_GET_ORDER_DETAIL = """
        SELECT  o.id AS order_id, DATE_FORMAT(o.order_date, \"%d/%m/%Y\") AS order_date, o.customer_id,
        SUM(od.quantity * od.unit_price)-SUM(od.quantity * od.unit_price * od.discount) AS Total_Price,
        SUM(od.quantity * p.standard_cost) AS Cost_Price
        FROM orders o
        JOIN order_details AS od
        ON o.id = od.order_id
        JOIN products p
        ON od.product_id = p.id
        WHERE o.id = ?;
            """;

    // check the differences of 2 tables
    // If required the left out data from INNER JOIN >> LEFT JOIN
    /*
        SELECT *
        FROM orders o
        WHERE o.id NOT IN(
        select order_id
        from order_details
        )
     */
}
