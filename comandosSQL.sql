CREATE TABLE Funcionario(
	idFuncionario SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	dataNascimento DATE NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	dataAdmissao DATE NOT NULL,
	departamento VARCHAR(100) NOT NULL,
	cargo VARCHAR(100) NOT NULL
);

SELECT * FROM FUNCIONARIO;

DROP TABLE funcionario;