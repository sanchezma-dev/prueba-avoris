CREATE TABLE IF NOT EXISTS hotel_searches
(
    search_id
    UUID
    PRIMARY
    KEY,
    hotel_id
    VARCHAR
(
    255
) NOT NULL, check_in DATE NOT NULL, check_out DATE NOT NULL, ages INTEGER [] NOT NULL);
CREATE INDEX IF NOT EXISTS idx_hotel_searches_similarity
    ON hotel_searches (hotel_id, check_in, check_out, ages);
