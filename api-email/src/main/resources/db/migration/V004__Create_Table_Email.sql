CREATE TABLE IF NOT EXISTS tb_email (
  id UUID PRIMARY KEY,
  subject VARCHAR(100) NOT NULL,
  content TEXT NOT NULL,
  user_id UUID REFERENCES tb_user(id) ON DELETE CASCADE ON UPDATE CASCADE
);