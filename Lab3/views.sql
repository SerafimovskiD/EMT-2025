CREATE MATERIALIZED VIEW accommodations_per_host_view AS
SELECT
    host_id,
    COUNT(*) AS accommodation_count
FROM
    accommodation
GROUP BY
    host_id;

CREATE MATERIALIZED VIEW hosts_per_country_view AS
SELECT
    country_id,
    COUNT(*) AS host_count
FROM
    host
GROUP BY
    country_id;