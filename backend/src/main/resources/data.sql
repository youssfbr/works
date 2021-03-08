INSERT INTO tb_client (name, email, telephone) VALUES ('Alisson Youssf', 'youssfbr@gmail.com', '1234563');
INSERT INTO tb_client (name, email, telephone) VALUES ('Link Informática', 'contato@link.com', '12345453');
INSERT INTO tb_client (name, email, telephone) VALUES ('Marcio', 'marcio@email.com', '123456453');
INSERT INTO tb_client (name, email, telephone) VALUES ('Fernanda', 'fernanda@email.com', '1234567');

INSERT INTO tb_order (opening_date, status, description, client_id) VALUES (NOW(), 1, 'Computador não liga', 3);
INSERT INTO tb_order (opening_date, status, description, client_id) VALUES (NOW(), 1, 'Computador travando', 4);

INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'Cooler com defeito', 1);
INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'Sem previsão, sem peça no momento', 1);
INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'Não liga', 2);
INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'Mal contato na chave', 2);
INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'USB não funciona', 2);
INSERT INTO TB_COMMENTATION (date, description, order_id) VALUES (NOW(), 'Detectado Windows ruim. Necessário formatar', 2);