CREATE TABLE lojas(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `codigo_loja` varchar(255) NOT NULL,
    `faixa_inicio` bigint NOT NULL,
    `faixa_fim` bigint NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
