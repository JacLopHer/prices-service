CREATE TABLE IF NOT EXISTS prices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand_id INT NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    price_list INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    curr VARCHAR(3) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_prices_product_id ON prices(product_id);
CREATE INDEX IF NOT EXISTS idx_prices_brand_id ON prices(brand_id);
CREATE INDEX IF NOT EXISTS idx_prices_start_date ON prices(start_date);
CREATE INDEX IF NOT EXISTS idx_prices_end_date ON prices(end_date);
CREATE INDEX IF NOT EXISTS idx_prices_priority ON prices(priority);
-- Removed composite index idx_prices_search as it does not efficiently support the date range query pattern

INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr) VALUES
(1, 1, '2020-06-14T00:00:00+02:00', '2020-12-31T23:59:59+02:00', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14T15:00:00+02:00', '2020-06-14T18:30:00+02:00', 2, 35455, 0, 25.45, 'EUR'),
(3, 1, '2020-06-15T00:00:00+02:00', '2020-06-15T11:00:00+02:00', 3, 35455, 0, 30.50, 'EUR'),
(4, 1, '2020-06-15T16:00:00+02:00', '2020-12-31T23:59:59+02:00', 4, 35455, 0, 38.95, 'EUR');
