SELECT
    i.id AS item_id,
    i.name AS item_name,
    COALESCE(total_orders, 0) AS total_orders,
    COALESCE(total_inventory, 0) AS total_inventory,
    COALESCE(total_price_orders, 0) AS revenue
FROM dbo.t_item i
LEFT JOIN
    (SELECT item_id, SUM(qty) AS total_orders
    FROM dbo.t_order
    GROUP BY item_id) o ON i.id = o.item_id
LEFT JOIN
    (SELECT item_id, SUM(qty) AS total_inventory
    FROM dbo.t_inventory
    GROUP BY item_id) inv ON i.id = inv.item_id
LEFT JOIN
    (SELECT item_id, SUM(o.qty * it.price) AS total_price_orders
    FROM dbo.t_order o
    JOIN dbo.t_item it ON o.item_id = it.id
    GROUP BY item_id) tp ON i.id = tp.item_id
ORDER BY i.id;