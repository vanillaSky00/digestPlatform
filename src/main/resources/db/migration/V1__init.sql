-- articles table
CREATE TABLE IF NOT EXISTS articles (
                                        id            uuid PRIMARY KEY,
                                        source        text NOT NULL,
                                        external_id   text,
                                        title         text NOT NULL,
                                        url           text NOT NULL,
                                        url_hash_hex  char(64) NOT NULL,
    author        text,
    published_at  timestamptz,
    first_seen_at timestamptz NOT NULL DEFAULT now(),

    CONSTRAINT uk_articles_source_external UNIQUE (source, external_id),
    CONSTRAINT uk_articles_url_hash_hex    UNIQUE (url_hash_hex)
    );

-- Indexes
CREATE INDEX IF NOT EXISTS idx_articles_published_at
    ON articles (published_at DESC NULLS LAST);

-- (Optional but useful) search by host/domain quickly
-- ALTER TABLE articles ADD COLUMN host text;
-- CREATE INDEX idx_articles_host_time ON articles (host, published_at DESC NULLS LAST);
