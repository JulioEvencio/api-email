CREATE TABLE IF NOT EXISTS tb_recipient (
  id UUID PRIMARY KEY,
  recipient VARCHAR(100) NOT NULL,
  email_id UUID REFERENCES tb_email(id) ON DELETE CASCADE ON UPDATE CASCADE
);
