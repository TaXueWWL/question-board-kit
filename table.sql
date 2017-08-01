-- Oracle --
-- Create table
create table QBK_QUESTION
(
  QBK_ID     VARCHAR2(150) not null,
  QBK_TITLE  VARCHAR2(300) not null,
  QBK_KEY    VARCHAR2(50),
  QBK_ANSWER VARCHAR2(999),
  QBK_DATE   DATE not null,
  QBK_TYPE   VARCHAR2(2)
)
